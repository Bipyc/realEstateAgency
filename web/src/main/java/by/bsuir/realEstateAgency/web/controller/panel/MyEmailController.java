package by.bsuir.realEstateAgency.web.controller.panel;

import by.bsuir.realEstateAgency.core.service.EmailService;
import by.bsuir.realEstateAgency.core.service.UserService;
import by.bsuir.realEstateAgency.web.bean.EmailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/panel/email")
public class MyEmailController {

    private final static String EMAIL_PARAMS = "em";

    private final static String EMAIL_ATTRIBUTE = "emailMessage";

    @Resource
    private UserService userService;

    @Resource
    private EmailService emailService;

    @GetMapping
    public String getEmailPage(@RequestParam(value="em", required=false) List<String> address,
                            Model model) {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setEmails(address);
        model.addAttribute(EMAIL_ATTRIBUTE, emailMessage);
        return "emailPage";
    }

    @PostMapping
    public String getEmailPage(@Valid EmailMessage emailMessage, BindingResult bindingResult, Model model) {
        checkEmailMessage(emailMessage, bindingResult);
        if(bindingResult.hasErrors()){
            return "emailPage";
        }
        emailService.sendEmail(emailMessage.getEmails(), emailMessage.getTitle(), emailMessage.getText());
        return "redirect:/panel/users";
    }

    public boolean checkEmailMessage(EmailMessage emailMessage, BindingResult bindingResult){
        if(emailMessage.getEmails() != null) {
            emailMessage.setEmails(emailMessage.getEmails()
                    .stream().filter(s -> !s.isEmpty())
                    .collect(Collectors.toList()));
        }
        if(emailMessage.getEmails() != null && emailMessage.getEmails().isEmpty()){
            bindingResult.addError(new FieldError("emailMessage", "emails[0]", null,
                    false, new String[]{"NotEmpty.emailMessage.emails"}, null,
                    "Addresses should be not empty"));
        }
        return bindingResult.hasErrors();
    }
}

