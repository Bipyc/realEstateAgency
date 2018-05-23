package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.exception.ValueNotUniqueException;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import java.util.List;

public class AbstractDaoImpl<T> {
    static Logger log = Logger.getLogger(AbstractDaoImpl.class.getName());

    @Resource
    protected SessionFactory sessionFactory;

    public void save(T object) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(object);
        } catch (ConstraintViolationException e) {
            log.info("Get ConstraintViolationException when save " + object.getClass().getSimpleName(), e);
            throw new ValueNotUniqueException(e);
        }
    }

    protected List<T> findAll(int offset, int limit, String query, Long userId) {
        return sessionFactory.getCurrentSession().createQuery(query)
                .setParameter("userId", userId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    protected long count(String query, Long userId) {
        return (Long) sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    protected List<T> findAll(int offset, int limit, String query) {
        return sessionFactory.getCurrentSession().createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    protected long count(String query) {
        return (Long) sessionFactory.getCurrentSession()
                .createQuery(query)
                .getSingleResult();
    }

    public void removeList(List<Long> keys, String queryString) {
        if (keys.size() > 0) {
            Query query = sessionFactory.getCurrentSession().createQuery(queryString);
            query.setParameterList("list", keys);
            query.executeUpdate();
        }
    }

    public boolean checkUser(List<Long> keys, Long userId, String query) {
        if (keys.size() > 0) {
            Long count = (Long) sessionFactory.getCurrentSession()
                    .createQuery(query)
                    .setParameterList("list", keys)
                    .setParameter("userId", userId)
                    .getSingleResult();
            return count == keys.size();
        }
        return true;
    }
}
