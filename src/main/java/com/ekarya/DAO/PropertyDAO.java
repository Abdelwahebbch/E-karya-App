package com.ekarya.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ekarya.Models.Property;
import com.ekarya.utile.DatabaseConnection;

public class PropertyDAO {

    public static final ArrayList<Property> properties = new ArrayList<>();

    public static ArrayList<Property> loadAllProperties() {
        String query = "SELECT * FROM properties";
        properties.clear();

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                properties.add(new Property(
                        resultSet.getString("id"),
                        resultSet.getString("title"),
                        resultSet.getString("location"),
                        resultSet.getString("description"),
                        resultSet.getInt("max_guests"),
                        resultSet.getInt("max_bedrooms"),
                        resultSet.getInt("max_beds"),
                        resultSet.getInt("max_bathrooms"),
                        resultSet.getDouble("price_per_night"),
                        resultSet.getString("landlord_id")));
            }

        } catch (SQLException e) {
            System.err.println("Error when loading the properties: " + e.getMessage());
        }

        return properties;
    }

    public static List<Property> getProperties() {
        return new ArrayList<>(properties); // return a copy
    }

    public static void saveProperty(Property property) {
        String query = "INSERT INTO properties (id, title, location, description, guests, bedrooms, beds, bathrooms, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setString(1, property.getId());
            stmt.setString(2, property.getTitle());
            stmt.setString(3, property.getLocation());
            stmt.setString(4, property.getDescription());
            stmt.setInt(5, property.getGuests());
            stmt.setInt(6, property.getBedrooms());
            stmt.setInt(7, property.getBeds());
            stmt.setInt(8, property.getBathrooms());
            stmt.setDouble(9, property.getPrice());
    
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving property: " + e.getMessage());
        }
    }
    
}
