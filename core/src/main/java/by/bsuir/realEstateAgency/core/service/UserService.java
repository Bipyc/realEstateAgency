package by.bsuir.realEstateAgency.core.service;

import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.common.FindAllInterface;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends FindAllInterface {
    void update(User user);

    void create(User user);

    User get(Long key);

    @Override
    List<User> findAll(int offset, int limit);

    @Override
    long count();

    void remove(Long key);

    /**
     *
     * @param keys - id list
     */
    void removeList(List<Long> keys);
}
