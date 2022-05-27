package org.example.service;

import org.example.dao.ApplicationDAO;
import org.example.models.Application;

import java.sql.Date;
import java.util.List;

/**
 * The ApplicationService class provides the connection
 * between servlets and application dao
 */
public class ApplicationService {
    private static ApplicationDAO applicationDAO = new ApplicationDAO();

    /**
     method addApplication adds application in database */
    public static void addApplication(String email, String text) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        Application application = new Application();
        application.setEmail(email);
        application.setText(text);
        application.setPrice(0.0);
        application.setDate(date);
        application.setPaymentStatus("Payment expected");
        application.setProgress("Not started");
        application.setResponseText("");

        applicationDAO.insert(application);
    }

    /**
     method getUserApplications gets user list of applications */
    public static List<Application> getUserApplications(String email) {
        return applicationDAO.getByEmail(email);
    }

    /**
     method getAllApplications gets a list of all applications */
    public static List<Application> getAllApplications() {
        return applicationDAO.getAll();
    }

    /**
     method updateApplication updates an application */
    public static void updateApplication(int id, double price, String serviceman, String paymentStatus, String progress, String responseText) {
        Application application = Application
                .newBuilder()
                .setApplicationId(id)
                .setPrice(price)
                .setServicemanEmail(serviceman)
                .setPaymentStatus(paymentStatus)
                .setProgress(progress)
                .setResponseText(responseText)
                .build();
        applicationDAO.update(application);
    }

    /**
     method updatePaymentStatus updates a payment status of application to Paid */
    public static void updatePaymentStatus(int id) {
        applicationDAO.updateApplicationPaymentStatus(id, "Paid");
    }

    /**
     method updateResponseText adds a response text to application */
    public static void updateResponseText(int id, String response) {
        applicationDAO.updateApplicationResponseText(id, response);
    }
}
