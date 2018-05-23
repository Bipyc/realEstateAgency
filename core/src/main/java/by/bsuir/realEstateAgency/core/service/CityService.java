package by.bsuir.realEstateAgency.core.service;

import by.bsuir.realEstateAgency.core.model.City;

import java.util.List;

public interface CityService {
    City get(String name);

    void save(City city);

    List<City> getAll();
}
