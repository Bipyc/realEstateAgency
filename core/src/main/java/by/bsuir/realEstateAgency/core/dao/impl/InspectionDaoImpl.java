package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.dao.InspectionDao;
import by.bsuir.realEstateAgency.core.model.Inspection;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class InspectionDaoImpl extends AbstractDaoImpl<Inspection> implements InspectionDao {

    @Override
    public Inspection get(Long key) {
        return sessionFactory.getCurrentSession().get(Inspection.class, key);
    }

    @Override
    public List<Inspection> findAll(int offset, int limit) {
        return super.findAll(offset, limit, "Select i from Inspection i ORDER BY i.id DESC");
    }

    @Override
    public long count() {
        return super.count("select count(i) from Inspection i");
    }

    @Override
    public void remove(Long key) {
        removeList(Arrays.asList(key));
    }

    @Override
    public void removeList(List<Long> keys) {
        super.removeList(keys, "DELETE FROM Inspection i WHERE i.id IN (:list)");
    }

    @Override
    public boolean checkUser(List<Long> keys, Long userId) {
        return super.checkUser(keys, userId,
                "select count(i) from Inspection i Where i.id in (:list) and (i.realtor.id=:userId or i.client.id=:userId)");
    }

    @Override
    public List<Inspection> findAllByUser(int offset, int limit, Long userId) {
        return super.findAll(offset, limit, "select i from Inspection i Where (i.realtor.id=:userId or i.client.id=:userId) ORDER BY i.id DESC", userId);
    }

    @Override
    public long countByUser(Long userId) {
        return super.count("select count(i) from Inspection i Where (i.realtor.id=:userId or i.client.id=:userId)", userId);
    }

    @Override
    public void removeByImmobilities(List<Long> keys) {
        super.removeList(keys, "DELETE FROM Inspection i WHERE i.immobility.id IN (:list)");
    }

    @Override
    public void removegByUser(List<Long> keys) {
        super.removeList(keys, "DELETE FROM Inspection i WHERE i.realtor.id IN (:list) or i.client.id IN (:list)");
    }

    @Override
    public List<Inspection> getAllToday(Long userId) {
        return super.findAll(0, 1000,"select i from Inspection i" +
                " Where (i.realtor.id=:userId or i.client.id=:userId) and i.date=CURRENT_DATE ORDER BY i.time", userId);
    }
}
