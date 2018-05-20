package by.bsuir.realEstateAgency.web.controller.profile;

import by.bsuir.realEstateAgency.core.exception.ValueNotUniqueException;
import by.bsuir.realEstateAgency.web.bean.user.UserDto;
import by.bsuir.realEstateAgency.web.exceptions.NotFoundException;
import by.bsuir.realEstateAgency.web.facade.UserFacade;
import by.bsuir.realEstateAgency.web.security.AuthUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static final String USER_ATTRIBUTE = "userDto";

    private static final String SHOW_USER_PROFILE_ATTRIBUTE = "showUserProfile";

    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";

    @Resource
    private UserFacade userFacade;

    @GetMapping
    public String getProfile(Authentication authentication, Model model){
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        UserDto user = userFacade.getUsers(userDetails.getUser().getId());
        model.addAttribute(USER_ATTRIBUTE, user);
        model.addAttribute(SHOW_USER_PROFILE_ATTRIBUTE, true);
        return "userDetails";
    }

    @PostMapping
    public String saveProfile(@Valid UserDto user, BindingResult bindingResult,
                              Authentication authentication, Model model){
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        user.setId(userDetails.getUser().getId());
        user.setLogin(userDetails.getUser().getLogin());
        if (!bindingResult.hasErrors()) {
            try {
                userFacade.saveOrUpdate(user);
                return "redirect:/profile";
            } catch (ValueNotUniqueException e) {
                model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "some values are not unique");
            }
        }
        model.addAttribute(SHOW_USER_PROFILE_ATTRIBUTE, true);
        return "userDetails";
    }

}
