package by.bsuir.realEstateAgency.core.service.common;

import by.bsuir.realEstateAgency.core.model.Immobility;
import by.bsuir.realEstateAgency.core.model.User;

import java.util.List;

public interface FindAllByUserService<T> {
    void save(T object, User user);

    List<T> findAllByUser(int offset, int limit, User user);

    long countByUser(User user);


}
