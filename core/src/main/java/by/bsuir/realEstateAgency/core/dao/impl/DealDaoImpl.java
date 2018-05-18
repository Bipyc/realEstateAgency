package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.dao.DealDao;
import by.bsuir.realEstateAgency.core.model.Deal;

import java.util.Arrays;
import java.util.List;

public class DealDaoImpl extends AbstractDaoImpl<Deal> implements DealDao {

    @Override
    public Deal get(Long key) {
        return sessionFactory.getCurrentSession().get(Deal.class, key);
    }

    @Override
    public List<Deal> findAll(int offset, int limit) {
        return super.findAll(offset, limit, "Select d from Deal d");
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
}
