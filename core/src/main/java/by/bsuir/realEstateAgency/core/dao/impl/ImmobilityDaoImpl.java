package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.dao.ImmobilityDao;
import by.bsuir.realEstateAgency.core.exception.ValueNotUniqueException;
import by.bsuir.realEstateAgency.core.model.Immobility;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Repository
public class ImmobilityDaoImpl implements ImmobilityDao {

    static Logger log = Logger.getLogger(ImmobilityDaoImpl.class.getName());

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void save(Immobility immobility) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(immobility);
        } catch (ConstraintViolationException e) {
            log.info("Get ConstraintViolationException", e);
            throw new ValueNotUniqueException(e);
        }
    }

    @Override
    public Immobility get(Long key) {
        return sessionFactory.getCurrentSession().get(Immobility.class, key);
    }

    @Override
    public List<Immobility> findAll(int offset, int limit) {
        return sessionFactory.getCurrentSession().createQuery("Select i from Immobility i")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public long count() {
        return (Long) sessionFactory.getCurrentSession()
                .createQuery("select count(i) from Immobility i")
                .getSingleResult();
    }

    @Override
    public void remove(Long key) {
        removeList(Arrays.asList(key));
    }

    @Override
    public boolean checkUser(List<Long> keys, Long userId) {
        if (keys.size() > 0) {
            Long count = (Long) sessionFactory.getCurrentSession()
                    .createQuery("select count(i) from Immobility i Where i.id in (:list) and i.owner.id=:userId")
                    .setParameterList("list", keys)
                    .setParameter("userId", userId)
                    .getSingleResult();
            return count == keys.size();
        }
        return true;
    }

    @Override
    public void removeList(List<Long> keys) {
        if (keys.size() > 0) {
            Query query = sessionFactory.getCurrentSession().createQuery("DELETE FROM Immobility i WHERE i.id IN (:list)");
            query.setParameterList("list", keys);
            query.executeUpdate();
        }
    }

    @Override
    public List<Immobility> findAllByUser(int offset, int limit, Long userId) {
        return null;
    }

    @Override
    public long countByUser(Long userId) {
        return 0;
    }
}
