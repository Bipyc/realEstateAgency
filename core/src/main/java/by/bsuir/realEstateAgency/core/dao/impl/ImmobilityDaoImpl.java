package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.dao.ImmobilityDao;
import by.bsuir.realEstateAgency.core.exception.ValueNotUniqueException;
import by.bsuir.realEstateAgency.core.model.Immobility;
import by.bsuir.realEstateAgency.core.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ImmobilityDaoImpl implements ImmobilityDao{

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
    public List<User> findAll(int offset, int limit) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void remove(Long key) {

    }

    @Override
    public void removeList(List<Long> keys) {

    }

    @Override
    public List<User> findAllByUser(int offset, int limit, Long userId) {
        return null;
    }

    @Override
    public long countByUser(Long userId) {
        return 0;
    }

    @Override
    public void removeByUser(Long key, Long userId) {

    }

    @Override
    public void removeListByUser(List<Long> keys, Long userId) {

    }
}
