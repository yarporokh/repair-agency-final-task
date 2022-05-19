package org.example.service;

import org.example.dao.UserDAO;
import org.example.models.Application;
import org.example.models.Role;
import org.example.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private static UserDAO userDAO = new UserDAO();

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

    public static boolean isUserExist(String email) {
        return userDAO.isExist(email);
    }

    public static User loggedUser(String email, String password) {
        return userDAO.getLoginedUser(email, password);
    }

    public static void updateUser(User user) {
        userDAO.update(user);
    }

    public static List getUsers() {
        return userDAO.getAll();
    }

    public static void updateBalanceAndRole(String email, String role, double balance) {
        userDAO.updateRoleAndBalanceByEmail(email, role, balance);
    }

    public static List<String> getServicemenNamesByEmail(List<Application> applicationList) {
        List<String> fullNames = new ArrayList<>();
        for (Application application: applicationList) {
            String fullName = userDAO.getFullNamesByEmail(application.getServicemanEmail());
            fullNames.add(fullName);
        }
        return fullNames;
    }

    public static List<String> getServicemenEmailsList() {
        List<User> users = userDAO.getAll();
        List<User> servicemen = users.stream().filter(x->x.getRole() == Role.SERVICEMAN).collect(Collectors.toList());
        List<String> emails = servicemen.stream().map(User::getEmail).collect(Collectors.toList());
        return emails;
    }
}
