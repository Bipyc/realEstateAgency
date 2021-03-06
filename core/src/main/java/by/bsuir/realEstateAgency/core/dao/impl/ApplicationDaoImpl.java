package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.bean.SearchForm;
import by.bsuir.realEstateAgency.core.dao.ApplicationDao;
import by.bsuir.realEstateAgency.core.dao.DealDao;
import by.bsuir.realEstateAgency.core.model.Application;
import by.bsuir.realEstateAgency.core.model.Immobility;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Repository
public class ApplicationDaoImpl extends AbstractDaoImpl<Application> implements ApplicationDao {

    @Resource
    private DealDao dealDao;

    @Override
    public Application get(Long key) {
        return sessionFactory.getCurrentSession().get(Application.class, key);
    }

    @Override
    public List<Application> findAll(int offset, int limit) {
        return super.findAll(offset, limit, "Select a from Application a ORDER BY a.id DESC");
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
        dealDao.deletingApplications(keys);
        super.removeList(keys, "DELETE FROM Application a WHERE a.id IN (:list)");
    }

    @Override
    public boolean checkUser(List<Long> keys, Long userId) {
        return super.checkUser(keys, userId, "select count(a) from Application a Where a.id in (:list) and a.realtor.id=:userId");
    }

    @Override
    public long countBySearch(SearchForm searchForm) {
        return (Long) getQueryBySearch("select count(a) from Application a where a.status = 2", searchForm)
                .getSingleResult();
    }

    @Override
    public List<Application> findAllBySearch(int offset, int limit, SearchForm searchForm) {
        return getQueryBySearch("select a from Application a where a.status = 2", searchForm)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<Application> findAllByUser(int offset, int limit, Long userId) {
        return findAll(offset, limit, "select a from Application a where (a.realtor.id=:userId or a.immobility.owner.id=:userId) ORDER BY a.id DESC", userId);
    }

    @Override
    public long countByUser(Long userId) {
        return count("select count(a) from Application a where (a.realtor.id=:userId or a.immobility.owner.id=:userId)", userId);
    }

    @Override
    public Application findLastApplicationByImmobility(Long key) {
        return (Application) sessionFactory.getCurrentSession().createQuery("select a from Application a where a.status = 2 " +
                "and a.immobility.id = :id ORDER BY a.id DESC")
                .setParameter("id", key)
                .uniqueResult();
    }

    private Query getQueryBySearch(String start, SearchForm searchForm){
        StringBuilder sb = new StringBuilder(start);
        if(searchForm.getCityName() != null){
            sb.append(" and a.immobility.city.name = :city");
        }
        if(searchForm.getTypeImmobility() != null){
            sb.append(" and a.immobility.type = :typeImmobility");
        }
        if(searchForm.getTypeApplication() != null){
            sb.append(" and a.type.id = :typeApplication");
        }
        if(searchForm.getMinPrice() != null){
            sb.append(" and a.immobility.price >= :minPrice");
        }
        if(searchForm.getMaxPrice() != null){
            sb.append(" and a.immobility.price <= :maxPrice");
        }

        Query query =  sessionFactory.getCurrentSession().createQuery(sb.toString());
        if(searchForm.getCityName() != null){
            query.setParameter("city", searchForm.getCityName());
        }
        if(searchForm.getTypeImmobility() != null){
            query.setParameter("typeImmobility", searchForm.getTypeImmobility());
        }
        if(searchForm.getTypeApplication() != null){
            query.setParameter("typeApplication", searchForm.getTypeApplication());
        }
        if(searchForm.getMinPrice() != null){
            query.setParameter("minPrice", new BigDecimal(searchForm.getMinPrice()));
        }
        if(searchForm.getMaxPrice() != null){
            query.setParameter("maxPrice", new BigDecimal(searchForm.getMaxPrice()));
        }
        return query;
    }


    @Override
    public void deleteUser(List<Long> keys) {
        sessionFactory.getCurrentSession().createQuery("update Application a set a.status = 1" +
                " where a.realtor.id in (:list) and a.status in (0,2)")
                .setParameterList("list", keys)
                .executeUpdate();

        sessionFactory.getCurrentSession().createQuery("update Application a set a.realtor = null" +
                " where a.realtor.id in (:list) ")
                .setParameterList("list", keys)
                .executeUpdate();
    }

    @Override
    public void canceledApplicationByImmobilities(List<Long> keys) {
        sessionFactory.getCurrentSession().createQuery("update Application a set a.status = 1" +
                " where a.immobility.id in (:list) and a.status in (0,2)")
                .setParameterList("list", keys)
                .executeUpdate();

        sessionFactory.getCurrentSession().createQuery("update Application a set a.immobility = null" +
                " where a.immobility.id in (:list) ")
                .setParameterList("list", keys)
                .executeUpdate();
    }

    @Override
    public Long[] applicationCountByCity(Long id) {
        Long[] aLong = new Long[2];
        aLong[0] = (Long) sessionFactory.getCurrentSession()
                .createQuery("select count(a) from Application a where a.immobility.city.id = :id")
                .setParameter("id", id)
                .uniqueResult();

        aLong[1] = (Long) sessionFactory.getCurrentSession()
                .createQuery("select count(a) from Application a where a.immobility.city.id = :id and a.status = 3")
                .setParameter("id", id)
                .uniqueResult();

        return aLong;
    }
}
