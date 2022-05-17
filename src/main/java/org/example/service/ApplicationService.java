package org.example.service;

import org.example.dao.ApplicationDAO;
import org.example.models.Application;

import java.sql.Date;
import java.util.List;

public class ApplicationService {
    private static ApplicationDAO applicationDAO = new ApplicationDAO();

    public static void addApplication(String email, String text) {
        Application application = new Application();
        application.setEmail(email);
        application.setText(text);
        application.setPrice(0.0);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        application.setDate(date);
        application.setPaymentStatus("Payment expected");
        application.setProgress("Not started");

        applicationDAO.insert(application);
    }

    public static List<Application> getUserApplications(String email) {
        return applicationDAO.getByEmail(email);
    }
}
