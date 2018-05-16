package by.bsuir.realEstateAgency.web.facade;

import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.web.bean.immobility.ImmobilityDto;

public interface ImmobilityFacade {

    ImmobilityDto getImmobility(Long key);

    void saveOrUpdate(ImmobilityDto immobilityDto, User user);
}
