package by.bsuir.realEstateAgency.web.controller;


import by.bsuir.realEstateAgency.core.exception.ValueNotUniqueException;
import by.bsuir.realEstateAgency.web.bean.user.TypeUser;
import by.bsuir.realEstateAgency.web.bean.user.UserDto;
import by.bsuir.realEstateAgency.web.facade.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

import static by.bsuir.realEstateAgency.web.controller.util.AuthUtil.isAuthenticated;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final String REGISTRATION_ATTRIBUTE = "registration";

    private static final String TYPE_USER_ATTRIBUTE = "typeUser";

    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";

    private static final String USER_ATTRIBUTE = "userDto";

    @Resource
    private UserFacade userFacade;

    @GetMapping
    public String getRegisterPage(Model model) {
        if (isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute(REGISTRATION_ATTRIBUTE, true);
        model.addAttribute(TYPE_USER_ATTRIBUTE, TypeUser.CLIENT);
        model.addAttribute(USER_ATTRIBUTE, new UserDto());
        return "userDetails";
    }

    @PostMapping
    public String registration(@Valid UserDto user, BindingResult bindingResult, Model model) {
        if (isAuthenticated()) {
            return "redirect:/";
        }

        if (!bindingResult.hasErrors()) {
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                bindingResult.addError(new FieldError("userDto", "password", null, false, new String[]{"NotEmpty.userDto.password"}, null, "value must not be empty"));
            } else {
                try {
                    user.setTypeUser(TypeUser.CLIENT);
                    userFacade.saveOrUpdate(user);
                    return "redirect:/";
                } catch (ValueNotUniqueException e) {
                    model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "some values are not unique");
                }
            }
        }
        model.addAttribute(REGISTRATION_ATTRIBUTE, true);
        model.addAttribute(TYPE_USER_ATTRIBUTE, TypeUser.CLIENT);
        return "userDetails";
    }
}
