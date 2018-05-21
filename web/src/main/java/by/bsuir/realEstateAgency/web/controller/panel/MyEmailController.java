package by.bsuir.realEstateAgency.web.controller.panel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/panel/email")
public class MyEmailController {

    private final static String EMAIL_PARAMS = "em";

    private final static String EMAIL_ATTRIBUTE = "emails";

    @GetMapping
    public String getEmailPage(@RequestParam(value="em", required=false) List<String> address,
                            Model model) {
        model.addAttribute(EMAIL_ATTRIBUTE, address);
        return "emailPage";
    }
}

