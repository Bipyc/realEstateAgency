package by.bsuir.realEstateAgency.core.dao;

import by.bsuir.realEstateAgency.core.model.Deal;

import java.util.List;

public interface DealDao {
    void save(Deal deal);

    Deal get(Long key);

    List<Deal> findAll(int offset, int limit);

    long count();

    void remove(Long key);

    void removeList(List<Long> keys);
}
