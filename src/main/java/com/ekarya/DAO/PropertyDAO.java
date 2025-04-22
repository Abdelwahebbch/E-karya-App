package com.ekarya.DAO;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ekarya.Models.Property;
import com.ekarya.controller.AddPropertyController;
import com.ekarya.utile.DatabaseConnection;

public class PropertyDAO {

    public static final ArrayList<Property> properties = new ArrayList<>();
    private static ArrayList<File> propImages = AddPropertyController.getImages();

    public static ArrayList<Property> loadSpecificPropertys(String destination, LocalDate start, LocalDate end,
            String guests) {
        ArrayList<Property> list = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM properties WHERE status = 0"); // status = 0 means
                                                                                              // available
        List<Object> parameters = new ArrayList<>();

        if (destination != null && !destination.trim().isEmpty()) {
            query.append(" AND location = ?");
            parameters.add(destination);
        }

        if (guests != null && !guests.trim().isEmpty() && Integer.parseInt(guests) > 0) {
            query.append(" AND max_guests >= ?");
            parameters.add(Integer.parseInt(guests));
        }

        if (start != null && end != null) {
            query.append(" AND id NOT IN (")
                    .append("SELECT property_id FROM booking ")
                    .append("WHERE (start_date <= ? AND end_date >= ?))");
            parameters.add(java.sql.Date.valueOf(end));
            parameters.add(java.sql.Date.valueOf(start));
        }

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                Object param = parameters.get(i);
                if (param instanceof String) {
                    stmt.setString(i + 1, (String) param);
                } else if (param instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) param);
                } else if (param instanceof java.sql.Date) {
                    stmt.setDate(i + 1, (java.sql.Date) param);
                }
            }

            ResultSet resultSet = stmt.executeQuery();
            list = getPropertiesFromResultSet(resultSet);

        } catch (SQLException e) {
            System.err.println("Error during property search: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

    public static ArrayList<Property> getPropertiesFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Property> properties = new ArrayList<>();

        while (rs.next()) {
            Property p = new Property(
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("location"),
                    rs.getString("description"),
                    rs.getInt("max_guests"),
                    rs.getInt("max_bedrooms"),
                    rs.getInt("max_beds"),
                    rs.getInt("max_bathrooms"),
                    rs.getDouble("price_per_night"),
                    rs.getInt("landlord_id"),
                    rs.getInt("status"));

            properties.add(p);
        }

        return properties;
    }

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
                        resultSet.getInt("landlord_id"),
                        resultSet.getInt("status")));
            }

        } catch (SQLException e) {
            System.err.println("Error when loading the properties: " + e.getMessage());
        }

        return properties;
    }

    public static List<Property> getProperties() {
        return new ArrayList<>(properties); // return a copy
    }

    public static boolean savePropertyDataToDataBase(Property p) throws Exception {
        String query = "INSERT INTO properties (id, title, location, price_per_night, max_guests, max_beds, max_bedrooms, max_bathrooms, description, landlord_id, status) "
                     + "VALUES (property_id_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 query, new String[] { "id" }) // <-- ask Oracle to return the generated ID
        ) {
            stmt.setString(1, p.getTitle());
            stmt.setString(2, p.getLocation());
            stmt.setDouble(3, p.getPrice());
            stmt.setInt(4, p.getGuests());
            stmt.setInt(5, p.getBeds());
            stmt.setInt(6, p.getBedrooms());
            stmt.setInt(7, p.getBathrooms());
            stmt.setString(8, p.getDescription());
            stmt.setInt(9, p.getLandlord_id());
            stmt.setInt(10, p.getStatus());
    
            int affectedRows = stmt.executeUpdate();
    
            if (affectedRows == 0) {
                throw new SQLException("Inserting property failed, no rows affected.");
            }
    
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1); // You can use this ID to insert images
                    for (File f : propImages) {
                        BlobDAO.insertBlob(generatedId+"", f); // Assuming insertBlob accepts int ID
                    }
                    return true;
                } else {
                    throw new SQLException("Inserting property failed, no ID returned.");
                }
            }
    
        } catch (SQLException e) {
            System.err.println("Error saving property: " + e.getMessage());
            return false;
        }
    }
}
