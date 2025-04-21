package com.ekarya.controller;

import java.io.IOException;

import com.ekarya.Models.Property;
import com.ekarya.Models.User;
import com.ekarya.DAO.PropertyDAO;
import com.ekarya.FilePicker.FilePicker;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddPropertyController {

    private User currentUser = new User();

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
    public void initialize(User user) {
        this.currentUser = user;
    }

    @FXML
    void createPropertyButton(ActionEvent event) {
        Property data = collectPropertyData();

        if (data == null)
            return; // invalid data

        try {
            if (PropertyDAO.savePropertyDataToDataBase(data)) {
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
        try {
            property.setTitle(titleField.getText().trim());
            property.setLocation(locationField.getText().trim());
            property.setDescription(descriptionArea.getText().trim());
            property.setGuests(Integer.parseInt(guestsField.getText()));
            property.setBedrooms(Integer.parseInt(bedroomsField.getText()));
            property.setBeds(Integer.parseInt(bedsField.getText()));
            property.setBathrooms(Integer.parseInt(bathroomsField.getText()));
            property.setPrice(Double.parseDouble(priceField.getText()));
            property.setLandlord_id(currentUser.getId());
            System.out.println(currentUser.getId());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers for guests, bedrooms, beds, bathrooms, and price.");
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
        Node node = (Node) event.getSource(); // Works for Button, MenuItem, etc.
        Scene scene = node.getScene();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropertyDashboard.fxml"));
            Parent root = loader.load();

            PropertyDashboardController propertyDashboardController = loader.getController();
            propertyDashboardController.initialize(currentUser);

            // Appliquer opacité à 0 pour début de l'animation
            root.setOpacity(0);
            scene.setRoot(root);

            // Lancer animation de fondu
            FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.millis(1), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Failed to load dashboard.");
        }
    }

    @FXML
    void handelLoadImage(ActionEvent event) {
        Stage stage = (Stage) titleField.getScene().getWindow();

        FilePicker f = new FilePicker();
        f.chooseFile(stage);
       
    }

}
