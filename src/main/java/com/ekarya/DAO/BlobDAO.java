package com.ekarya.DAO;

import java.io.*;
import java.sql.*;

import com.ekarya.utile.DatabaseConnection;

public class BlobDAO {

    public void insertBlob(int id, String name, File file) throws Exception {
        String sql = "INSERT INTO images (id, name, file_data) VALUES (images_id_seq.NEXTVAL, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                FileInputStream fis = new FileInputStream(file)) {

            pstmt.setString(1, name);
            pstmt.setBinaryStream(2, fis, (int) file.length());
            pstmt.executeUpdate();

            System.out.println("Inserted BLOB for ID: " + id);
        }
    }

    public void readBlob(int id, File outputFile) throws Exception {
        String sql = "SELECT file_data FROM images WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    try (InputStream input = rs.getBinaryStream("file_data");
                            FileOutputStream fos = new FileOutputStream(outputFile)) {

                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = input.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }

                        System.out.println("BLOB read and saved to: " + outputFile.getAbsolutePath());
                    }
                } else {
                    System.out.println("No record found with ID: " + id);
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
}
