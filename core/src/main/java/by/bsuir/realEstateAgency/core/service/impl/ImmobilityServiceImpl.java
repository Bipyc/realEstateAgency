package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.bean.SearchForm;
import by.bsuir.realEstateAgency.core.dao.ApplicationDao;
import by.bsuir.realEstateAgency.core.dao.ImmobilityDao;
import by.bsuir.realEstateAgency.core.dao.InspectionDao;
import by.bsuir.realEstateAgency.core.model.Application;
import by.bsuir.realEstateAgency.core.model.Immobility;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.ApplicationService;
import by.bsuir.realEstateAgency.core.service.ImmobilityService;
import by.bsuir.realEstateAgency.core.service.PhotoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ImmobilityServiceImpl extends AbstractService implements ImmobilityService {

    static Logger log = Logger.getLogger(ImmobilityServiceImpl.class.getName());

    @Resource
    private ImmobilityDao immobilityDao;

    @Resource
    private PhotoService photoService;

    @Resource
    private ApplicationService applicationService;

    @Override
    public void save(Immobility immobility) {
        immobilityDao.save(immobility);
    }

    @Override
    public void save(Immobility object, User user) {
        checkUser(Collections.singletonList(object), user, immobilityDao);
        save(object);
    }

    @Override
    public Immobility get(Long key) {
        return immobilityDao.get(key);
    }

    @Override
    public List<Immobility> findAll(int offset, int limit) {
        return immobilityDao.findAll(offset, limit);
    }

    @Override
    public long count() {
        return immobilityDao.count();
    }

    @Override
    public void remove(Long key, User user) {
        removeList(Arrays.asList(key), user);
    }

    @Override
    public void removeList(List<Long> keys, User user) {
        checkUser(keys, user, immobilityDao);
        removeList(keys);
    }

    @Override
    public void removeList(List<Long> keys) {
        photoService.removeByImmobilityList(keys);
        immobilityDao.removeList(keys);
    }

    @Override
    public List<Immobility> findAllBySearch(int offset, int limit, SearchForm searchForm) {
        return applicationService.findAllBySearch(offset, limit, searchForm).stream()
                .map(Application::getImmobility)
                .collect(Collectors.toList());
    }

    @Override
    public long countBySearch(SearchForm searchForm) {
        return applicationService.countBySearch(searchForm);
    }

    @Override
    public List<Immobility> findAllByUser(int offset, int limit, User user) {
        return immobilityDao.findAllByUser(offset, limit, user.getId());
    }

    @Override
    public long countByUser(User user) {
        return immobilityDao.countByUser(user.getId());
    }

    @Override
    public List<Long> getAllIdByUser(List<Long> keys) {
        return immobilityDao.getAllIdByUser(keys);
    }
}
