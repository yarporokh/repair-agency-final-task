package org.example.dao;

import org.example.models.Application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.connection.CreateConnection.getConnection;

public class ApplicationDAO implements DAO<Application, String>{
    private static final String INSERT = "INSERT INTO application (email, text, date, price, payment_status, progress) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SELECT_BY_EMAIL = "SELECT * FROM application WHERE email = ?";

    @Override
    public Application findEntity(String s) {
        return null;
    }

    @Override
    public void insert(Application application) {
        try {
            PreparedStatement psmt = getConnection().prepareStatement(INSERT);
            psmt.setString(1, application.getEmail());
            psmt.setString(2, application.getText());
            psmt.setDate(3, application.getDate());
            psmt.setDouble(4, application.getPrice());
            psmt.setString(5, application.getPaymentStatus());
            psmt.setString(6, application.getProgress());
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Application application) {

    }

    @Override
    public void delete(String s) {

    }

    @Override
    public List<Application> getAll() {
        return null;
    }


    public List<Application> getByEmail (String email) {
        List<Application> applicationList = new ArrayList<>();
        try {
            PreparedStatement psmt = getConnection().prepareStatement(SELECT_BY_EMAIL);
            psmt.setString(1, email);
            ResultSet result = psmt.executeQuery();

            while (result.next()) {
                Application application = new Application();
                application.setApplicationId(result.getInt("application_id"));
                application.setEmail(result.getString("email"));
                application.setText(result.getString("text"));
                application.setDate(result.getDate("date"));
                application.setPrice(result.getDouble("price"));
                application.setPaymentStatus(result.getString("payment_status"));
                application.setProgress(result.getString("progress"));
                applicationList.add(application);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return applicationList;
    }
}
