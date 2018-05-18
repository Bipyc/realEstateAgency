package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.dao.ApplicationDao;
import by.bsuir.realEstateAgency.core.model.Application;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Repository
public class ApplicationDaoImpl extends AbstractDaoImpl<Application> implements ApplicationDao {
    @Override
    public Application get(Long key) {
        return sessionFactory.getCurrentSession().get(Application.class, key);
    }

    @Override
    public List<Application> findAll(int offset, int limit) {
        return super.findAll(offset, limit, "Select a from Application a");
    }

    @Override
    public long count() {
        return super.count("select count(a) from Application a");
    }

    @Override
    public void remove(Long key) {
        removeList(Arrays.asList(key));
    }

    @Override
    public void removeList(List<Long> keys) {
        if (keys.size() > 0) {
            Query query = sessionFactory.getCurrentSession().createQuery("DELETE FROM Application a WHERE a.id IN (:list)");
            query.setParameterList("list", keys);
            query.executeUpdate();
        }
    }

    @Override
    public boolean checkUser(List<Long> keys, Long userId) {
        if (keys.size() > 0) {
            Long count = (Long) sessionFactory.getCurrentSession()
                    .createQuery("select count(a) from Application a Where a.id in (:list) and a.realtor.id=:userId")
                    .setParameterList("list", keys)
                    .setParameter("userId", userId)
                    .getSingleResult();
            return count == keys.size();
        }
        return true;
    }
}
