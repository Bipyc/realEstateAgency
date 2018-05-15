package by.bsuir.realEstateAgency.core.dao;

import by.bsuir.realEstateAgency.core.exception.ValueNotUniqueException;
import by.bsuir.realEstateAgency.core.model.Immobility;
import by.bsuir.realEstateAgency.core.model.User;

import java.util.List;

public interface ImmobilityDao {
    void save(Immobility immobility);

    Immobility get(Long key);

    List<User> findAll(int offset, int limit);

    long count();

    void remove(Long key);

    /**
     * @param keys - id list
     */
    void removeList(List<Long> keys);

    List<User> findAllByUser(int offset, int limit, Long userId);

    long countByUser(Long userId);

    void removeByUser(Long key, Long userId);

    /**
     *  @param keys - id list
     */
    void removeListByUser(List<Long> keys, Long userId);
}
