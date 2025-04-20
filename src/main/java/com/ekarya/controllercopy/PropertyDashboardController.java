package com.ekarya.controller;

<<<<<<< HEAD
import com.ekarya.Models.Property;
import com.ekarya.Models.User;
import com.ekarya.utile.DatabaseConnection;
=======
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import com.ekarya.DAO.PropertyDAO;
import com.ekarya.Models.Property;

>>>>>>> 643576f3484ec0eacbe72e6e4b3369557d286e9e
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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

<<<<<<< HEAD
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
=======
    public Property currentProperty;
    
    @FXML
    private VBox propertiesContainer;

    @FXML
    private VBox notificationsContainer;

    @FXML
    private Text propertyTitle;

    @FXML
    private Text titleText;

    @FXML
    private Text locationText;

    @FXML
    private Text priceText;

    @FXML
    private Text guestsText;
>>>>>>> 643576f3484ec0eacbe72e6e4b3369557d286e9e

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
<<<<<<< HEAD
    private void handleBackToHome(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(new VBox(new Text("Home Placeholder")))); // Replace with actual navigation
            stage.show();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to go back", e.getMessage());
=======
    private Text bathroomsText;

    @FXML
    private Text descriptionText;

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
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    public void initialize() {
        PropertyDAO.loadAllProperties();
        refreshPropertyList();
    }

    private void refreshPropertyList() {
        propertiesContainer.getChildren().clear();
        for (Property p : PropertyDAO.properties) {
            addPropertyToList(p);
        }
    }

    public void addPropertyToList(Property property) {
        Button propertyButton = createPropertyButton(property);
        propertiesContainer.getChildren().add(propertyButton);
    }

    @FXML
    void handleAddPropertyButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddProperty.fxml"));
            Parent addPropertyRoot = loader.load();

            Stage stage = (Stage) titleText.getScene().getWindow();
            stage.setScene(new Scene(addPropertyRoot));
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading AddProperty.fxml: " + e.getMessage());
            e.printStackTrace();
>>>>>>> 643576f3484ec0eacbe72e6e4b3369557d286e9e
        }
    }

    @FXML
<<<<<<< HEAD
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
=======
    void handleBackToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            Parent mainRoot = loader.load();

            Stage stage = (Stage) titleText.getScene().getWindow();
            stage.setScene(new Scene(mainRoot));
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading Main.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleDeleteProperty(ActionEvent event) {
        // TODO be implemented
    }

    @FXML
    void handleEditProperty(ActionEvent event) {
        // TODO be implemented
    }

    public void loadPropertyData(String id) {
        for (Property p : PropertyDAO.getProperties()) {
            if (p.getId().equals(id)) {
                currentProperty = p;
                loadPropertyDetails();
                break;
            }
        }
    }

    private void loadPropertyDetails() {
        if (currentProperty != null) {
            locationText.setText(currentProperty.getLocation());
            descriptionText.setText(currentProperty.getDescription());
            titleText.setText(currentProperty.getTitle());
            bathroomsText.setText(String.valueOf(currentProperty.getBathrooms()));
            bedroomsText.setText(String.valueOf(currentProperty.getBedrooms()));
            bedsText.setText(String.valueOf(currentProperty.getBeds()));
            guestsText.setText(String.valueOf(currentProperty.getGuests()));
        }
    }

    private Button createPropertyButton(Property property) {
        Button propertyButton = new Button();
        propertyButton.setId("PropertyBtn_" + property.getId());
        propertyButton.setMaxWidth(Double.MAX_VALUE);
        propertyButton.setOnAction(event -> loadPropertyData(property.getId()));
        propertyButton.setStyle("-fx-background-color: white; -fx-border-color: #E0E0E0;");

        Text propertyNameText = new Text(property.getTitle());
        propertyNameText.setStyle("-fx-font-family: 'Montserrat';");

        Text priceText = new Text(property.getPrice() + " € per night");
        priceText.setStyle("-fx-font-family: 'Montserrat';");

        VBox textVBox = new VBox(propertyNameText, priceText);
        HBox hbox = new HBox(10, textVBox);
        propertyButton.setGraphic(hbox);

        return propertyButton;
    }

    
>>>>>>> 643576f3484ec0eacbe72e6e4b3369557d286e9e
}
