package by.bsuir.realEstateAgency.core.sheduler;

import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.UserService;
import by.bsuir.realEstateAgency.core.service.impl.EmailServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class TimerTask {

    @Resource
    private UserService userService;

    @Resource
    private EmailServiceImpl mailSender;

    @Scheduled(fixedDelay = (60000 * 60 * 24))
    public void fix_delay() {

        List<User> users = userService.findAll(0, 10000);

        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd");

        System.out.println(date);

        for (User u : users) {

            System.out.println(u.getEmail());
            System.out.println(formatForDateNow.format(u.getDateOfBirth()));
            System.out.println(formatForDateNow.format(date));
            System.out.println("------------------");

            if (Objects.equals(formatForDateNow.format(u.getDateOfBirth()), formatForDateNow.format(date))) {

                try {
                    List<String> recipients = new ArrayList<>();
                    recipients.add(u.getEmail());
                    recipients.add("dimakutas17@gmail.com");
                    recipients.add("dimon.hyip@mail.ru");
                    mailSender.sendEmail(recipients
                            ,"BirthDay"
                            ,"Hi, " + u.getFirstName() + u.getLastName()
                                    + "\nTheme: BirthDay!!!!\n" +
                                    "I wish you a long and amazing life.\n" +
                                    " I wish you great health and excellent results in all your dealings.\n" +
                                    " Most importantly, I wish you happiness without measure every minute in your life.\n" +
                                    " Be loved, be happy, enjoy every day!");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }
}