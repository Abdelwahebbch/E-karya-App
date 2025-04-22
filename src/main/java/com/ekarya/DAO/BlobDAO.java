package com.ekarya.DAO;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import com.ekarya.Models.ImageModel;
import com.ekarya.utile.DatabaseConnection;

public class BlobDAO {
    public static ArrayList<ImageModel> AllDataBaseImages = new ArrayList<>();

    public static void insertBlob(String propertyId, File file) throws Exception {
        String sql = "INSERT INTO images (image_id, property_id, file_data) VALUES (image_id_seq.NEXTVAL, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                FileInputStream fis = new FileInputStream(file)) {

            pstmt.setInt(1, Integer.parseInt(propertyId));
            pstmt.setBinaryStream(2, fis, (int) file.length());
            pstmt.executeUpdate();

            System.out.println("Inserted BLOB for pro ID : " + propertyId);
        }
    }

    public File readBlob(int id) throws Exception {
        String sql = "SELECT file_data FROM images WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    InputStream input = rs.getBinaryStream("file_data");

                    // Create a temp file (you can give a better name/path if needed)
                    File outputFile = File.createTempFile("image_" + id + "_", ".bin");

                    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = input.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }

                    return outputFile;

                } else {
                    System.out.println("No record found with ID: " + id);
                    return null;
                }
            }
        }
    }

    public void updateBlob(int id, File newFile) throws Exception {
        String sql = "UPDATE images SET file_data = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                FileInputStream fis = new FileInputStream(newFile)) {

            pstmt.setBinaryStream(1, fis, (int) newFile.length());
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Updated BLOB for ID: " + id);
            } else {
                System.out.println("No record found to update for ID: " + id);
            }
        }
    }

    public static ArrayList<ImageModel> loadImagesForProperty(String propertyId) throws Exception {
        ArrayList<ImageModel> propertyImages = new ArrayList<>();

        String sql = "SELECT file_data FROM images WHERE property_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, propertyId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    InputStream input = rs.getBinaryStream("file_data");
                    File outputFile = File.createTempFile("img_" + propertyId + "_", ".bin");
                    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = input.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }
                    propertyImages.add(new ImageModel(propertyId, outputFile));
                }
            }
        }

        return propertyImages;
    }

}
