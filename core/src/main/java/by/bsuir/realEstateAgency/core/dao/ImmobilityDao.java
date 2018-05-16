package by.bsuir.realEstateAgency.core.dao;

import by.bsuir.realEstateAgency.core.model.Immobility;

import java.util.List;

public interface ImmobilityDao {
    void save(Immobility immobility);

    Immobility get(Long key);

    List<Immobility> findAll(int offset, int limit);

    long count();

    void remove(Long key);

    /**
     * @param keys - id list
     */
    void removeList(List<Long> keys);

    List<Immobility> findAllByUser(int offset, int limit, Long userId);

    long countByUser(Long userId);

    boolean checkUser(List<Long> keys, Long userId);

    /*void removeByUser(Long key, Long userId);

    /**
     * @param keys - id list
     */
    //void removeListByUser(List<Long> keys, Long userId);
}
