package by.bsuir.realEstateAgency.core.dao.user;

import by.bsuir.realEstateAgency.core.model.User;

public interface UserDao {
    void add(User user);

    User get(Long key);
}
