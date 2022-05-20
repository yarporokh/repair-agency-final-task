package org.example.dao;

import com.sun.imageio.plugins.jpeg.JPEGStreamMetadataFormat;
import org.example.models.Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.connection.CreateConnection.getConnection;

public class ApplicationDAO implements DAO<Application, String> {
    private static final String INSERT = "INSERT INTO application (email, text, date, price, payment_status, progress) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SELECT_BY_EMAIL = "SELECT * FROM application WHERE email = ?";
    public static final String GET_ALL = "SELECT * FROM application";

    public static final String UPDATE = "UPDATE application SET price = ?, payment_status = ?, progress = ?, serviceman_email = ? WHERE application_id = ?";
    public static final String UPDATE_PAYMENT_STATUS = "UPDATE application SET payment_status = ? WHERE application_id = ?";
    public static final String UPDATE_RESPONSE = "UPDATE application SET response_text = ? WHERE application_id = ?";

    @Override
    public void insert(Application application) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement(INSERT);
            psmt.setString(1, application.getEmail());
            psmt.setString(2, application.getText());
            psmt.setDate(3, application.getDate());
            psmt.setDouble(4, application.getPrice());
            psmt.setString(5, application.getPaymentStatus());
            psmt.setString(6, application.getProgress());
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }
    }

    @Override
    public void update(Application application) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement(UPDATE);
            psmt.setDouble(1, application.getPrice());
            psmt.setString(2, application.getPaymentStatus());
            psmt.setString(3, application.getProgress());
            psmt.setString(4, application.getServicemanEmail());
            psmt.setInt(5, application.getApplicationId());
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }
    }

    @Override
    public List<Application> getAll() {
        List<Application> applicationList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement(GET_ALL);
            ResultSet result = psmt.executeQuery();

            while (result.next()) {
                Application application = Application.newBuilder().setApplicationId(result.getInt("application_id"))
                        .setEmail(result.getString("email"))
                        .setText(result.getString("text"))
                        .setDate(result.getDate("date"))
                        .setPrice(result.getDouble("price"))
                        .setPaymentStatus(result.getString("payment_status"))
                        .setProgress(result.getString("progress"))
                        .setServicemanEmail(result.getString("serviceman_email"))
                        .setResponseText(result.getString("response_text"))
                        .build();
                applicationList.add(application);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }

        return applicationList;
    }


    public List<Application> getByEmail(String email) {
        List<Application> applicationList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement(SELECT_BY_EMAIL);
            psmt.setString(1, email);
            ResultSet result = psmt.executeQuery();

            while (result.next()) {
                Application application = Application.newBuilder().setApplicationId(result.getInt("application_id"))
                        .setEmail(result.getString("email"))
                        .setText(result.getString("text"))
                        .setDate(result.getDate("date"))
                        .setPrice(result.getDouble("price"))
                        .setPaymentStatus(result.getString("payment_status"))
                        .setProgress(result.getString("progress"))
                        .setServicemanEmail(result.getString("serviceman_email"))
                        .setResponseText(result.getString("response_text"))
                        .build();
                applicationList.add(application);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }
        return applicationList;
    }

    public void updateApplicationPaymentStatus(int id, String paymentStatus) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement(UPDATE_PAYMENT_STATUS);
            psmt.setString(1, paymentStatus);
            psmt.setInt(2, id);
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }
    }

    public void updateApplicationResponseText(int id, String responseText) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement(UPDATE_RESPONSE);
            psmt.setString(1, responseText);
            psmt.setInt(2, id);
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }
    }
}
