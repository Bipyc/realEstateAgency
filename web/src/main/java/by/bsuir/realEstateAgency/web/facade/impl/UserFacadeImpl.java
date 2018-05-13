package by.bsuir.realEstateAgency.web.facade.impl;

import by.bsuir.realEstateAgency.core.model.*;
import by.bsuir.realEstateAgency.core.service.UserService;
import by.bsuir.realEstateAgency.web.bean.pagedList.Pagination;
import by.bsuir.realEstateAgency.web.bean.pagedList.CheckedItem;
import by.bsuir.realEstateAgency.web.bean.user.TypeUser;
import by.bsuir.realEstateAgency.web.bean.user.UserDto;
import by.bsuir.realEstateAgency.web.facade.UserFacade;
import by.bsuir.realEstateAgency.web.service.page.PageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static by.bsuir.realEstateAgency.web.service.page.PageService.AMOUNT_PHONES_ON_PAGE;

@Service
public class UserFacadeImpl implements UserFacade {

    @Resource
    private UserService userService;

    @Override
    public UserDto getUsers(Long key) {
        User user = userService.get(key);
        UserDto userDto = new UserDto();

        if (user.getClass() == Client.class){
            userDto.setTypeUser(TypeUser.CLIENT);
        } else if (user.getClass() == Admin.class){
            userDto.setTypeUser(TypeUser.ADMIN);
        }
        else if (user.getClass() == Realtor.class){
            userDto.setTypeUser(TypeUser.REALTOR);
        }

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPatronymic(user.getPatronymic());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setDateOfBirth(user.getDateOfBirth());

        if(user.getPassport() != null) {
            PassportData passportData = user.getPassport();
            userDto.setNumber(passportData.getNumber());
            userDto.setDateOfIssue(passportData.getDateOfIssue());
            userDto.setIdentificationNumber(passportData.getIdentificationNumber());
            userDto.setAuthority(passportData.getAuthority());
        }

        if(user instanceof Employee) {
            Employee employee = (Employee) user;
            userDto.setEmploymentDate(employee.getEmploymentDate());
            userDto.setSalary(employee.getSalary());
        }

        return userDto;
    }

    @Override
    public void saveOrUpdate(UserDto userDto) {
        User user = null;

        if(userDto.getId() == null){
            switch (userDto.getTypeUser()){
                case CLIENT:
                    user = new Client();
                    break;
                case REALTOR:
                    user = new Realtor();
                    break;
                case ADMIN:
                    user = new Admin();
                    break;
            }
        }else{
            user = userService.get(userDto.getId());
        }


        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPatronymic(userDto.getPatronymic());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setLogin(userDto.getLogin());
        user.setDateOfBirth(userDto.getDateOfBirth());
        if(!userDto.getPassword().isEmpty()){
            user.setPassword(userDto.getPassword());
        }
        PassportData passportData=null;
        if(user.getPassport() != null) {
            passportData = user.getPassport();
        } else {
            passportData = new PassportData();
            passportData.setUser(user);
            user.setPassport(passportData);
        }

        passportData.setNumber(userDto.getNumber());
        passportData.setDateOfIssue(userDto.getDateOfIssue());
        passportData.setIdentificationNumber(userDto.getIdentificationNumber());
        passportData.setAuthority(userDto.getAuthority());
        if(user instanceof Employee) {
            Employee employee = (Employee) user;
            employee.setEmploymentDate(userDto.getEmploymentDate());
            employee.setSalary(userDto.getSalary());
        }
        if(user.getId() == null) {
            userService.create(user);
            userDto.setId(user.getId());
        }
        else{
            userService.update(user);
        }
    }
}
