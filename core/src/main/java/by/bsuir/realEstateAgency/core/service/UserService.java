package by.bsuir.realEstateAgency.core.service;

import by.bsuir.realEstateAgency.core.model.Realtor;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.common.FindAllInterface;

import java.util.List;

public interface UserService extends FindAllInterface<User> {
    void update(User user);

    void create(User user);

    User get(Long key);

    User getByLoginOrEmail(String key);

    void remove(Long key);

    /**
     * @param keys - id list
     */
    void removeList(List<Long> keys);

    List<Realtor> findAllRealtor();

    List<User> findAll(List<Long> keys);
}
