package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.dao.TypeApplicationDao;
import by.bsuir.realEstateAgency.core.model.TypeApplication;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.TypeApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class TypeApplicationServiceImpl implements TypeApplicationService {

    @Resource
    private TypeApplicationDao typeApplicationDao;

    @Override
    public void save(TypeApplication typeApplication) {
        typeApplicationDao.save(typeApplication);
    }

    @Override
    public TypeApplication get(Long key) {
        return typeApplicationDao.get(key);
    }

    @Override
    public void remove(Long key) {
        typeApplicationDao.remove(key);
    }

    @Override
    public List<TypeApplication> findAll(int offset, int limit) {
        return typeApplicationDao.findAll(offset, limit);
    }

    @Override
    public long count() {
        return typeApplicationDao.count();
    }
}
