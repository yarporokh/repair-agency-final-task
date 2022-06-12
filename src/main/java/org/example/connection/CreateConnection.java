package org.example.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateConnection {

    private static final String url = "jdbc:mysql://localhost:3306/repair_agency?autoReconnect=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "root";
    public static final String driver= "com.mysql.jdbc.Driver";
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.setDriverClassName(driver);
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}