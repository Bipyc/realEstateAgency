package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.exception.ValueNotUniqueException;
import by.bsuir.realEstateAgency.core.model.Immobility;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

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
            log.info("Get ConstraintViolationException when save "+object.getClass().getSimpleName(), e);
            throw new ValueNotUniqueException(e);
        }
    }

    protected List<T> findAll(int offset, int limit, String query){
        return sessionFactory.getCurrentSession().createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    protected long count(String query){
        return (Long) sessionFactory.getCurrentSession()
                .createQuery(query)
                .getSingleResult();
    }
}
