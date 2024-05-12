package org.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class UserServiceTest {
    private EmailService emailService;
    private UserService userService;

    @Before
    public void setup() {
        emailService = Mockito.mock(EmailService.class);
        userService = new UserService(emailService);
    }

    @Test
    public void shouldSendEmailWithCorrectMessage() {
        Map<String, Object> data = new HashMap<>();
        data.put("email", "test@example.com");
        data.put("name", "Test User");

        userService.sendEmail("Test Message", data);

        verify(emailService, times(2)).sendEmail(anyString());
        verify(emailService).sendEmail("Hello Test User, Test Message");
    }

    @Test
    public void shouldNotSendEmailWhenDataMapIsEmpty() {
        Map<String, Object> data = new HashMap<>();

        userService.sendEmail("Test Message", data);

        verify(emailService, times(1)).sendEmail(anyString());
        verify(emailService).sendEmail("Test Message");
    }

    @Test
    public void shouldNotSendEmailWhenDataMapIsNull() {
        userService.sendEmail("Test Message", null);

        verify(emailService, times(1)).sendEmail(anyString());
        verify(emailService).sendEmail("Test Message");
    }
}