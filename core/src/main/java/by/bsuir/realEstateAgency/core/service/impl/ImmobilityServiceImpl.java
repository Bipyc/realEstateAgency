package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.dao.ImmobilityDao;
import by.bsuir.realEstateAgency.core.model.Admin;
import by.bsuir.realEstateAgency.core.model.Immobility;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.ImmobilityService;
import by.bsuir.realEstateAgency.core.service.PhotoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ImmobilityServiceImpl implements ImmobilityService {

    static Logger log = Logger.getLogger(ImmobilityServiceImpl.class.getName());

    @Resource
    private ImmobilityDao immobilityDao;

    @Resource
    private PhotoService photoService;

    @Override
    public void save(Immobility immobility) {
        immobilityDao.save(immobility);
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
        removeList(Arrays.asList(key),user);
    }

    @Override
    public void removeList(List<Long> keys, User user) {
        if(!(user instanceof Admin)){
            if (!immobilityDao.checkUser(keys, user.getId())) {
                RuntimeException e = new IllegalStateException();
                log.error("Trying remove immobility by not the owner or admin", e);
                throw e;
            }
        }
        photoService.removeByImmobilityList(keys);
        immobilityDao.removeList(keys);
    }
}
