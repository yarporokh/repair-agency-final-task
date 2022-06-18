package org.example.dao;

import org.slf4j.*;
import org.example.models.Application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.connection.CreateConnection.getConnection;

public class ApplicationDAO implements DAO<Application, String> {
    private static final Logger log = LoggerFactory.getLogger(ApplicationDAO.class);
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
            log.debug("User {} create application", application.getEmail());
        } catch (SQLException e) {
            log.warn("Can't insert application. ", e);
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
            log.debug("User {} update application ", application.getEmail());
        } catch (SQLException e) {
            log.warn("Can't update application. ", e);
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
            log.warn("Can't get all applications. ", e);
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
            log.warn("Can't get by email applications. ", e);
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
            log.debug("Update application payment status {}", id);
        } catch (SQLException e) {
            log.warn("Can't update application payment status. ", e);
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
            log.debug("Update application response {}", id);
        } catch (SQLException e) {
            log.warn("Can't update application response. ", e);
            throw new RuntimeException(e);
        } finally {
            close(connection);
            close(psmt);
        }
    }

    public List<Application> getRecords(int start, int total) {
        List<Application> list = new ArrayList<Application>();
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement("select * from application limit " + (start - 1) + "," + total);
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
                list.add(application);
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
            psmt = connection.prepareStatement("SELECT COUNT(*) FROM application");
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
        return numberOfRows;
    }


    public List<Application> getSortedRecords(int start, int total, String sortingBy) {
        List<Application> list = new ArrayList<Application>();
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            String query = "select * from application";
            String fquery = "limit " + (start - 1) + "," + total;
            connection = getConnection();

            switch (sortingBy) {
                case "idASC":
                    psmt = connection.prepareStatement(query + " order by application_id asc " + fquery);
                    break;
                case "idDESC":
                    psmt = connection.prepareStatement(query + " order by application_id desc " + fquery);
                    break;
                case "emailASC":
                    psmt = connection.prepareStatement(query + " order by email asc " + fquery);
                    break;
                case "emailDESC":
                    psmt = connection.prepareStatement(query + " order by email desc " + fquery);
                    break;
                case "textASC":
                    psmt = connection.prepareStatement(query + " order by text asc " + fquery);
                    break;
                case "textDESC":
                    psmt = connection.prepareStatement(query + " order by text desc " + fquery);
                    break;
                case "dateASC":
                    psmt = connection.prepareStatement(query + " order by date asc " + fquery);
                    break;
                case "dateDESC":
                    psmt = connection.prepareStatement(query + " order by date desc " + fquery);
                    break;
                case "servicemanASC":
                    psmt = connection.prepareStatement(query + " order by serviceman_email asc " + fquery);
                    break;
                case "servicemanDESC":
                    psmt = connection.prepareStatement(query + " order by serviceman_email desc " + fquery);
                    break;
                case "priceASC":
                    psmt = connection.prepareStatement(query + " order by price asc " + fquery);
                    break;
                case "priceDESC":
                    psmt = connection.prepareStatement(query + " order by price desc " + fquery);
                    break;
                case "pstatusASC":
                    psmt = connection.prepareStatement(query + " order by paymnet_status asc " + fquery);
                    break;
                case "pstatusDESC":
                    psmt = connection.prepareStatement(query + " order by paymnet_status desc " + fquery);
                    break;
                case "progressASC":
                    psmt = connection.prepareStatement(query + " order by progress asc " + fquery);
                    break;
                case "progressDESC":
                    psmt = connection.prepareStatement(query + " order by progress desc " + fquery);
                    break;
                case "responseASC":
                    psmt = connection.prepareStatement(query + " order by response_text asc " + fquery);
                    break;
                case "responseDESC":
                    psmt = connection.prepareStatement(query + " order by response_text desc " + fquery);
                    break;

            }

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
                list.add(application);
            }
        } catch (Exception e) {
        } finally {
            close(connection);
            close(psmt);
        }
        return list;
    }
}