package by.bsuir.realEstateAgency.web.controller.panel;

import by.bsuir.realEstateAgency.core.exception.ValueNotUniqueException;
import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.UserService;
import by.bsuir.realEstateAgency.web.bean.pagedList.CheckedItem;
import by.bsuir.realEstateAgency.web.bean.pagedList.CheckedList;
import by.bsuir.realEstateAgency.web.bean.user.TypeUser;
import by.bsuir.realEstateAgency.web.bean.user.UserDto;
import by.bsuir.realEstateAgency.web.exceptions.BadRequestException;
import by.bsuir.realEstateAgency.web.exceptions.NotFoundException;
import by.bsuir.realEstateAgency.web.facade.UserFacade;
import by.bsuir.realEstateAgency.web.service.page.PageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/panel/users")
public class UserController {

    static Logger log = Logger.getLogger(UserController.class.getName());

    private static final String PAGE_NUMBER_REQUEST_PARAM = "page";

    private static final String PAGED_LIST_ATTRIBUTE = "pagedList";

    private static final String USER_ATTRIBUTE = "userDto";

    private static final String CREATE_USER_ATTRIBUTE = "createUser";

    private static final String TYPE_USER_ATTRIBUTE = "typeUser";

    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";


    @Resource
    private PageService pageService;

    @Resource
    private UserService userService;

    @Resource
    private UserFacade userFacade;

    @GetMapping
    private String getUsers(@RequestParam(name = PAGE_NUMBER_REQUEST_PARAM, defaultValue = "1") int pageNumber,
                            @RequestParam(name = "count", defaultValue = "10") int countOnPage,
                            Model model) {
        model.addAttribute(PAGED_LIST_ATTRIBUTE, pageService.getPagedList(pageNumber, countOnPage, userService));
        return "usersList";
    }

    @GetMapping(value = "/{id}")
    private String getUser(@PathVariable("id") long id,
                           Model model) {
        UserDto user = userFacade.getUsers(id);
        if (user == null) {
            log.error("User not found. HTTP404");
            throw new NotFoundException();
        }
        model.addAttribute(USER_ATTRIBUTE, user);

        return "userDetails";
    }

    @PostMapping(value = "/{id}", params = "save")
    private String updateUser(@PathVariable("id") long id,
                              @Valid UserDto user, BindingResult bindingResult,
                              Model model) {
        if (!bindingResult.hasErrors()) {
            try {
                userFacade.saveOrUpdate(user);
                return "redirect:/panel/users/" + user.getId();
            } catch (ValueNotUniqueException e) {
                model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "some values are not unique");
            }
        }
        return "userDetails";
    }

    @GetMapping(value = "/new")
    private String createUserForm(@RequestParam(value = "typeUser", defaultValue = "CLIENT") TypeUser typeUser, Model model) {
        model.addAttribute(CREATE_USER_ATTRIBUTE, true);
        model.addAttribute(TYPE_USER_ATTRIBUTE, typeUser);
        model.addAttribute(USER_ATTRIBUTE, new UserDto());
        return "userDetails";
    }

    @PostMapping(value = "/new", params = "save")
    private String createUserForm(@RequestParam(value = "typeUser", defaultValue = "CLIENT") TypeUser typeUser,
                                  @Valid UserDto user, BindingResult bindingResult,
                                  Model model) {
        if (!bindingResult.hasErrors()) {
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                bindingResult.addError(new FieldError("userDto", "password", null, false, new String[]{"NotEmpty.userDto.password"}, null, "value must not be empty"));
            } else {
                try {
                    userFacade.saveOrUpdate(user);
                    return "redirect:/panel/users/" + user.getId();
                } catch (ValueNotUniqueException e) {
                    model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "some values are not unique");
                }
            }
        }
        model.addAttribute(CREATE_USER_ATTRIBUTE, true);
        model.addAttribute(TYPE_USER_ATTRIBUTE, typeUser);
        return "userDetails";
    }

    @PostMapping(value = "/{id}", params = "remove")
    private String removeUser(@PathVariable("id") long id,
                              Model model) {
        userService.remove(id);
        return "redirect:/panel/users";
    }

    @PostMapping(params = "remove")
    private String removeUsers(@RequestParam(name = PAGE_NUMBER_REQUEST_PARAM, defaultValue = "1") int pageNumber,
                               CheckedList checkedList, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            log.error("Bad request params for deleting Users. HTTP400");
            throw new BadRequestException();
        }
        if (checkedList.getCheckedList() != null) {
            userService.removeList(checkedList.getCheckedList()
                    .stream().filter(CheckedItem::isChecked)
                    .map(CheckedItem::getId).collect(Collectors.toList()));
        }
        return "redirect:/panel/users?page=" + pageNumber;
    }

    @PostMapping(params = "sendEmail")
    private String sendEmailUsers(CheckedList checkedList, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            log.error("Bad request params for deleting Users. HTTP400");
            throw new BadRequestException();
        }
        StringBuilder params = new StringBuilder();
        if (checkedList.getCheckedList() != null) {
            List<User> users = userService.findAll(checkedList.getCheckedList()
                    .stream().filter(CheckedItem::isChecked)
                    .map(CheckedItem::getId).collect(Collectors.toList()));
            for(User user: users){
                params.append("&em=");
                params.append(UriUtils.encode(user.getEmail(), "UTF-8"));
            }
        }
        return "redirect:/panel/email?" + params.toString();
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public void exceptionHandler(Throwable t) {
        log.error("Bad request params, catch IllegalArgumentException. HTTP400", t);
        throw new BadRequestException(t);
    }
}
