package com.ekarya.utile ;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    //private static final String URL = "jdbc:oracle:thin:@localhost:1521:FREE";
    private static final String URL = "jdbc:oracle:thin:@192.168.100.60:1521:FREE";
    //private static final String USER = "ekarya";
    private static final String USER = "c##test";
    private static final String PASSWORD = "0000";
    
    public static Connection connection;
    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load Oracle JDBC Driver
                Class.forName("oracle.jdbc.driver.OracleDriver");
                
                // Create connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connection established successfully");
            } catch (ClassNotFoundException e) {
                System.err.println("Oracle JDBC Driver not found: " + e.getMessage());
                throw new SQLException("Oracle JDBC Driver not found", e);
            } catch (SQLException e) {
                System.err.println("Connection failed: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}


// import java.sql.*;

// public class JdbcMethods {

//     // DB connection details
//     private static final String URL = "jdbc:oracle:thin:@localhost:1521:FREE";
//     private static final String USER = "ekarya";
//     private static final String PASSWORD = "0000";

//     // Load Oracle JDBC Driver
//     static {
//         try {
//             Class.forName("oracle.jdbc.OracleDriver");
//         } catch (ClassNotFoundException e) {
//             e.printStackTrace();
//         }
//     }

//     // --- User Methods ---
//     public static void createUser(int id,String fullname, String email, String phoneNumber, String password, String birthday, String bio) {
//         String insertQuery = "INSERT INTO users (id,fullname, email, phone_number, password, birthday, bio) " +
//                              "VALUES ('"+id+"','" + fullname + "', '" + email + "', '" + phoneNumber + "', '" + password + "', TO_DATE('" + birthday + "', 'YYYY-MM-DD'), '" + bio + "')";
//         executeUpdate(insertQuery);
//     }

//     public static void readUserById(int userId) {
//         String selectQuery = "SELECT * FROM users WHERE id = " + userId;
//         executeQuery(selectQuery);
//     }

//     public static void updateUser(int userId, String newPhoneNumber) {
//         String updateQuery = "UPDATE users SET phone_number = '" + newPhoneNumber + "' WHERE id = " + userId;
//         executeUpdate(updateQuery);
//     }

//     public static void deleteUser(int userId) {
//         String deleteQuery = "DELETE FROM users WHERE id = " + userId;
//         executeUpdate(deleteQuery);
//     }
 
//     // --- Property Methods ---
//     public static void createProperty(int id,String title, String location, String description, int maxGuests, int maxBeds, int maxBedrooms, double pricePerNight, double rating, int numberOfRatings) {
//         String insertQuery = "INSERT INTO properties (id,title, location, description, max_guests, max_beds, max_bedrooms, price_per_night, rating, number_of_ratings) " +
//                              "VALUES ('"+id+"','" + title + "', '" + location + "', '" + description + "', " + maxGuests + ", " + maxBeds + ", " + maxBedrooms + ", " + pricePerNight + ", " + rating + ", " + numberOfRatings + ")";
//         executeUpdate(insertQuery);
//     }

//     public static void readPropertyById(int propertyId) {
//         String selectQuery = "SELECT * FROM properties WHERE id = " + propertyId;
//         executeQuery(selectQuery);
//     }

//     public static void updateProperty(int propertyId, double newPricePerNight) {
//         String updateQuery = "UPDATE properties SET price_per_night = " + newPricePerNight + " WHERE id = " + propertyId;
//         executeUpdate(updateQuery);
//     }

//     public static void deleteProperty(int propertyId) {
//         String deleteQuery = "DELETE FROM properties WHERE id = " + propertyId;
//         executeUpdate(deleteQuery);
//     }

//     // --- Rent Proposal Methods ---
//     public static void createRentProposal(int id,int userId, int propertyId, String startDate, String endDate, int numberOfGuests, double totalPrice) {
//         String insertQuery = "INSERT INTO rent_proposals (id,user_id, property_id, start_date, end_date, number_of_guests, total_price) " +
//                              "VALUES (" + userId + "," + userId + ", " + propertyId + ", TO_DATE('" + startDate + "', 'YYYY-MM-DD'), TO_DATE('" + endDate + "', 'YYYY-MM-DD'), " + numberOfGuests + ", " + totalPrice + ")";
//         executeUpdate(insertQuery);
//     }

//     public static void readRentProposalById(int proposalId) {
//         String selectQuery = "SELECT * FROM rent_proposals WHERE id = " + proposalId;
//         executeQuery(selectQuery);
//     }

//     public static void updateRentProposal(int proposalId, double newTotalPrice) {
//         String updateQuery = "UPDATE rent_proposals SET total_price = " + newTotalPrice + " WHERE id = " + proposalId;
//         executeUpdate(updateQuery);
//     }

//     public static void deleteRentProposal(int proposalId) {
//         String deleteQuery = "DELETE FROM rent_proposals WHERE id = " + proposalId;
//         executeUpdate(deleteQuery);
//     }
  
//     // --- Review Methods ---
//     public static void createReview(int id ,int userId, int propertyId, double rating, String commentaire) {
//         String insertQuery = "INSERT INTO reviews (id,user_id, property_id, rating, commentaire) " +
//                              "VALUES (" + userId + "," + userId + ", " + propertyId + ", " + rating + ", '" + commentaire + "')";
//         executeUpdate(insertQuery);
//     }

//     public static void readReviewById(int id) {
//         String selectQuery = "SELECT * FROM reviews WHERE id = " + id;
//         executeQuery(selectQuery);
//     }

//     public static void updateReview(int reviewId, String newComment) {
//         String updateQuery = "UPDATE reviews SET commentaire = '" + newComment + "' WHERE id = " + reviewId;
//         executeUpdate(updateQuery);
//     }

//     public static void deleteReview(int id) {
//         String deleteQuery = "DELETE FROM reviews WHERE id = " + id;
//         executeUpdate(deleteQuery);
//     }

//     // --- Approval Methods ---
//     public static void createApproval(int id,int proposalId, int landlordId, String status) {
//         String insertQuery = "INSERT INTO approvals (id,proposal_id, landlord_id, status) " +
//                              "VALUES (" + proposalId + "," + proposalId + ", " + landlordId + ", '" + status + "')";
//         executeUpdate(insertQuery);
//     }

//     public static void readApprovalById(int approvalId) {
//         String selectQuery = "SELECT * FROM approvals WHERE id = " + approvalId;
//         executeQuery(selectQuery);
//     }

//     public static void updateApprovalStatus(int approvalId, String newStatus) {
//         String updateQuery = "UPDATE approvals SET status = '" + newStatus + "' WHERE id = " + approvalId;
//         executeUpdate(updateQuery);
//     }

//     public static void deleteApproval(int approvalId) {
//         String deleteQuery = "DELETE FROM approvals WHERE id = " + approvalId;
//         executeUpdate(deleteQuery);
//     }

//     // --- Common Methods ---
//     private static void executeUpdate(String query) {
//         try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//              Statement st = conn.createStatement()) {
//             System.out.println("Connected to Oracle Database!");
//             int rowsAffected = st.executeUpdate(query);
//             System.out.println(rowsAffected + " row(s) affected.");
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     private static void executeQuery(String query) {
//         try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//              Statement st = conn.createStatement();
//              ResultSet rs = st.executeQuery(query)) {
//             System.out.println("Connected to Oracle Database!");
//             while (rs.next()) {
//                 // Print out result based on your table's fields
//                 System.out.println(rs.getString(1)); // Adjust according to the column you want to display
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     // Main method to test all the methods
//     public static void main(String[] args) {
//         // // Test User Methods
//         // createUser(1,"John Doe", "john.doe@example.com", "1234567890", "password123", "1990-01-01", "Hello, I am John!");
//         // readUserById(1);
//         // updateUser(1, "0987654321");
//         // //deleteUser(1);

//         // // Test Property Methods
//         //  createProperty(1,"Beautiful Villa", "Tunisia", "A great place to stay", 4, 3, 2, 150.0, 4.5, 10);
//         // readPropertyById(1);
//         // updateProperty(1, 160.0);
//         // //deleteProperty(1);

//         // // Test Rent Proposal Methods
//         // createRentProposal(1,1, 1, "2025-01-01", "2025-01-10", 3, 450.0);
//         // readRentProposalById(1);
//         // updateRentProposal(1, 500.0);
//         // //deleteRentProposal(1);

//         // // Test Review Methods
//         // createReview(1,1, 1, 5.0, "Great property!");
//         // readReviewById(1);
//         // updateReview(1, "Amazing property!");
//         // //deleteReview(1);

//         // // Test Approval Methods
//         // createApproval(1,1, 1, "approved");
//         // readApprovalById(1);
//         // updateApprovalStatus(1, "rejected");
//         // //deleteApproval(1);
//     }
// }
