package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.dao.ImmobilityDao;
import by.bsuir.realEstateAgency.core.model.Immobility;
import by.bsuir.realEstateAgency.core.service.ImmobilityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ImmobilityServiceImpl implements ImmobilityService {

    @Resource
    private ImmobilityDao immobilityDao;

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
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}
