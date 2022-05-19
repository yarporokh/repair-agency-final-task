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

    public static List<Application> getAllApplications() {
        return applicationDAO.getAll();
    }

    public static void updateApplication(int id, double price, String serviceman, String paymentStatus, String progress) {
        Application application = Application
                .newBuilder()
                .setApplicationId(id)
                .setPrice(price)
                .setServicemanEmail(serviceman)
                .setPaymentStatus(paymentStatus)
                .setProgress(progress)
                .build();
        applicationDAO.update(application);
    }

    public static void updatePaymentStatus(int id) {
        applicationDAO.updateApplicationPaymentStatus(id, "Paid");
    }
}
