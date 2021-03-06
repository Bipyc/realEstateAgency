package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.dao.CityDao;
import by.bsuir.realEstateAgency.core.model.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityDaoImpl extends AbstractDaoImpl<City> implements CityDao {
    @Override
    public City get(String name) {
        return (City) sessionFactory.getCurrentSession().createQuery("select c from City c where c.name = :name")
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public List<City> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select c from City c")
                .getResultList();
    }
}
