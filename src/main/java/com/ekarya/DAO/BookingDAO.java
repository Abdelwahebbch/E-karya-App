package com.ekarya.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.ekarya.Models.Property;
import com.ekarya.Models.User;
import com.ekarya.utile.DatabaseConnection;

public class BookingDAO {
    public static boolean savePropertyDataToBooking(Property p, User u, LocalDate start, LocalDate end) {
        String query = "INSERT INTO booking (booking_id, landlord_id, user_id, property_id, start_date, end_date) " +
                       "VALUES (booking_id_seq.NEXTVAL, ?, ?, ?, ?, ?)";
        String updateQuery = "UPDATE properties SET status = ? WHERE id = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
    
            // Inserting booking
            stmt.setInt(1, p.getLandlord_id());
            stmt.setInt(2, u.getId());
            stmt.setString(3, p.getId());
            stmt.setDate(4, java.sql.Date.valueOf(start));
            stmt.setDate(5, java.sql.Date.valueOf(end));
    
            // Updating property status
            updateStmt.setInt(1, 1); // assuming 1 = booked
            updateStmt.setInt(2, Integer.parseInt(p.getId()));
    
            // Execute both statements
            int bookingResult = stmt.executeUpdate();
            int updateResult = updateStmt.executeUpdate();
    
            return bookingResult > 0 && updateResult > 0;
    
        } catch (SQLException e) {
            System.err.println("Error saving property: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
}
