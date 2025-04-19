package com.ekarya.DAO;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import com.ekarya.Models.House;
import com.ekarya.Models.Image;
import com.ekarya.Models.Property;
import com.ekarya.utile.DatabaseConnection;

/**
 * Data Access Object for property-related database operations.
 * Handles CRUD operations for houses and their associated images.
 */
public class PropertyDAO {
    
    /**
     * Save a house to the database and return its ID.
     * Uses a transaction and MAX(id) to reliably get the generated ID.
     * 
     * @param house The house object to save
     * @return The generated ID of the saved house
     * @throws SQLException If a database error occurs
     */
    public Long saveHouse(House house) throws SQLException {
        // First insert the house without specifying the ID
        String insertSql = "INSERT INTO houses (title, description, location, price, bedrooms, bathrooms, beds, max_guests) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        Long generatedId = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            
            // Start transaction
            conn.setAutoCommit(false);
            
            // Insert the house
            pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, house.getTitle());
            pstmt.setString(2, house.getDescription());
            pstmt.setString(3, house.getLocation());
            pstmt.setDouble(4, house.getPrice());
            pstmt.setInt(5, house.getBedrooms());
            pstmt.setInt(6, house.getBathrooms());
            pstmt.setInt(7, house.getBeds());
            pstmt.setInt(8, house.getMaxGuests());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                conn.rollback();
                throw new SQLException("Creating house failed, no rows affected.");
            }
            
            // Get the last inserted ID using a separate query
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT MAX(id) FROM houses");
            
            if (rs.next()) {
                generatedId = rs.getLong(1);
                conn.commit();
                return generatedId;
            } else {
                conn.rollback();
                throw new SQLException("Creating house failed, no ID obtained.");
            }
            
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error rolling back: " + ex.getMessage());
                }
            }
            System.err.println("Error saving house: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
            if (conn != null) {
                try { 
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("Error resetting auto-commit: " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * Save house images to the database.
     * Uses a transaction to ensure all images are saved or none.
     * 
     * @param images List of image objects to save
     * @throws SQLException If a database error occurs
     */
    public void saveHouseImages(List<Image> images) throws SQLException {
        if (images == null || images.isEmpty()) {
            return; // Nothing to save
        }
        
        String sql = "INSERT INTO images (house_id, url, caption, is_primary) VALUES (?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction
            
            pstmt = conn.prepareStatement(sql);
            
            for (Image image : images) {
                if (image.getHouseId() == null || image.getUrl() == null) {
                    continue; // Skip invalid images
                }
                
                pstmt.setLong(1, image.getHouseId());
                pstmt.setString(2, image.getUrl());
                pstmt.setString(3, image.getCaption() != null ? image.getCaption() : "");
                pstmt.setInt(4, image.isPrimary() ? 1 : 0);
                pstmt.addBatch();
            }
            
            pstmt.executeBatch();
            conn.commit();
            
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error rolling back: " + ex.getMessage());
                }
            }
            System.err.println("Error saving images: " + e.getMessage());
            throw e;
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
            if (conn != null) {
                try { 
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("Error resetting auto-commit: " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * Get all houses with their primary images.
     * 
     * @return List of house objects with their primary images
     * @throws SQLException If a database error occurs
     */
    public List<House> getAllHouses() throws SQLException {
        String sql = "SELECT h.*, " +
                     "(SELECT url FROM images WHERE house_id = h.id AND is_primary = 1 AND ROWNUM = 1) AS primary_image " +
                     "FROM houses h ORDER BY h.id DESC";
        
        List<House> houses = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                House house = new House();
                house.setId(rs.getLong("id"));
                house.setTitle(rs.getString("title"));
                house.setDescription(rs.getString("description"));
                house.setLocation(rs.getString("location"));
                house.setPrice(rs.getDouble("price"));
                house.setBedrooms(rs.getInt("bedrooms"));
                house.setBathrooms(rs.getInt("bathrooms"));
                house.setBeds(rs.getInt("beds"));
                house.setMaxGuests(rs.getInt("max_guests"));
                
                String primaryImageUrl = rs.getString("primary_image");
                if (primaryImageUrl != null) {
                    Image primaryImage = new Image();
                    primaryImage.setUrl(primaryImageUrl);
                    primaryImage.setPrimary(true);
                    house.setPrimaryImage(primaryImage);
                }
                
                houses.add(house);
            }
        } catch (SQLException e) {
            System.err.println("Error getting all houses: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
        }
        
        return houses;
    }
    public Property createPropertyFromResultSet(ResultSet rs) throws SQLException {
    Property property = new Property();
    property.setTitle(rs.getString("title"));
    property.setLocation(rs.getString("location"));
    property.setDescription(rs.getString("description"));
    property.setGuests(rs.getInt("guests"));
    property.setBedrooms(rs.getInt("bedrooms"));
    property.setBeds(rs.getInt("beds"));
    property.setBathrooms(rs.getInt("bathrooms"));
    property.setPrice(rs.getDouble("price"));
    
    // Handle image paths - convert from stored path to File
    String mainImagePath = rs.getString("main_image_path");
    if (mainImagePath != null && !mainImagePath.isEmpty()) {
        property.setMainImage(new File(mainImagePath));
    }
    
    // For additional images, you might need a separate query or join
    
    return property;
}
    /**
     * Get a house by ID with all its images.
     * 
     * @param id The ID of the house to retrieve
     * @return The house object with all its images, or null if not found
     * @throws SQLException If a database error occurs
     */
    public House getHouseById(Long id) throws SQLException {
        String houseSql = "SELECT * FROM houses WHERE id = ?";
        String imagesSql = "SELECT * FROM images WHERE house_id = ?";
        
        House house = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(houseSql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                house = new House();
                house.setId(rs.getLong("id"));
                house.setTitle(rs.getString("title"));
                house.setDescription(rs.getString("description"));
                house.setLocation(rs.getString("location"));
                house.setPrice(rs.getDouble("price"));
                house.setBedrooms(rs.getInt("bedrooms"));
                house.setBathrooms(rs.getInt("bathrooms"));
                house.setBeds(rs.getInt("beds"));
                house.setMaxGuests(rs.getInt("max_guests"));
            }
            
            rs.close();
            pstmt.close();
            
            if (house != null) {
                pstmt = conn.prepareStatement(imagesSql);
                pstmt.setLong(1, id);
                rs = pstmt.executeQuery();
                
                List<Image> images = new ArrayList<>();
                
                while (rs.next()) {
                    Image image = new Image();
                    image.setId(rs.getLong("id"));
                    image.setHouseId(rs.getLong("house_id"));
                    image.setUrl(rs.getString("url"));
                    image.setCaption(rs.getString("caption"));
                    image.setPrimaryFromInt(rs.getInt("is_primary"));
                    
                    images.add(image);
                    
                    if (image.isPrimary()) {
                        house.setPrimaryImage(image);
                    }
                }
                
                house.setImages(images);
            }
        } catch (SQLException e) {
            System.err.println("Error getting house by ID: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
        }
        
        return house;
    }
    
    /**
     * Debug method to print database structure information.
     * Useful for diagnosing database-related issues.
     */
    public void debugDatabaseStructure() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            
            // Check houses table structure
            System.out.println("=== HOUSES TABLE STRUCTURE ===");
            rs = stmt.executeQuery("SELECT column_name, data_type, data_length, nullable FROM user_tab_columns WHERE table_name = 'HOUSES' ORDER BY column_id");
            while (rs.next()) {
                System.out.println(rs.getString("column_name") + " - " + 
                                  rs.getString("data_type") + " - " +
                                  rs.getString("data_length") + " - " +
                                  rs.getString("nullable"));
            }
            rs.close();
            
            // Check images table structure
            System.out.println("\n=== IMAGES TABLE STRUCTURE ===");
            rs = stmt.executeQuery("SELECT column_name, data_type, data_length, nullable FROM user_tab_columns WHERE table_name = 'IMAGES' ORDER BY column_id");
            while (rs.next()) {
                System.out.println(rs.getString("column_name") + " - " + 
                                  rs.getString("data_type") + " - " +
                                  rs.getString("data_length") + " - " +
                                  rs.getString("nullable"));
            }
            rs.close();
            
            // Check if there are any identity columns
            System.out.println("\n=== IDENTITY COLUMNS ===");
            try {
                rs = stmt.executeQuery("SELECT table_name, column_name, generation_type FROM user_tab_identity_cols");
                boolean hasIdentityColumns = false;
                while (rs.next()) {
                    hasIdentityColumns = true;
                    System.out.println(rs.getString("table_name") + " - " + 
                                      rs.getString("column_name") + " - " +
                                      rs.getString("generation_type"));
                }
                if (!hasIdentityColumns) {
                    System.out.println("No identity columns found");
                }
                rs.close();
            } catch (SQLException e) {
                System.out.println("Could not query identity columns: " + e.getMessage());
            }
            
            // Check sequences
            System.out.println("\n=== SEQUENCES ===");
            rs = stmt.executeQuery("SELECT sequence_name, min_value, max_value, increment_by, last_number FROM user_sequences");
            boolean hasSequences = false;
            while (rs.next()) {
                hasSequences = true;
                System.out.println(rs.getString("sequence_name") + " - " + 
                                  rs.getString("min_value") + " - " +
                                  rs.getString("max_value") + " - " +
                                  rs.getString("increment_by") + " - " +
                                  rs.getString("last_number"));
            }
            if (!hasSequences) {
                System.out.println("No sequences found");
            }
            rs.close();
            
            // Check triggers
            System.out.println("\n=== TRIGGERS ===");
            rs = stmt.executeQuery("SELECT trigger_name, table_name, triggering_event, status FROM user_triggers");
            boolean hasTriggers = false;
            while (rs.next()) {
                hasTriggers = true;
                System.out.println(rs.getString("trigger_name") + " - " + 
                                  rs.getString("table_name") + " - " +
                                  rs.getString("triggering_event") + " - " +
                                  rs.getString("status"));
            }
            if (!hasTriggers) {
                System.out.println("No triggers found");
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking database structure: " + e.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
        }
    }
    
    /**
     * Delete a house and all its associated images.
     * Uses a transaction to ensure data consistency.
     * 
     * @param houseId The ID of the house to delete
     * @return true if the house was deleted, false otherwise
     * @throws SQLException If a database error occurs
     */
    public boolean deleteHouse(Long houseId) throws SQLException {
        String deleteImagesSql = "DELETE FROM images WHERE house_id = ?";
        String deleteHouseSql = "DELETE FROM houses WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction
            
            // First delete all images associated with the house
            pstmt = conn.prepareStatement(deleteImagesSql);
            pstmt.setLong(1, houseId);
            pstmt.executeUpdate();
            pstmt.close();
            
            // Then delete the house
            pstmt = conn.prepareStatement(deleteHouseSql);
            pstmt.setLong(1, houseId);
            int affectedRows = pstmt.executeUpdate();
            
            conn.commit();
            
            return affectedRows > 0;
            
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error rolling back: " + ex.getMessage());
                }
            }
            System.err.println("Error deleting house: " + e.getMessage());
            throw e;
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
            if (conn != null) {
                try { 
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("Error resetting auto-commit: " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * Update an existing house in the database.
     * 
     * @param house The house object with updated values
     * @return true if the house was updated, false otherwise
     * @throws SQLException If a database error occurs
     */
    public boolean updateHouse(House house) throws SQLException {
        String sql = "UPDATE houses SET title = ?, description = ?, location = ?, " +
                     "price = ?, bedrooms = ?, bathrooms = ?, beds = ?, max_guests = ? " +
                     "WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, house.getTitle());
            pstmt.setString(2, house.getDescription());
            pstmt.setString(3, house.getLocation());
            pstmt.setDouble(4, house.getPrice());
            pstmt.setInt(5, house.getBedrooms());
            pstmt.setInt(6, house.getBathrooms());
            pstmt.setInt(7, house.getBeds());
            pstmt.setInt(8, house.getMaxGuests());
            pstmt.setLong(9, house.getId());
            
            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating house: " + e.getMessage());
            throw e;
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
        }
    }
}