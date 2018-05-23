package by.bsuir.realEstateAgency.core.dao;

import by.bsuir.realEstateAgency.core.exception.ValueNotUniqueException;
import by.bsuir.realEstateAgency.core.model.Employee;
import by.bsuir.realEstateAgency.core.model.Realtor;
import by.bsuir.realEstateAgency.core.model.User;

import java.util.List;

public interface UserDao {
    void save(User user) throws ValueNotUniqueException;

    User get(Long key);

    List<User> findAll(List<Long> keys);

    User getByLoginOrEmail(String key);

    List<User> findAll(int offset, int limit);

    long count();

    void remove(Long key);

    /**
     * @param keys - id list
     */
    void removeList(List<Long> keys);

    List<Realtor> findAllRealtor();
}
