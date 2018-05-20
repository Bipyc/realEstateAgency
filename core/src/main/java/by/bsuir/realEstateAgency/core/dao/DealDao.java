package by.bsuir.realEstateAgency.core.dao;

import by.bsuir.realEstateAgency.core.dao.common.CheckUserInterface;
import by.bsuir.realEstateAgency.core.dao.common.FindAllByUserDao;
import by.bsuir.realEstateAgency.core.model.Deal;
import by.bsuir.realEstateAgency.core.model.Immobility;

import java.util.List;

public interface DealDao extends FindAllByUserDao<Deal>, CheckUserInterface {
    void save(Deal deal);

    Deal get(Long key);

    List<Deal> findAll(int offset, int limit);

    long count();

    void remove(Long key);

    void removeList(List<Long> keys);

    void deletingApplications(List<Long> keys);

    void deletingUser(List<Long> keys);
}
