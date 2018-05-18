package by.bsuir.realEstateAgency.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static by.bsuir.realEstateAgency.web.controller.util.AuthUtil.isAuthenticated;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String getLoginPage() {
        if (isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }
}
