package org.example.dao;

import org.example.models.Application;
import org.slf4j.*;
import org.example.models.Role;
import org.example.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.connection.CreateConnection.getConnection;

public class UserDAO implements DAO<User, String> {
    private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

    private static final String INSERT = "INSERT INTO user (email, password, first_name, last_name, balance, role) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SELECT_LOGINED_USER = "SELECT * FROM user WHERE email = ? AND password = ?";
    public static final String IS_EXIST = "SELECT * FROM user WHERE email = ?";
    public static final String UPDATE_USER = "UPDATE user SET password = ?, first_name = ?, last_name = ?, balance = ?, role = ? WHERE email = ?";
    public static final String GET_ALL = "SELECT * FROM user";
    public static final String UPDATE_ROLE_AND_BALANCE = "UPDATE user SET balance = ?, role = ? WHERE email = ?";

    public static final String GET_FULL_NAME_BY_EMAIL = "SELECT first_name, last_name FROM user WHERE email = ?";


    @Override
    public void insert(User user) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement(INSERT);
            psmt.setString(1, user.getEmail());
            psmt.setString(2, user.getPassword());
            psmt.setString(3, user.getFirstName());
            psmt.setString(4, user.getLastName());
            psmt.setDouble(5, user.getBalance());
            psmt.setString(6, user.getRole().toString());
            psmt.executeUpdate();
            log.debug("Create user {}", user.getEmail());
        } catch (SQLException e) {
            log.warn("Can't create user. ", e);
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }
    }

    @Override
    public void update(User user) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement(UPDATE_USER);
            psmt.setString(1, user.getPassword());
            psmt.setString(2, user.getFirstName());
            psmt.setString(3, user.getLastName());
            psmt.setDouble(4, user.getBalance());
            psmt.setString(5, user.getRole().toString());
            psmt.setString(6, user.getEmail());
            psmt.executeUpdate();
            log.debug("Update user {}", user.getEmail());
        } catch (SQLException e) {
            log.warn("Can't update user. ", e);
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }
    }


    @Override
    public List getAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement(GET_ALL);
            ResultSet result = psmt.executeQuery();
            while (result.next()) {
                User user = User.newBuilder().setEmail(result.getString("email"))
                        .setFirstName(result.getString("first_name"))
                        .setLastName(result.getString("last_name"))
                        .setBalance(result.getDouble("balance"))
                        .setRole(Role.valueOf(result.getString("role")))
                        .build();
                users.add(user);
            }
        } catch (SQLException e) {
            log.warn("Can't get all users. ", e);
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }
        return users;
    }

    public User getLoginedUser(String email, String password) {
        User user = new User();
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement(SELECT_LOGINED_USER);
            psmt.setString(1, email);
            psmt.setString(2, password);

            ResultSet result = psmt.executeQuery();
            while (result.next()) {
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                Double balance = result.getDouble("balance");
                String role = result.getString("role");
                user = User.newBuilder().setEmail(email)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setPassword(password)
                        .setBalance(balance)
                        .setRole(Role.valueOf(role))
                        .build();
            }
        } catch (SQLException e) {
            log.warn("Can't get loggined user. ", e);
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }
        return user.getFirstName() != null ? user : null;
    }

    public boolean isExist(String email) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement(IS_EXIST);
            psmt.setString(1, email);
            ResultSet result = psmt.executeQuery();
            while (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            log.warn("Can't get exist user. ", e);
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }
        return false;
    }

    public void updateRoleAndBalanceByEmail(String email, String role, double balance) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement(UPDATE_ROLE_AND_BALANCE);
            psmt.setDouble(1, balance);
            psmt.setString(2, role);
            psmt.setString(3, email);
            psmt.executeUpdate();
            log.debug("Update role and balance for {}", email);
        } catch (SQLException e) {
            log.warn("Can't update role and balance. ", e);
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }
    }

    public String getFullNamesByEmail(String email) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement(GET_FULL_NAME_BY_EMAIL);
            psmt.setString(1, email);
            ResultSet result = psmt.executeQuery();
            while (result.next()) {
                String fullName = result.getString("first_name") + " " + result.getString("last_name");
                return fullName;
            }
        } catch (SQLException e) {
            log.warn("Can't get full name. ", e);
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }
        return "None";
    }


    public List<User> getRecords(int start, int total) {
        List<User> list = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement("select * from user limit " + (start - 1) + "," + total);
            ResultSet result = psmt.executeQuery();
            while (result.next()) {
                User user = User.newBuilder().setEmail(result.getString("email"))
                        .setFirstName(result.getString("first_name"))
                        .setLastName(result.getString("last_name"))
                        .setBalance(result.getDouble("balance"))
                        .setRole(Role.valueOf(result.getString("role")))
                        .build();
                list.add(user);
            }
        } catch (Exception e) {
        } finally {
            close(connection);
            close(psmt);
        }
        return list;
    }

    public int numberOfRows() {
        int numberOfRows = 0;
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement("SELECT COUNT(*) FROM user");
            ResultSet result = psmt.executeQuery();
            while (result.next()) {
                numberOfRows = result.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }
        return  numberOfRows;
    }
}
