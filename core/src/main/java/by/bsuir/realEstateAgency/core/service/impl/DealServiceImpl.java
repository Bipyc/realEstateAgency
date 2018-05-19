package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.dao.DealDao;
import by.bsuir.realEstateAgency.core.model.Deal;
import by.bsuir.realEstateAgency.core.service.DealService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
}
