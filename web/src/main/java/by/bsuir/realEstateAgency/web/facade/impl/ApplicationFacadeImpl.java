package by.bsuir.realEstateAgency.web.facade.impl;

import by.bsuir.realEstateAgency.core.model.*;
import by.bsuir.realEstateAgency.core.service.ApplicationService;
import by.bsuir.realEstateAgency.core.service.ImmobilityService;
import by.bsuir.realEstateAgency.core.service.TypeApplicationService;
import by.bsuir.realEstateAgency.core.service.UserService;
import by.bsuir.realEstateAgency.web.bean.application.ApplicationDto;
import by.bsuir.realEstateAgency.web.exceptions.NotFoundException;
import by.bsuir.realEstateAgency.web.facade.ApplicationFacade;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ApplicationFacadeImpl implements ApplicationFacade {

    static Logger log = Logger.getLogger(ApplicationFacadeImpl.class.getName());

    @Resource
    private ApplicationService applicationService;

    @Resource
    private UserService userService;

    @Resource
    private TypeApplicationService typeApplicationService;

    @Resource
    private ImmobilityService immobilityService;

    @Override
    public ApplicationDto getApplication(Long key) {
        Application application = applicationService.get(key);
        if(application == null){
            return null;
        }

        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setId(application.getId());
        applicationDto.setDate(application.getDate());
        if(application.getRealtor() != null){
            applicationDto.setRealtorName(application.getRealtor().getLogin());
        }
        applicationDto.setImmobilityId(application.getImmobility().getId());
        applicationDto.setTypeId(application.getType().getId());
        applicationDto.setStatus(application.getStatus());
        return applicationDto;
    }

    @Override
    public boolean saveOrUpdate(ApplicationDto applicationDto, User user, BindingResult bindingResult) {
        Application application = null;
        if (applicationDto.getId() != null) {
            application = applicationService.get(applicationDto.getId());
            if (application == null) {
                RuntimeException e = new IllegalStateException();
                log.error("Trying update a nonexistent object", e);
                throw e;
            }
        } else {
            application = new Application();
            application.setDate(new Date());
        }

        application.setId(applicationDto.getId());
        application.setStatus(applicationDto.getStatus());
        User realtor = userService.getByLoginOrEmail(applicationDto.getRealtorName());
        if(realtor == null || !(realtor instanceof Realtor)){
            bindingResult.addError(new FieldError("applicationDto", "realtorName", applicationDto.getRealtorName(), false,
                    new String[]{"NotFound.applicationDto.realtorName"}, null, "realtor not found"));

        }
        else{
            application.setRealtor((Realtor) realtor);
        }

        Immobility immobility = immobilityService.get(applicationDto.getImmobilityId());
        if(immobility == null){
            bindingResult.addError(new FieldError("applicationDto", "immobilityId", applicationDto.getImmobilityId(), false,
                    new String[]{"NotFound.applicationDto.immobilityId"}, null, "immobility not found"));

        }
        else {
            application.setImmobility(immobility);
        }

        TypeApplication typeApplication = typeApplicationService.get(applicationDto.getTypeId());
        if(typeApplication == null){
            bindingResult.addError(new FieldError("typeId", "typeId", null, false,
                    new String[]{"NotFound.applicationDto.immobilityId"}, null, "type not found"));

        }
        else {
            application.setType(typeApplication);
        }
        if(!bindingResult.hasErrors()) {
            applicationService.save(application);
            applicationDto.setId(application.getId());
        }
        return bindingResult.hasErrors();
    }
}
