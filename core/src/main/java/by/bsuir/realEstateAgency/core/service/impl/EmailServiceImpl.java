package by.bsuir.realEstateAgency.core.service.impl;

import by.bsuir.realEstateAgency.core.service.EmailService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailServiceImpl implements EmailService {
    final String fromEmail = "dimakutas17@mail.ru";
    final String password = "";
    final String host = "smtp.yandex.ru";
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
    public boolean sendEmail(ArrayList<String> recipients, String subject, String body){
        try
        {
            if(recipients.size() == 0)
                return true;
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, "Car sharing service"));
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
}
