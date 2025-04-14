package com.ekarya.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ekarya.Models.User;
import com.ekarya.utile.DatabaseConnection;


public class UserDAO {
    private final DatabaseConnection dbConnection;
    
    public UserDAO() {
        this.dbConnection = new DatabaseConnection();
    }
    /*public User createUser()
    {

    }*/
    
    // public void update(User user) throws SQLException {
    //     String query = "UPDATE users SET username = ?, email = ? WHERE id = ?";
        
    //     try (Connection conn = dbConnection.getConnection();
    //          PreparedStatement stmt = conn.prepareStatement(query)) {
            
    //         stmt.setString(1, user.getUsername());
    //         stmt.setString(2, user.getEmail());
    //         stmt.setInt(3, user.getId());
    //         // Set other parameters as needed
            
    //         stmt.executeUpdate();
    //     }
    // }
    
    // public void delete(int id) throws SQLException {
    //     String query = "DELETE FROM users WHERE id = ?";
        
    //     try (Connection conn = dbConnection.getConnection();
    //          PreparedStatement stmt = conn.prepareStatement(query)) {
            
    //         stmt.setInt(1, id);
    //         stmt.executeUpdate();
    //     }
    // }
}