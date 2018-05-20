package by.bsuir.realEstateAgency.core.service;

import java.util.ArrayList;

public interface EmailService {
    boolean sendEmail(ArrayList<String> recipients, String subject, String body);
}
