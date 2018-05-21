package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.dao.InspectionDao;
import by.bsuir.realEstateAgency.core.dao.UserDao;
import by.bsuir.realEstateAgency.core.model.Realtor;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.ImmobilityService;
import by.bsuir.realEstateAgency.core.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private ImmobilityService immobilityService;

    @Resource
    private UserDao userDao;

    @Override
    public void create(User user) {
        if (user.getId() != null) {
            RuntimeException t = new IllegalArgumentException();
            log.error("Creating user with set id", t);
            throw t;
        }
        encryptPassword(user);
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        if (user.getId() == null) {
            RuntimeException t = new IllegalArgumentException();
            log.error("Updating user without id", t);
            throw t;
        }
        encryptPassword(user);
        userDao.save(user);
    }

    private void encryptPassword(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
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
        removeList(Collections.singletonList(key));
    }

    @Override
    public void removeList(List<Long> keys) {
        immobilityService.removeList(immobilityService.getAllIdByUser(keys));
        userDao.removeList(keys);
    }

    @Override
    public User getByLoginOrEmail(String key) {
        return userDao.getByLoginOrEmail(key);
    }

    @Override
    public List<Realtor> findAllRealtor() {
        return userDao.findAllRealtor();
    }
}
