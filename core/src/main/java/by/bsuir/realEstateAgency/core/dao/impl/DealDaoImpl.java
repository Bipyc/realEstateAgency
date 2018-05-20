package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.dao.DealDao;
import by.bsuir.realEstateAgency.core.model.Deal;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class DealDaoImpl extends AbstractDaoImpl<Deal> implements DealDao {

    @Override
    public Deal get(Long key) {
        return sessionFactory.getCurrentSession().get(Deal.class, key);
    }

    @Override
    public List<Deal> findAll(int offset, int limit) {
        return super.findAll(offset, limit, "Select d from Deal d ORDER BY d.id DESC");
    }

    @Override
    public long count() {
        return super.count("select count(d) from Deal d");
    }

    @Override
    public void remove(Long key) {
        removeList(Arrays.asList(key));
    }

    @Override
    public void removeList(List<Long> keys) {
        super.removeList(keys, "DELETE FROM Deal d WHERE d.id IN (:list)");
    }

    @Override
    public void deletingApplications(List<Long> keys) {
        sessionFactory.getCurrentSession().createQuery("update Deal d set d.application = null" +
                " where d.application.id in (:list)")
                .setParameterList("list", keys)
                .executeUpdate();
    }

    @Override
    public void deletingUser(List<Long> keys) {
        sessionFactory.getCurrentSession().createQuery("update Deal d set d.client = null" +
                " where d.client.id in (:list)")
                .setParameterList("list", keys)
                .executeUpdate();
    }

    @Override
    public List<Deal> findAllByUser(int offset, int limit, Long userId) {
        return findAll(offset, limit, "select d from Deal d Where d.application.realtor.id=:userId or d.client.id=:userId ORDER BY d.id DESC", userId);
    }

    @Override
    public long countByUser(Long userId) {
        return count("select count(d) from Deal d Where d.application.realtor.id=:userId or d.client.id=:userId ", userId);
    }

    @Override
    public boolean checkUser(List<Long> keys, Long userId) {
        return checkUser(keys, userId, "select count(d) from Deal d Where d.id in (:list) and d.application.realtor.id=:userId");
    }
}
