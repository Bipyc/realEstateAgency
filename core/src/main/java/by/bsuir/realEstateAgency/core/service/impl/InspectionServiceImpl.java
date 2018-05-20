package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.dao.InspectionDao;
import by.bsuir.realEstateAgency.core.model.Immobility;
import by.bsuir.realEstateAgency.core.model.Inspection;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.InspectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class InspectionServiceImpl extends AbstractService<Inspection> implements InspectionService {

    @Resource
    private InspectionDao inspectionDao;

    @Override
    public void save(Inspection inspection, User user) {
        if (inspection.getId() != null) {
            checkUser(Collections.singletonList(inspection.getId()), user, inspectionDao);
        }
        inspectionDao.save(inspection);
    }

    @Override
    public Inspection get(Long key) {
        return inspectionDao.get(key);
    }

    @Override
    public void remove(Long key, User user) {
        removeList(Collections.singletonList(key), user);
    }

    @Override
    public void removeList(List<Long> keys, User user) {
        checkUser(keys, user, inspectionDao);
        inspectionDao.removeList(keys);
    }

    @Override
    public List<Inspection> findAll(int offset, int limit) {
        return inspectionDao.findAll(offset, limit);
    }

    @Override
    public long count() {
        return inspectionDao.count();
    }

    @Override
    public List<Inspection> findAllByUser(int offset, int limit, User user) {
        return inspectionDao.findAllByUser(offset, limit, user.getId());
    }

    @Override
    public long countByUser(User user) {
        return inspectionDao.countByUser(user.getId());
    }
}
