package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.dao.PhotoDao;
import by.bsuir.realEstateAgency.core.model.Photo;
import by.bsuir.realEstateAgency.core.service.PhotoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    @Resource
    private PhotoDao photoDao;

    @Override
    public void save(Photo photo) {
        photoDao.save(photo);
    }

    @Override
    public void removeByList(List<Long> ids) {
        photoDao.removeByList(ids);
    }

    @Override
    public void removeByImmobilityList(List<Long> ids) {
        photoDao.removeByImmobilityList(ids);
    }
}
