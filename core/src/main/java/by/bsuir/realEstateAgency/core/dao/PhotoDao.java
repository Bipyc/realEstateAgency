package by.bsuir.realEstateAgency.core.dao;

import by.bsuir.realEstateAgency.core.model.Photo;

import java.util.List;

public interface PhotoDao {
    void save(Photo photo);

    void removeByList(List<Long> ids);

    void removeByImmobilityList(List<Long> ids);
}
