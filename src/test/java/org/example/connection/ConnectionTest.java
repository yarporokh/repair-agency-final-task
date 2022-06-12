package org.example.connection;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {
    @Test
    public void testConnection() throws SQLException {
        Connection connection = CreateConnection.getConnection();
        Assert.assertNotNull(connection);
        connection.close();
    }
}
