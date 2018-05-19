package by.bsuir.realEstateAgency.web.facade;

import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.web.bean.immobility.ImmobilityDto;
import org.springframework.validation.BindingResult;

public interface ImmobilityFacade {

    ImmobilityDto getImmobility(Long key);

    boolean saveOrUpdate(ImmobilityDto immobilityDto, User user, BindingResult bindingResult);
}
