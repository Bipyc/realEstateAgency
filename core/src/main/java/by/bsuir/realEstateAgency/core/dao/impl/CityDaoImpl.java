package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.dao.CityDao;
import by.bsuir.realEstateAgency.core.exception.ValueNotUniqueException;
import by.bsuir.realEstateAgency.core.model.City;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CityDaoImpl implements CityDao {

    static Logger log = Logger.getLogger(CityDaoImpl.class.getName());

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public City get(String name) {
        return (City) sessionFactory.getCurrentSession().createQuery("select c from City c where c.name = :name")
                .setParameter("name",name)
                .uniqueResult();
    }

    @Override
    public void save(City city) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(city);
        } catch (ConstraintViolationException e) {
            log.info("Get ConstraintViolationException", e);
            throw new ValueNotUniqueException(e);
        }
    }
}
