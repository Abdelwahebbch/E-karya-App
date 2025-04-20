package com.ekarya.utile;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {
    
    @BeforeEach
    void setUp() {
        // Ensures that before each test, the connection is closed if it was opened during a previous test.
        DatabaseConnection.closeConnection();
    }

    @Test
    void testGetConnection_success() {
        try {
            // Verifies that the getConnection() method successfully establishes a connection and doesn't return null.
            Connection connection = DatabaseConnection.getConnection();
            assertNotNull(connection, "Connection should not be null");
            assertFalse(connection.isClosed(), "Connection should be open");
        } catch (SQLException e) {
            fail("SQLException should not have been thrown: " + e.getMessage());
        }
    }

    @Test
    void testGetConnection_multipleCalls() {
        try {
            // Verifies that subsequent calls to getConnection() return the same connection object (i.e., the connection is reused).
            Connection firstConnection = DatabaseConnection.getConnection();
            Connection secondConnection = DatabaseConnection.getConnection();
            
            assertSame(firstConnection, secondConnection, "Subsequent calls should return the same connection");
        } catch (SQLException e) {
            fail("SQLException should not have been thrown: " + e.getMessage());
        }
    }

    @Test
    void testCloseConnection() {
        try {
            // Establish the connection
            Connection connection = DatabaseConnection.getConnection();
            assertNotNull(connection, "Connection should not be null");

            // Now close the connection and verify that it is indeed closed
            DatabaseConnection.closeConnection();
            assertTrue(connection.isClosed(), "Connection should be closed after calling closeConnection()");
        } catch (SQLException e) {
            fail("SQLException should not have been thrown: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        // Ensures that after each test, the connection is closed to avoid any interference between tests.
        DatabaseConnection.closeConnection();
    }
}
