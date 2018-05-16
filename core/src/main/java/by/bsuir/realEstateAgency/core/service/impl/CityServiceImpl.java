package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.dao.CityDao;
import by.bsuir.realEstateAgency.core.model.City;
import by.bsuir.realEstateAgency.core.service.CityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    @Resource
    private CityDao cityDao;

    @Override
    public City get(String name) {
        return cityDao.get(name);
    }

    @Override
    public void save(City city) {
        cityDao.save(city);
    }
}
