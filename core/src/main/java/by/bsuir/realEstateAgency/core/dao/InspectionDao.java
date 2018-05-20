package by.bsuir.realEstateAgency.core.dao;

import by.bsuir.realEstateAgency.core.dao.common.CheckUserInterface;
import by.bsuir.realEstateAgency.core.dao.common.FindAllByUserDao;
import by.bsuir.realEstateAgency.core.model.Inspection;

import java.util.List;

public interface InspectionDao extends FindAllByUserDao<Inspection>, CheckUserInterface {
    void save(Inspection inspection);

    Inspection get(Long key);

    List<Inspection> findAll(int offset, int limit);

    long count();

    void remove(Long key);

    void removeList(List<Long> keys);

    void removeByImmobilities(List<Long> keys);

    void removegByUser(List<Long> keys);
}
