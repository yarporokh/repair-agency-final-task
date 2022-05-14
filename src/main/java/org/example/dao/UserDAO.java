package org.example.dao;

import org.example.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.example.connection.CreateConnection.getConnection;

public class UserDAO implements DAO<User, String> {

    private static final String INSERT = "INSERT INTO user (email, password, first_name, last_name, balance, role) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SELECT_LOGINED_USER = "SELECT * FROM user WHERE email = ? AND password = ?";
    public static final String IS_EXIST = "SELECT * FROM user WHERE email = ?";

    @Override
    public User findEntity(String email) {
        return null;
    }

    @Override
    public void insert(User user) {
        try {
            PreparedStatement psmt = getConnection().prepareStatement(INSERT);
            System.out.println(user.getEmail());
            psmt.setString(1, user.getEmail());
            psmt.setString(2, user.getPassword());
            psmt.setString(3, user.getFirstName());
            psmt.setString(4, user.getLastName());
            psmt.setDouble(5, user.getBalance());
            psmt.setString(6, user.getRole());
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(String s) {

    }

    @Override
    public List getAll() {
        return null;
    }

    public User getLoginedUser(String email, String password) {
        User user = new User();
        try {
            PreparedStatement psmt = getConnection().prepareStatement(SELECT_LOGINED_USER);
            psmt.setString(1, email);
            psmt.setString(2, password);

            ResultSet result = psmt.executeQuery();
            while (result.next()) {
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                Double balance = result.getDouble("balance");
                String role = result.getString("role");
                user.setEmail(email);
                user.setPassword(password);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setBalance(balance);
                user.setRole(role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean isExist(String email) {
        try {
            PreparedStatement psmt = getConnection().prepareStatement(IS_EXIST);
            psmt.setString(1, email);
            ResultSet result = psmt.executeQuery();
            while (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
