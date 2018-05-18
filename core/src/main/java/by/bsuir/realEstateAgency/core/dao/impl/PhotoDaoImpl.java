package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.dao.PhotoDao;
import by.bsuir.realEstateAgency.core.model.Photo;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotoDaoImpl extends AbstractDaoImpl<Photo> implements PhotoDao {

    @Override
    public void removeByList(List<Long> ids) {
        if (ids.size() > 0) {
            Query q = sessionFactory.getCurrentSession().createQuery("DELETE FROM Photo p WHERE p.id IN (:list)");
            q.setParameterList("list", ids);
            q.executeUpdate();
        }
    }

    @Override
    public void removeByImmobilityList(List<Long> ids) {
        super.removeList(ids,"DELETE FROM Immobility i WHERE i.id IN (:list)");
    }
}
