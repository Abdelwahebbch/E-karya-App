package com.ekarya.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ekarya.Models.User;
import com.ekarya.utile.DatabaseConnection;

public class UserDAO {
    private final DatabaseConnection dbConnection;

    public UserDAO() {
        this.dbConnection = new DatabaseConnection();
    }

    public User createUser(String fullName, String phoneNumber, String email, String password) {
        User u = new User();
        u.setFullname(fullName);
        u.setPhoneNumber(phoneNumber);
        u.setEmail(email);
        u.setPassword(password);

        // Using the Oracle sequence in the INSERT statement
        String query = "INSERT INTO users (id, fullname, phone_number, email, password) " +
                "VALUES (user_id_seq.NEXTVAL, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, fullName);
            stmt.setString(2, phoneNumber);
            stmt.setString(3, email);
            stmt.setString(4, password);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                // Get the ID that was just generated by the sequence
                try (Statement idStmt = conn.createStatement();
                        ResultSet rs = idStmt.executeQuery("SELECT user_id_seq.CURRVAL FROM dual")) {
                    if (rs.next()) {
                        int userId = rs.getInt(1);
                        u.setId(userId);
                    }
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
            return null; // Return null if there's an exception
        }

        return u; // Return the user with the generated ID
    }

    public User VerifUser(String email, String password) {
        // SQL syntax error: "when" should be "where"
        String query = "SELECT email, password, ID FROM users WHERE email = ? AND password = ?";

        try (Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                // ResultSet has at least one row, user exists
                User u = new User();
                u.setEmail(email);
                u.setPassword(password);
                u.setId(resultSet.getInt("ID"));
                return u;
            } else {

                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error in the verifying process: " + e.getMessage());
            return null; 
        }
    }

}