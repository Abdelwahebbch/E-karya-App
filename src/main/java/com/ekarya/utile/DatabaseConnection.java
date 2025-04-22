package com.ekarya.utile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    //private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String URL ="jdbc:oracle:thin:@192.168.100.60:1521:FREE";
    //private static final String USER = "ekarya";
    private static final String USER = "c##test";
    private static final String PASSWORD = "0000";

    public static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load Oracle JDBC Driver
                Class.forName("oracle.jdbc.driver.OracleDriver");

                // Create connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connection established successfully");
            } catch (ClassNotFoundException e) {
                System.err.println("Oracle JDBC Driver not found: " + e.getMessage());
                throw new SQLException("Oracle JDBC Driver not found", e);
            } catch (SQLException e) {
                System.err.println("Connection failed: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
