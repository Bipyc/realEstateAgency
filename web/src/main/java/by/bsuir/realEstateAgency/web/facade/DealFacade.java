package by.bsuir.realEstateAgency.web.facade;

import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.web.bean.DealDto;
import by.bsuir.realEstateAgency.web.bean.application.ApplicationDto;
import org.springframework.validation.BindingResult;

public interface DealFacade {
    DealDto getDeal(Long key);

    public boolean saveOrUpdate(DealDto dealDto, User user, BindingResult bindingResult);
}
