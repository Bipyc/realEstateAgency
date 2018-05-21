package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.model.User;
import by.bsuir.realEstateAgency.core.service.EmailService;
import by.bsuir.realEstateAgency.core.service.UserService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private UserService userService;

    ExecutorService executor = Executors.newFixedThreadPool(1);
    final String fromEmail = "Real_Estate_Agency_info@mail.ru";
    final String password = "RealEstateAgency";
    final String host = "smtp.mail.ru"; // smtp.yandex.ru smtp.mail.ru smtp.gmail.com
    final String port = "465";

    Session session;

    public EmailServiceImpl() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        session = Session.getInstance(props, auth);
    }

    @Override
    public boolean sendEmailWithTemplate(List<String> recipients, String subject, String body){
        try
        {
            if(recipients.size() == 0)
                return true;

            for (String address:recipients) {
                User user = userService.getByLoginOrEmail(address);
                if(user != null){
                    MimeMessage msg = new MimeMessage(session);
                    msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                    msg.addHeader("format", "flowed");
                    msg.addHeader("Content-Transfer-Encoding", "8bit");

                    msg.setFrom(new InternetAddress(fromEmail, "Real estate agency service"));
                    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
                    msg.setSubject(compileTemplate(subject, user), "UTF-8");
                    msg.setText(compileTemplate(body, user), "UTF-8");
                    msg.setSentDate(new Date());
                    Transport.send(msg);
                }
            }
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sendEmail(List<String> recipients, String subject, String body){
        try
        {
            if(recipients.size() == 0)
                return true;
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, "Real estate agency service"));
            for (String recipient: recipients) {
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            Transport.send(msg);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    private String compileTemplate(String template, User user){
         return template.replaceAll("\\{username\\}", user.getLogin())
                .replaceAll("\\{firstName\\}", user.getFirstName())
                .replaceAll("\\{lastName\\}", user.getLastName());
    }
}
