package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.dao.DealDao;
import by.bsuir.realEstateAgency.core.model.Deal;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.DealService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class DealServiceImpl extends AbstractService<Deal> implements DealService {

    @Resource
    private DealDao dealDao;

    @Override
    public void save(Deal deal) {
        dealDao.save(deal);
    }

    @Override
    public Deal get(Long key) {
        return dealDao.get(key);
    }

    @Override
    public void remove(Long key) {
        dealDao.remove(key);
    }

    @Override
    public void removeList(List<Long> keys) {
        dealDao.removeList(keys);
    }

    @Override
    public List<Deal> findAll(int offset, int limit) {
        return dealDao.findAll(offset, limit);
    }

    @Override
    public long count() {
        return dealDao.count();
    }

    @Override
    public void save(Deal object, User user) {
        checkUser(Collections.singletonList(object.getId()), user, dealDao);
        save(object);
    }

    @Override
    public List<Deal> findAllByUser(int offset, int limit, User user) {
        return dealDao.findAllByUser(offset, limit, user.getId());
    }

    @Override
    public long countByUser(User user) {
        return dealDao.countByUser(user.getId());
    }
}
