package org.example.models;

import org.example.models.Application;
import org.example.models.Role;
import org.example.models.User;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ModelsTest {
    @Test
    public void UserTest() {
        User user = spy(User.class);
        user.setEmail("user@gmail.com");
        verify(user, times(1)).setEmail("user@gmail.com");
        assertEquals("user@gmail.com", user.getEmail());

        user.setPassword("123456");
        verify(user, times(1)).setPassword("123456");
        assertEquals("123456", user.getPassword());

        user.setFirstName("User");
        verify(user, times(1)).setFirstName("User");
        assertEquals("User", user.getFirstName());

        user.setLastName("User");
        verify(user, times(1)).setLastName("User");
        assertEquals("User", user.getLastName());

        user.setRole(Role.USER);
        verify(user, times(1)).setRole(Role.USER);
        assertEquals(Role.USER, user.getRole());

        user.setBalance(100);
        verify(user, times(1)).setBalance(100);
        assertEquals(100, user.getBalance(), 0.1);
    }

    @Test
    public void ApplicationTest() {
        Application application = spy(Application.class);

        application.setApplicationId(1);
        verify(application, times(1)).setApplicationId(1);
        assertEquals(1, application.getApplicationId());

        application.setResponseText("Qwer");
        verify(application, times(1)).setResponseText("Qwer");
        assertEquals("Qwer", application.getResponseText());

        application.setProgress("At work");
        verify(application, times(1)).setProgress("At work");
        assertEquals("At work", application.getProgress());

        application.setPrice(100);
        verify(application, times(1)).setPrice(100);
        assertEquals(100, application.getPrice(), 0.1);

        application.setPaymentStatus("Paid");
        verify(application, times(1)).setPaymentStatus("Paid");
        assertEquals("Paid", application.getPaymentStatus());

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        application.setDate(date);
        verify(application, times(1)).setDate(date);
        assertEquals(date, application.getDate());

        application.setEmail("app@gmail.com");
        verify(application, times(1)).setEmail("app@gmail.com");
        assertEquals("app@gmail.com", application.getEmail());

        application.setServicemanEmail("ser@gmail.com");
        verify(application, times(1)).setServicemanEmail("ser@gmail.com");
        assertEquals("ser@gmail.com", application.getServicemanEmail());
    }
}
