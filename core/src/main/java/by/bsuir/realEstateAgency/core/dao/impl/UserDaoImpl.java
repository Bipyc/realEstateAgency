package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.dao.DealDao;
import by.bsuir.realEstateAgency.core.dao.ImmobilityDao;
import by.bsuir.realEstateAgency.core.dao.InspectionDao;
import by.bsuir.realEstateAgency.core.dao.UserDao;
import by.bsuir.realEstateAgency.core.exception.ValueNotUniqueException;
import by.bsuir.realEstateAgency.core.model.Realtor;
import by.bsuir.realEstateAgency.core.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

    static Logger log = Logger.getLogger(UserDaoImpl.class.getName());

    @Resource
    private InspectionDao inspectionDao;

    @Resource
    private DealDao dealDao;

    @Resource
    private ImmobilityDao immobilityDao;

    @Override
    public void save(User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(user);
            if (user.getPassport() != null) {
                session.saveOrUpdate(user.getPassport());
            }
        } catch (ConstraintViolationException e) {
            log.info("Get ConstraintViolationException", e);
            throw new ValueNotUniqueException(e);
        }
    }

    @Override
    public List<User> findAll(List<Long> keys) {
        return sessionFactory.getCurrentSession().createQuery("select u from User u where u.id in (:list)")
                .setParameterList("list", keys)
                .getResultList();
    }

    @Override
    public User get(Long key) {
        return sessionFactory.getCurrentSession().get(User.class, key);
    }

    @Override
    public List<User> findAll(int offset, int limit) {
        return super.findAll(offset, limit, "Select u from User u");
    }

    @Override
    public long count() {
        return super.count("select count(u) from User u");
    }

    @Override
    public void remove(Long key) {
        removeList(Arrays.asList(key));
    }

    @Override
    public void removeList(List<Long> keys) {
        inspectionDao.removegByUser(keys);
        dealDao.deletingUser(keys);
        if (keys.size() > 0) {
            Query qPassort = sessionFactory.getCurrentSession().createQuery("DELETE FROM PassportData p WHERE p.user.id IN (:list)");
            qPassort.setParameterList("list", keys);
            qPassort.executeUpdate();
            Query q = sessionFactory.getCurrentSession().createQuery("DELETE FROM User u WHERE u.id IN (:list)");
            q.setParameterList("list", keys);
            q.executeUpdate();
        }
    }

    @Override
    public User getByLoginOrEmail(String key) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("select u from User u where u.login= :login or u.email=:email")
                .setParameter("login", key)
                .setParameter("email", key)
                .uniqueResult();
    }

    @Override
    public List<Realtor> findAllRealtor() {
        return sessionFactory.getCurrentSession()
                .createQuery("select r from Realtor r")
                .getResultList();
    }
}
