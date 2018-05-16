package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.dao.PhotoDao;
import by.bsuir.realEstateAgency.core.model.Photo;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PhotoDaoImpl implements PhotoDao {

    static Logger log = Logger.getLogger(PhotoDaoImpl.class.getName());

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void save(Photo photo) {
        sessionFactory.getCurrentSession().saveOrUpdate(photo);
    }

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
        if (ids.size() > 0) {
            Query q = sessionFactory.getCurrentSession().createQuery("DELETE FROM Photo p WHERE p.immobility.id IN (:list)");
            q.setParameterList("list", ids);
            q.executeUpdate();
        }
    }
}
