package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.bean.SearchForm;
import by.bsuir.realEstateAgency.core.dao.ApplicationDao;
import by.bsuir.realEstateAgency.core.model.Application;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.ApplicationService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class ApplicationServiceImpl extends AbstractService implements ApplicationService {

    static Logger log = Logger.getLogger(ImmobilityServiceImpl.class.getName());

    @Resource
    private ApplicationDao applicationDao;

    @Override
    public void save(Application application) {
        applicationDao.save(application);
    }

    @Override
    public void save(Application object, User user) {
        checkUser(Collections.singletonList(object), user, applicationDao);
        save(object);
    }

    @Override
    public Application get(Long key) {
        return applicationDao.get(key);
    }

    @Override
    public void remove(Long key, User user) {
        removeList(Arrays.asList(key), user);
    }

    @Override
    public void removeList(List<Long> keys, User user) {
        checkUser(keys, user, applicationDao);
        applicationDao.removeList(keys);
    }

    @Override
    public List<Application> findAll(int offset, int limit) {
        return applicationDao.findAll(offset, limit);
    }

    @Override
    public long count() {
        return applicationDao.count();
    }

    @Override
    public List<Application> findAllBySearch(int offset, int limit, SearchForm searchForm) {
        return applicationDao.findAllBySearch(offset, limit, searchForm);
    }

    @Override
    public long countBySearch(SearchForm searchForm) {
        return applicationDao.countBySearch(searchForm);
    }

    @Override
    public Application findLastApplicationByImmobility(Long key) {
        return applicationDao.findLastApplicationByImmobility(key);
    }

    @Override
    public List<Application> findAllByUser(int offset, int limit, User user) {
        return applicationDao.findAllByUser(offset, limit, user.getId());
    }

    @Override
    public long countByUser(User user) {
        return applicationDao.countByUser(user.getId());
    }

    @Override
    public Long[] applicationCountByCity(Long id) {
        return applicationDao.applicationCountByCity(id);
    }
}
