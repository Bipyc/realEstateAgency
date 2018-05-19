package by.bsuir.realEstateAgency.core.dao;

import by.bsuir.realEstateAgency.core.dao.common.CheckUserInterface;
import by.bsuir.realEstateAgency.core.model.Application;

import java.util.List;

public interface ApplicationDao extends CheckUserInterface {
    void save(Application immobility);

    Application get(Long key);

    List<Application> findAll(int offset, int limit);

    long count();

    void remove(Long key);

    /**
     * @param keys - id list
     */
    void removeList(List<Long> keys);


}
