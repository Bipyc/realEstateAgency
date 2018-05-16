package by.bsuir.realEstateAgency.core.service;

import by.bsuir.realEstateAgency.core.model.Photo;

import java.util.List;

public interface PhotoService {
    void save(Photo photo);

    void removeByList(List<Long> ids);

    void removeByImmobilityList(List<Long> ids);
}
