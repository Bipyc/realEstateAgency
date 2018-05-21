package by.bsuir.realEstateAgency.core.dao;

import by.bsuir.realEstateAgency.core.model.City;

import java.util.List;

public interface CityDao {
    City get(String name);

    void save(City city);

    List<City> getAll();
}
