package by.bsuir.realEstateAgency.core.dao;

import by.bsuir.realEstateAgency.core.dao.common.CheckUserInterface;
import by.bsuir.realEstateAgency.core.dao.common.FindAllByUserDao;
import by.bsuir.realEstateAgency.core.model.Immobility;

import java.util.List;

public interface ImmobilityDao extends FindAllByUserDao<Immobility>, CheckUserInterface {
    void save(Immobility immobility);

    Immobility get(Long key);

    List<Immobility> findAll(int offset, int limit);

    long count();

    void remove(Long key);

    /**
     * @param keys - id list
     */
    void removeList(List<Long> keys);

    boolean checkUser(List<Long> keys, Long userId);
}
