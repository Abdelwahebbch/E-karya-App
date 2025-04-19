package com.ekarya.controller;

import com.ekarya.Models.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.ekarya.utile.DatabaseConnection;

public class PropertyDashboardControllercopy {

    @FXML
    private VBox propertiesContainer;

    @FXML
    private VBox notificationsContainer;

    @FXML
    private Button addPropertyButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Text propertyTitle;

    @FXML
    private ImageView mainImageView;

    @FXML
    private ImageView image1View;

    @FXML
    private ImageView image2View;

    @FXML
    private ImageView image3View;

    @FXML
    private ImageView image4View;

    @FXML
    private Text titleText;

    @FXML
    private Text locationText;

    @FXML
    private Text priceText;

    @FXML
    private Text guestsText;

    @FXML
    private Text bedroomsText;

    @FXML
    private Text bedsText;

    @FXML
    private Text bathroomsText;

    @FXML
    private Text descriptionText;
    int currentPropertyId = 1;

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */

    @FXML
    private void initialize() {
        //loadPropertyData(1);
    }

    /**
     * Handles the back button action to navigate back to the home page
     */
    @FXML
    private void handleBackToHome(ActionEvent event) {
       
    }

    @FXML
    public void handleAddPropertyButton() {
     
    }

    @FXML
    private void handleDeleteProperty(ActionEvent event) {

        int propertyId = currentPropertyId;

        // 1. Show a confirmation dialog
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm Deletion");
        confirmDialog.setHeaderText("Delete Property");
        confirmDialog.setContentText("Are you sure you want to delete this property? This action cannot be undone.");

        Optional<ButtonType> result = confirmDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            if (deletePropertyFromDatabase(propertyId)) {

                clearPropertyFields();

                showAlert(Alert.AlertType.INFORMATION, "Success",
                        "Property Deleted", "The property has been successfully deleted.");

                refreshPropertyList();
            } else {
                // Show error message if deletion failed
                showAlert(Alert.AlertType.ERROR, "Error",
                        "Deletion Failed", "Could not delete the property. Please try again.");
            }
        }
    }

    private void refreshPropertyList() {

        // TODO : Mazelet bech tet5dem
        propertyTitle.setText("Property Details");
        titleText.setText("");
        locationText.setText("");
        priceText.setText("");
        guestsText.setText("");
        bedroomsText.setText("");
        bedsText.setText("");
        bathroomsText.setText("");
        descriptionText.setText("");
    }

    private void clearPropertyFields() {
        propertyTitle.setText("Property Details");
        titleText.setText("");
        locationText.setText("");
        priceText.setText("");
        guestsText.setText("");
        bedroomsText.setText("");
        bedsText.setText("");
        bathroomsText.setText("");
        descriptionText.setText("");
        currentPropertyId = -1;
    }

    private boolean deletePropertyFromDatabase(int propertyId) {
        String deleteQuery = "DELETE FROM properties WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

            pstmt.setInt(1, propertyId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting property: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Loads sample property data for demonstration purposes
     */
     void loadPropertyData(ActionEvent event) {
        int id = 1 ;
        String selectQuery = "SELECT * FROM properties WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement selectStmt = conn.prepareStatement(selectQuery);) {

            selectStmt.setInt(1, id);
            ResultSet result = selectStmt.executeQuery();
            if (result.next()) {
                propertyTitle.setText(result.getString("title"));
                titleText.setText(result.getString("title")); // Note: This is hardcoded
                locationText.setText(result.getString("location"));
                priceText.setText(result.getString("price_per_night"));
                guestsText.setText(String.valueOf(result.getInt("max_guests")));
                bedroomsText.setText(String.valueOf(result.getInt("max_bedrooms")));
                bedsText.setText(String.valueOf(result.getInt("max_beds")));
                bathroomsText.setText(String.valueOf(result.getInt("max_bathrooms")));

                // Handle potentially null description
                String description = result.getString("description");
                descriptionText.setText(description != null ? description : "No Description available ");
            }
        } catch (SQLException e) {
            System.err.println("Error loading property data: " + e.getMessage());

        }
    }

    public static void addPropertyToList(Property newProperty) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPropertyToList'");
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
