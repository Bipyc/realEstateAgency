package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.dao.ImmobilityDao;
import by.bsuir.realEstateAgency.core.model.Immobility;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ImmobilityDaoImpl  extends AbstractDaoImpl<Immobility> implements ImmobilityDao {

    @Override
    public Immobility get(Long key) {
        return sessionFactory.getCurrentSession().get(Immobility.class, key);
    }

    @Override
    public List<Immobility> findAll(int offset, int limit) {
        return super.findAll(offset, limit, "Select i from Immobility i");
    }

    @Override
    public long count() {
        return super.count("select count(i) from Immobility i");
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
