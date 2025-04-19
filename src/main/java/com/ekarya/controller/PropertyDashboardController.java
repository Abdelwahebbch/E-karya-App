package com.ekarya.controller;

import com.ekarya.Models.Property;
import com.ekarya.Models.User;
import com.ekarya.utile.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PropertyDashboardController {

    @FXML private VBox propertiesContainer;
    @FXML private VBox notificationsContainer;
    @FXML private Button addPropertyButton;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    @FXML private Text propertyTitle;
    @FXML private ImageView mainImageView;
    @FXML private ImageView image1View;
    @FXML private ImageView image2View;
    @FXML private ImageView image3View;
    @FXML private ImageView image4View;
    @FXML private Text titleText;
    @FXML private Text locationText;
    @FXML private Text priceText;
    @FXML private Text guestsText;
    @FXML private Text bedroomsText;
    @FXML private Text bedsText;
    @FXML private Text bathroomsText;
    @FXML private Text descriptionText;

    private int currentPropertyId = 1;
    private User currentUser;

    @FXML
    private void initialize() {
        loadPropertyData();
    }

    public void initData(User user) {
        this.currentUser = user;
        // Load user-specific data here
    }
    @FXML
    private void loadPropertyData(ActionEvent event) {
        loadPropertyData(); // This calls your actual data loader
    }

    @FXML
    private void handleAddPropertyButton(ActionEvent event) {
        showAlert(Alert.AlertType.INFORMATION, "Add Property", "Not implemented", "You clicked Add Property.");
    }

    @FXML
    private void handleBackToHome(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(new VBox(new Text("Home Placeholder")))); // Replace with actual navigation
            stage.show();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to go back", e.getMessage());
        }
    }

    @FXML
    private void handleDeleteProperty(ActionEvent event) {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm Deletion");
        confirmDialog.setHeaderText("Delete Property");
        confirmDialog.setContentText("Are you sure you want to delete this property? This action cannot be undone.");

        Optional<ButtonType> result = confirmDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (deletePropertyFromDatabase(currentPropertyId)) {
                clearPropertyFields();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Property Deleted", "The property has been successfully deleted.");
                refreshPropertyList();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Deletion Failed", "Could not delete the property. Please try again.");
            }
        }
    }

    private void loadPropertyData() {
        String selectQuery = "SELECT * FROM properties WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectQuery)) {

            stmt.setInt(1, currentPropertyId);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                titleText.setText(result.getString("title"));
                propertyTitle.setText(result.getString("title"));
                locationText.setText(result.getString("location"));
                priceText.setText(result.getString("price_per_night"));
                guestsText.setText(String.valueOf(result.getInt("max_guests")));
                bedroomsText.setText(String.valueOf(result.getInt("max_bedrooms")));
                bedsText.setText(String.valueOf(result.getInt("max_beds")));
                bathroomsText.setText(String.valueOf(result.getInt("max_bathrooms")));
                descriptionText.setText(result.getString("description") != null ? result.getString("description") : "No Description available");

                // Load main image if available
                String mainImageUrl = result.getString("main_image_url");
                if (mainImageUrl != null) {
                    File file = new File("src/main/resources/" + mainImageUrl);
                    if (file.exists()) {
                        mainImageView.setImage(new Image(file.toURI().toString()));
                    }
                }

                // TODO: load thumbnails image1View - image4View
            }

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Could not load property", e.getMessage());
        }
    }

    private void refreshPropertyList() {
        titleText.setText("");
        locationText.setText("");
        priceText.setText("");
        guestsText.setText("");
        bedroomsText.setText("");
        bedsText.setText("");
        bathroomsText.setText("");
        descriptionText.setText("");
        propertyTitle.setText("Property Details");
    }

    private void clearPropertyFields() {
        refreshPropertyList();
        currentPropertyId = -1;
        mainImageView.setImage(null);
        image1View.setImage(null);
        image2View.setImage(null);
        image3View.setImage(null);
        image4View.setImage(null);
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
            return false;
        }
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
