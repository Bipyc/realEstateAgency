package by.bsuir.realEstateAgency.core.service;

import by.bsuir.realEstateAgency.core.model.City;

public interface CityService {
    City get(String name);

    void save(City city);
}
