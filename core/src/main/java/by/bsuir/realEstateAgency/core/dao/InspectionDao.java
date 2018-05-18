package by.bsuir.realEstateAgency.core.dao;

import by.bsuir.realEstateAgency.core.dao.common.CheckUserInterface;
import by.bsuir.realEstateAgency.core.model.Deal;
import by.bsuir.realEstateAgency.core.model.Inspection;

import java.util.List;

public interface InspectionDao extends CheckUserInterface {
    void save(Inspection inspection);

    Inspection get(Long key);

    List<Inspection> findAll(int offset, int limit);

    long count();

    void remove(Long key);

    void removeList(List<Long> keys);
}
