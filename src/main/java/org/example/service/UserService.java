package org.example.service;

import org.example.dao.UserDAO;
import org.example.models.Application;
import org.example.models.Role;
import org.example.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The UserService class provides the connection
 * between servlets and user dao
 */
public class UserService {
    private static UserDAO userDAO = new UserDAO();

    /**
     method addUser adds user in database */
    public static void addUser(String email, String password, String firstName, String lastName) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBalance(0.0);
        user.setRole(Role.USER);
        userDAO.insert(user);
    }

    /**
     method isUserExist returns true if user is in database */
    public static boolean isUserExist(String email) {
        return userDAO.isExist(email);
    }

    /**
     method loggedUser returns logged user */
    public static User loggedUser(String email, String password) {
        return userDAO.getLoginedUser(email, password);
    }

    /**
     method updateUser updates user in database */
    public static void updateUser(User user) {
        userDAO.update(user);
    }

    /**
     method getUsers gets a list of all users */
    public static List getUsers() {
        return userDAO.getAll();
    }

    /**
     method updateBalanceAndRole updates balance and role of the user */
    public static void updateBalanceAndRole(String email, String role, double balance) {
        userDAO.updateRoleAndBalanceByEmail(email, role, balance);
    }

    /**
     method getServicemenNamesByEmail gets a list of servicemen full names */
    public static List<String> getServicemenNamesByEmail(List<Application> applicationList) {
        List<String> fullNames = new ArrayList<>();
        for (Application application: applicationList) {
            String fullName = userDAO.getFullNamesByEmail(application.getServicemanEmail());
            fullNames.add(fullName);
        }
        return fullNames;
    }

    /**
     method getServicemenNamesByEmail gets a list of servicemen emails */
    public static List<String> getServicemenEmailsList() {
        List<User> users = userDAO.getAll();
        List<User> servicemen = users.stream().filter(x->x.getRole() == Role.SERVICEMAN).collect(Collectors.toList());
        List<String> emails = servicemen.stream().map(User::getEmail).collect(Collectors.toList());
        return emails;
    }

    /**
     method getUserRecords gets a list of pagination user */
    public static List<User> getUserRecords(int start, int total) {
        return userDAO.getRecords(start, total);
    }

    /**
     method getNumberOfRows returns number of rows in user table */
    public static int getNumberOfRows() {
        return userDAO.numberOfRows();
    }
}
