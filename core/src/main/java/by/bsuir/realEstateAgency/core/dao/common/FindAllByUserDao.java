package by.bsuir.realEstateAgency.core.dao.common;

import by.bsuir.realEstateAgency.core.model.Immobility;
import by.bsuir.realEstateAgency.core.model.User;

import java.util.List;

public interface FindAllByUserDao<T> {
    List<T> findAllByUser(int offset, int limit, Long userId);

    long countByUser(Long userId);
}
