package com.ekarya.controller;

import java.io.IOException;

import com.ekarya.Models.Property;
import com.ekarya.DAO.PropertyDAO;
import com.ekarya.DAO.UserDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AddPropertyController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField guestsField;
    @FXML
    private TextField bedroomsField;
    @FXML
    private TextField bedsField;
    @FXML
    private TextField bathroomsField;
    @FXML
    private TextArea descriptionArea;

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
    private Button submitButton;

    @FXML
    void createPropertyButton(ActionEvent event) {
        Property data = collectPropertyData();

        if (data == null)
            return; // invalid data

        try {
            if (UserDAO.savePropertyDataToDataBase(data)) {
                // Success alert
                PropertyDAO.properties.add(data);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Property listing created successfully!");
                alert.showAndWait();

                // Redirect to dashboard
                handleBackToDashboard(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save property. Please try again.");
        }
    }

    public Property collectPropertyData() {
        Property property = new Property();

        property.setTitle(titleField.getText().trim());
        property.setLocation(locationField.getText().trim());
        property.setDescription(descriptionArea.getText().trim());

        try {
            if (!guestsField.getText().isEmpty())
                property.setGuests(Integer.parseInt(guestsField.getText()));
            if (!bedroomsField.getText().isEmpty())
                property.setBedrooms(Integer.parseInt(bedroomsField.getText()));
            if (!bedsField.getText().isEmpty())
                property.setBeds(Integer.parseInt(bedsField.getText()));
            if (!bathroomsField.getText().isEmpty())
                property.setBathrooms(Integer.parseInt(bathroomsField.getText()));
            if (!priceField.getText().isEmpty())
                property.setPrice(Double.parseDouble(priceField.getText()));
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers for guests, bedrooms, beds, bathrooms, and price.");
            return null;
        }

        // Validate required fields
        if (property.getTitle().isEmpty() || property.getLocation().isEmpty() || property.getDescription().isEmpty()) {
            showAlert("Missing Information", "Please fill in all required fields.");
            return null;
        }

        // TODO: Handle images if needed

        return property;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void handleBackToDashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropertyDashboard.fxml")); // corrected
                                                                                                        // filename
            Parent dashboardRoot = loader.load();

            Stage stage = (Stage) submitButton.getScene().getWindow();
            Scene scene = new Scene(dashboardRoot);
            stage.setScene(scene);
            stage.setFullScreen(true);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Failed to load dashboard.");
        }
    }
}
