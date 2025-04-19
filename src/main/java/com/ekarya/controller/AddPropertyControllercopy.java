package com.ekarya.controller;

import java.io.IOException;

import com.ekarya.Models.Property;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddPropertyControllercopy {
    Property p = new Property(0, null, null, 0, 0, 0, 0, 0, null);
    @FXML
    private ComboBox<?> bathroomsCombo;

    @FXML
    private ComboBox<?> bedroomsCombo;

    @FXML
    private ComboBox<?> bedsCombo;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private ComboBox<?> guestsCombo;
    @FXML
    private Button submitButton;

    @FXML
    private Button image1Button;

    @FXML
    private Button image2Button;
    @FXML
    private Button image4Button;
    @FXML
    private Button image3Button;
    @FXML
    private Button mainImageButton;
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
    private TextField locationField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField titleField;

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
       
    }

    // @FXML
    // private void handleSaveButton() {
    //     try {
    //         // Get values from form fields
    //         String propertyName = titleField.getText();
    //         String priceText = priceField.getText();

    //         // Validate input
    //         if (propertyName.isEmpty() || priceText.isEmpty()) {
    //             showAlert("Please fill in all required fields.");
    //             return;
    //         }

    //         // Parse price (with error handling)
    //         double price;
    //         try {
    //             price = Double.parseDouble(priceText);
    //         } catch (NumberFormatException e) {
    //             showAlert("Please enter a valid price.");
    //             return;
    //         }

    //         // Create a new Property object
    //         Property newProperty = new Property(1, "propEx", "Sfax", 1.1, 0, 0, 0, 0, "Discriiiiption");

    //         // Add the property to the main scene
    //         PropertyDashboardController.addPropertyToList(newProperty);

    //         // Close the form
    //         ((Stage) titleField.getScene().getWindow()).close();

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         showAlert("Error adding property: " + e.getMessage());
    //     }
    // }

    // private void showAlert(String message) {
    //     Alert alert = new Alert(Alert.AlertType.ERROR);
    //     alert.setTitle("Error");
    //     alert.setHeaderText(null);
    //     alert.setContentText(message);
    //     alert.showAndWait();
    // }
}
