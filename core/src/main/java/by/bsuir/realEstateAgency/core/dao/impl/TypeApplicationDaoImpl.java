package by.bsuir.realEstateAgency.core.dao.impl;

import by.bsuir.realEstateAgency.core.dao.TypeApplicationDao;
import by.bsuir.realEstateAgency.core.exception.LinkedObjectDeletingException;
import by.bsuir.realEstateAgency.core.exception.ValueNotUniqueException;
import by.bsuir.realEstateAgency.core.model.TypeApplication;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class TypeApplicationDaoImpl extends AbstractDaoImpl<TypeApplication> implements TypeApplicationDao {

    static Logger log = Logger.getLogger(TypeApplicationDaoImpl.class.getName());

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void save(TypeApplication typeApplication) throws ValueNotUniqueException {
        super.save(typeApplication);
    }

    @Override
    public TypeApplication get(Long key) {
        return sessionFactory.getCurrentSession().get(TypeApplication.class, key);
    }

    @Override
    public List<TypeApplication> findAll(int offset, int limit) {
        return super.findAll(offset, limit, "Select t from TypeApplication t");
    }

    @Override
    public long count() {
        return super.count("select count(t) from TypeApplication t");
    }

    @Override
    public void remove(Long key) {
        try {
            Query q = sessionFactory.getCurrentSession().createQuery("DELETE FROM TypeApplication t WHERE t.id=:id");
            q.setParameter("id", key);
            q.executeUpdate();
        } catch (ConstraintViolationException e) {
            log.error("Trying delete linked object");
            throw new LinkedObjectDeletingException(e);
        }
    }
}
