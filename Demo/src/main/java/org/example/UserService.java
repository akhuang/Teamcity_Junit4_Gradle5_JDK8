package org.example;

import java.util.Map;

public class UserService {
    private EmailService emailService;

    public UserService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendEmail(String message, Map<String, Object> data) {
        emailService.sendEmail(message);

        // get data from data map
        String email = (String) data.get("email");
        String name = (String) data.get("name");
        // send email
        emailService.sendEmail("Hello " + name + ", " + message);

    }
}
