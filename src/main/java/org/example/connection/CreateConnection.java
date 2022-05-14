package org.example.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CreateConnection {
/*    private static final String PATH = "src/main/resources/database.properties";
    private static Properties properties = new Properties();
    static {
        try {
            properties.load(new FileReader(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("db.url");
        Connection con = DriverManager.getConnection(url, properties);
        return con;
    }*/
    private static final String url = "jdbc:mysql://localhost:3306/repair_agency";
    private static final String user = "root";
    private static final String password = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(url, user, password);
    }
}
