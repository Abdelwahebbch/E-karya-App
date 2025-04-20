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
                        resultSet.getInt("landlord_id")));
            }

        } catch (SQLException e) {
            System.err.println("Error when loading the properties: " + e.getMessage());
        }

        return properties;
    }

    public static List<Property> getProperties() {
        return new ArrayList<>(properties); // return a copy
    }

    // public static void saveProperty(Property property) {
    //     String query = "INSERT INTO properties (id, title, location, description, guests, bedrooms, beds, bathrooms, price, landlord_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    //     try (Connection conn = DatabaseConnection.getConnection();
    //          PreparedStatement stmt = conn.prepareStatement(query)) {
    
    //         stmt.setString(1, property.getId());
    //         stmt.setString(2, property.getTitle());
    //         stmt.setString(3, property.getLocation());
    //         stmt.setString(4, property.getDescription());
    //         stmt.setInt(5, property.getGuests());
    //         stmt.setInt(6, property.getBedrooms());
    //         stmt.setInt(7, property.getBeds());
    //         stmt.setInt(8, property.getBathrooms());
    //         stmt.setDouble(9, property.getPrice());
    //         stmt.setInt(10, property.getLandlord_id());
    
    //         stmt.executeUpdate();
    //     } catch (SQLException e) {
    //         System.err.println("Error saving property: " + e.getMessage());
    //     }
    // }
    public static boolean savePropertyDataToDataBase(Property p) {

        String query = "INSERT INTO properties (id, title, location, price_per_night, max_guests,max_beds,max_bedrooms,max_bathrooms,description, landlord_id) "
                +
                "VALUES (property_id_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, p.getTitle());
            stmt.setString(2, p.getLocation());
            stmt.setLong(3, (long) p.getPrice());
            stmt.setInt(4, p.getGuests());
            stmt.setInt(5, p.getBeds());
            stmt.setInt(6, p.getBedrooms());
            stmt.setInt(7, p.getBathrooms());
            stmt.setString(8, p.getDescription());
            stmt.setInt(9, p.getLandlord_id());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
            return false;
        }

    }
}
