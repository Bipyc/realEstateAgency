package by.bsuir.realEstateAgency.core.dao.user;

import by.bsuir.realEstateAgency.core.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User get(Long key) {
        User user = sessionFactory.getCurrentSession().get(User.class, key);
        return user;
    }
}
