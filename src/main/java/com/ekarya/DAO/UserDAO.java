package com.ekarya.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ekarya.Models.User;
import com.ekarya.utile.DatabaseConnection;

public class UserDAO {
    // private final DatabaseConnection dbConnection;

    public UserDAO() {
        // this.dbConnection = new DatabaseConnection();
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

        try (Connection conn = DatabaseConnection.getConnection();
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
            return null;
        }

        return u;
    }

    public User VerifUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                // ResultSet has at least one row, user exists
                User u = new User();
                u.setFullname(resultSet.getString("fullname"));
                u.setPhoneNumber(resultSet.getString("phone_number"));
                u.setEmail(email);
                u.setBirthday(resultSet.getDate("birthday"));
                u.setBio(resultSet.getString("bio"));
                u.setPassword(password);
                u.setId(resultSet.getInt("id"));
                return u;
            } else {

                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error in the verifying process: " + e.getMessage());
            return null;
        }
    }

    public User editUser(int id, String fullName, String email, String phoneNumber, Date birthDate, String bio) {
        String updateQuery = "UPDATE users SET fullname = ?, email = ?, phone_number = ?, birthday = ?, bio = ? WHERE id = ?";
        String selectQuery = "SELECT * FROM users WHERE id = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
             PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
    

            updateStmt.setString(1, fullName);
            updateStmt.setString(2, email);
            updateStmt.setString(3, phoneNumber);
            updateStmt.setDate(4, birthDate);
            updateStmt.setString(5, bio);
            updateStmt.setInt(6, id);
    

            int rowsUpdated = updateStmt.executeUpdate();
    
            if (rowsUpdated > 0) {
                // If update was successful, fetch the updated user
                selectStmt.setInt(1, id);
                ResultSet selectResultSet = selectStmt.executeQuery();
    
                if (selectResultSet.next()) {
                    User u = new User();
                    u.setId(selectResultSet.getInt("id"));
                    u.setFullname(selectResultSet.getString("fullname"));
                    u.setPhoneNumber(selectResultSet.getString("phone_number"));
                    u.setEmail(selectResultSet.getString("email"));
                    u.setBirthday(selectResultSet.getDate("birthday"));
                    u.setBio(selectResultSet.getString("bio"));
                    u.setPassword(selectResultSet.getString("password")); 
                    return u;
                }
            }
    
            return null;
    
        } catch (SQLException e) {
            System.err.println("Error editing user: " + e.getMessage());
            return null;
        }
    }
    public boolean changePassword(int userId, String currentPassword, String newPassword) {
        String verifyQuery = "SELECT * FROM users WHERE id = ? AND password = ?";
        String updateQuery = "UPDATE users SET password = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement verifyStmt = conn.prepareStatement(verifyQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
    
            // Verify if the current password matches
            verifyStmt.setInt(1, userId);
            verifyStmt.setString(2, currentPassword);
            
            ResultSet resultSet = verifyStmt.executeQuery();
            if (resultSet.next()) {
                // If current password is correct, update to the new password
                updateStmt.setString(1, newPassword);
                updateStmt.setInt(2, userId);
                
                int rowsUpdated = updateStmt.executeUpdate();
                return rowsUpdated > 0; // Return true if password update was successful
            }
            
        } catch (SQLException e) {
            System.err.println("Error changing password: " + e.getMessage());
        }
        
        return false; // Return false if current password did not match or any other issue
    }
    

    
    
}