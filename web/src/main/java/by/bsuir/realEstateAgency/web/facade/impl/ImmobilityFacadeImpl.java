package by.bsuir.realEstateAgency.web.facade.impl;

import by.bsuir.realEstateAgency.core.model.*;
import by.bsuir.realEstateAgency.core.service.CityService;
import by.bsuir.realEstateAgency.core.service.ImmobilityService;
import by.bsuir.realEstateAgency.core.service.PhotoService;
import by.bsuir.realEstateAgency.web.bean.immobility.ImmobilityDto;
import by.bsuir.realEstateAgency.web.bean.immobility.PhotoDto;
import by.bsuir.realEstateAgency.web.facade.ImmobilityFacade;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImmobilityFacadeImpl implements ImmobilityFacade {

    static Logger log = Logger.getLogger(ImmobilityFacadeImpl.class.getName());

    private final static int MAX_FILE_COUNT = 10;

    private final static int LENGTH_FILE_NAME = 15;

    @Resource
    private ImmobilityService immobilityService;

    @Resource
    private PhotoService photoService;

    @Resource
    private CityService cityService;

    @Resource
    private ServletContext servletContext;

    @Transactional
    @Override
    public ImmobilityDto getImmobility(Long key) {
        Immobility immobility = immobilityService.get(key);
        if (immobility == null) {
            return null;
        }
        ImmobilityDto immobilityDto = new ImmobilityDto();
        immobilityDto.setId(immobility.getId());
        immobilityDto.setName(immobility.getName());
        immobilityDto.setOwnerLogin(immobility.getOwner().getLogin());
        immobilityDto.setDescription(immobility.getDescription());
        immobilityDto.setPrice(immobility.getPrice());
        immobilityDto.setCityName(immobility.getCity().getName());
        immobilityDto.setNumberOfRooms(immobility.getNumberOfRooms());
        immobilityDto.setSquare(immobility.getSquare());
        immobilityDto.setAddress(immobility.getAddress());
        immobilityDto.setTypeImmobility(immobility.getType());
        immobilityDto.setPhotos(immobility.getPhotos().stream()
                .map(photo -> new PhotoDto(photo.getId(), photo.getPath()))
                .collect(Collectors.toList()));

        return immobilityDto;
    }

    @Override
    public void saveOrUpdate(ImmobilityDto immobilityDto, User user) {
        Immobility immobility = getImmobilityWithCheck(immobilityDto, user);
        updatePhotos(immobility, immobilityDto);

        immobility.setId(immobilityDto.getId());
        immobility.setName(immobilityDto.getName());
        immobility.setDescription(immobilityDto.getDescription());
        immobility.setPrice(immobilityDto.getPrice());
        immobility.setNumberOfRooms(immobilityDto.getNumberOfRooms());
        immobility.setSquare(immobilityDto.getSquare());
        immobility.setAddress(immobilityDto.getAddress());
        immobility.setType(immobilityDto.getTypeImmobility());

        String cityName = immobilityDto.getCityName();

        City city = cityService.get(cityName);
        if (city == null) {
            city = new City();
            city.setName(cityName);
            cityService.save(city);
        }
        immobility.setCity(city);

        immobilityService.save(immobility);
        immobilityDto.setId(immobility.getId());
        saveFiles(immobility, immobilityDto);
    }

    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzZ0123456789";

    private static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    @Transactional
    protected Immobility getImmobilityWithCheck(ImmobilityDto immobilityDto, User user) {
        Immobility immobility = null;
        if (immobilityDto.getId() != null) {
            immobility = immobilityService.get(immobilityDto.getId());
            if (immobility == null) {
                RuntimeException e = new IllegalStateException();
                log.error("Trying update a nonexistent object", e);
                throw e;
            }
            if (!user.equals(immobility.getOwner()) && !(user instanceof Admin)) {
                RuntimeException e = new IllegalStateException();
                log.error("Trying update immobility by not the owner or admin", e);
                throw e;
            }
        } else {
            immobility = new Immobility();
            if (!(user instanceof Client)) {
                RuntimeException e = new IllegalStateException();
                log.error("Creating immobility with by not a client", e);
                throw e;
            }
            immobility.setOwner((Client) user);
        }
        return immobility;
    }

    @Transactional
    protected void updatePhotos(Immobility immobility, ImmobilityDto immobilityDto) {
        int photoCount = 0;
        int uploadPhotoCount = 0;
        List<Long> deletedPhotoIds = null;
        if (immobilityDto.getPhotos() == null) {
            immobilityDto.setPhotos(new ArrayList<>());
        }

        if (immobility.getPhotos() != null) {
            photoCount = immobilityDto.getPhotos().size();

            List<Long> leftPhotoIdList = immobilityDto.getPhotos().stream().map(PhotoDto::getId)
                    .collect(Collectors.toList());

            List<Photo> deletedPhotos = immobility.getPhotos().stream().filter(photo -> !leftPhotoIdList.contains(photo.getId()))
                    .collect(Collectors.toList());

            immobility.getPhotos().removeAll(deletedPhotos);

            deletedPhotoIds = deletedPhotos.stream().map(Photo::getId).collect(Collectors.toList());
        }


        if (immobilityDto.getUploadedFiles() != null) {
            uploadPhotoCount = immobilityDto.getUploadedFiles().size();
        }

        if (photoCount + uploadPhotoCount > MAX_FILE_COUNT) {
            RuntimeException e = new IllegalStateException();
            log.error("Trying upload mo then " + MAX_FILE_COUNT + " files!", e);
            throw e;
        }

        if (deletedPhotoIds != null) {
            photoService.removeByList(deletedPhotoIds);
        }
    }

    @Transactional
    protected void saveFiles(Immobility immobility, ImmobilityDto immobilityDto) {
        if (immobilityDto.getUploadedFiles() != null) {
            if (immobility.getPhotos() == null) {
                immobility.setPhotos(new ArrayList<>());
            }
            for (MultipartFile file : immobilityDto.getUploadedFiles()) {
                String[] fileNameOriginSplit = file.getOriginalFilename().split("\\.", 2);
                String extension = "";
                if (fileNameOriginSplit.length > 1) {
                    extension = "." + fileNameOriginSplit[1];
                }
                String filename = null;
                File imageFile = null;
                do {
                    filename = randomAlphaNumeric(LENGTH_FILE_NAME);
                    imageFile = new File(servletContext.getRealPath("/images"), filename + extension);
                } while (imageFile.exists());
                try {
                    file.transferTo(imageFile);
                    Photo photo = new Photo();
                    photo.setPath(filename + extension);
                    photo.setImmobility(immobility);
                    photoService.save(photo);
                    immobility.getPhotos().add(photo);
                } catch (IOException e) {
                    log.error("Handle IOException", e);
                }
            }
        }
    }
}
