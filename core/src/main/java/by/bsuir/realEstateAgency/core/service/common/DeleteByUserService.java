package by.bsuir.realEstateAgency.core.service.common;

import by.bsuir.realEstateAgency.core.model.User;

import java.util.List;

public interface DeleteByUserService {
    void remove(Long key, User user);

    /**
     * @param keys - id list
     */
    void removeList(List<Long> keys, User user);
}
