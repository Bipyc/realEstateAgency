package by.bsuir.realEstateAgency.core.service;

import java.util.ArrayList;
import java.util.List;

public interface EmailService {
    boolean sendEmail(List<String> recipients, String subject, String body);

    boolean sendEmailWithTemplate(List<String> recipients, String subject, String body);
}
