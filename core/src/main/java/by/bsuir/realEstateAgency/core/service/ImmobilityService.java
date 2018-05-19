package by.bsuir.realEstateAgency.core.service;

import by.bsuir.realEstateAgency.core.model.Immobility;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.common.FindAllBySearchInterface;
import by.bsuir.realEstateAgency.core.service.common.FindAllInterface;

import java.util.List;

public interface ImmobilityService extends FindAllInterface<Immobility>, FindAllBySearchInterface<Immobility> {
    void save(Immobility immobility);

    Immobility get(Long key);

    void remove(Long key, User user);

    /**
     * @param keys - id list
     */
    void removeList(List<Long> keys, User user);
}
