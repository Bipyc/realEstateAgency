package by.bsuir.realEstateAgency.web.facade;

import by.bsuir.realEstateAgency.web.bean.user.UserDto;

public interface UserFacade {

    UserDto getUsers(Long key);

    void saveOrUpdate(UserDto userDto);

}
