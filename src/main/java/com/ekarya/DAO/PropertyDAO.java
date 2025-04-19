package com.ekarya.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ekarya.Models.Property;
import com.ekarya.utile.DatabaseConnection;

public class PropertyDAO {
    
    public static ArrayList<Property> properties = new ArrayList<>();
    
    public ArrayList<Property> LoadAllProperties() {
        String query = "SELECT * FROM properties";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                properties.add(new Property(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5), 
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getDouble(9) 
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error when loading the properties: " + e.getMessage());
        }

        return properties;
    }
}
