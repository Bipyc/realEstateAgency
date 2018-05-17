package by.bsuir.realEstateAgency.web.facade.impl;

import by.bsuir.realEstateAgency.core.model.*;
import by.bsuir.realEstateAgency.core.service.TypeApplicationService;
import by.bsuir.realEstateAgency.web.bean.application.TypeApplicationDto;
import by.bsuir.realEstateAgency.web.bean.immobility.ImmobilityDto;
import by.bsuir.realEstateAgency.web.facade.TypeApplicationFacade;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TypeApplicationFacadeImpl implements TypeApplicationFacade {

    static Logger log = Logger.getLogger(ImmobilityFacadeImpl.class.getName());

    @Resource
    private TypeApplicationService typeApplicationService;

    @Override
    public TypeApplicationDto getTypeApplication(Long key) {
        TypeApplication typeApplication = typeApplicationService.get(key);
        if(typeApplication == null){
            return null;
        }
        TypeApplicationDto typeApplicationDto = new TypeApplicationDto();
        typeApplicationDto.setId(typeApplication.getId());
        typeApplicationDto.setCommission(typeApplication.getCommission());
        typeApplicationDto.setName(typeApplication.getName());
        return typeApplicationDto;
    }

    @Override
    public void saveOrUpdate(TypeApplicationDto typeApplicationDto) {
        TypeApplication typeApplication = getTypeApplication(typeApplicationDto);
        typeApplication.setId(typeApplicationDto.getId());
        typeApplication.setCommission(typeApplicationDto.getCommission());
        typeApplication.setName(typeApplicationDto.getName());
        typeApplicationService.save(typeApplication);
        typeApplicationDto.setId(typeApplication.getId());
    }

    @Transactional
    protected TypeApplication getTypeApplication(TypeApplicationDto typeApplicationDto) {
        TypeApplication typeApplication = null;
        if (typeApplicationDto.getId() != null) {
            typeApplication = typeApplicationService.get(typeApplicationDto.getId());
            if (typeApplication == null) {
                RuntimeException e = new IllegalStateException();
                log.error("Trying update a nonexistent object", e);
                throw e;
            }
        } else {
            typeApplication = new TypeApplication();
        }
        return typeApplication;
    }
}
