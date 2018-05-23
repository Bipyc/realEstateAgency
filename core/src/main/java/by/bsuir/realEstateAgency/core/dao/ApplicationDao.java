package by.bsuir.realEstateAgency.core.dao;

import by.bsuir.realEstateAgency.core.bean.SearchForm;
import by.bsuir.realEstateAgency.core.dao.common.CheckUserInterface;
import by.bsuir.realEstateAgency.core.dao.common.FindAllByUserDao;
import by.bsuir.realEstateAgency.core.model.Application;
import by.bsuir.realEstateAgency.core.model.Immobility;

import java.util.List;

public interface ApplicationDao extends FindAllByUserDao<Application>, CheckUserInterface {
    void save(Application immobility);

    Application get(Long key);

    List<Application> findAll(int offset, int limit);

    long count();

    void remove(Long key);

    /**
     * @param keys - id list
     */
    void removeList(List<Long> keys);

    List<Application> findAllBySearch(int offset, int limit, SearchForm searchForm);

    long countBySearch(SearchForm searchForm);

    Application findLastApplicationByImmobility(Long key);

    void canceledApplicationByImmobilities(List<Long> keys);

    Long[] applicationCountByCity(Long id);

    void deleteUser(List<Long> keys);
}
