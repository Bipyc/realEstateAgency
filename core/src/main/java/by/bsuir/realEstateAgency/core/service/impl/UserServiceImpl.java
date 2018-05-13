package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.dao.UserDao;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    @Resource
    private UserDao userDao;

    @Override
    public void create(User user) {
        if(user.getId() != null){
            RuntimeException t = new IllegalArgumentException();
            log.error("Creating user with set id",t);
            throw t;
        }
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        if(user.getId() == null){
            RuntimeException t = new IllegalArgumentException();
            log.error("Updating user without id",t);
            throw t;
        }
        userDao.save(user);
    }

    @Override
    public User get(Long key) {
        return userDao.get(key);
    }

    @Override
    public List<User> findAll(int offset, int limit) {
        return userDao.findAll(offset, limit);
    }

    @Override
    public long count() {
        return userDao.count();
    }

    @Override
    public void remove(Long key) {
        userDao.remove(key);
    }

    @Override
    public void removeList(List<Long> keys) {
        userDao.removeList(keys);
    }
}
