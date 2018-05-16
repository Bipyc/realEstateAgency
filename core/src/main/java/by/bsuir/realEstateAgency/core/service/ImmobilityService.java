package by.bsuir.realEstateAgency.core.service;

import by.bsuir.realEstateAgency.core.model.Immobility;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.common.FindAllInterface;

import java.util.List;

public interface ImmobilityService extends FindAllInterface {
    void save(Immobility immobility);

    Immobility get(Long key);

    @Override
    List<Immobility> findAll(int offset, int limit);

    @Override
    long count();

    void remove(Long key, User user);

    /**
     * @param keys - id list
     */
    void removeList(List<Long> keys, User user);
}
