package by.bsuir.realEstateAgency.web.facade;

import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.web.bean.application.ApplicationDto;
import org.springframework.validation.BindingResult;

public interface ApplicationFacade {
    ApplicationDto getApplication(Long key);

    public boolean saveOrUpdate(ApplicationDto applicationDto, User user, BindingResult bindingResult);
}
