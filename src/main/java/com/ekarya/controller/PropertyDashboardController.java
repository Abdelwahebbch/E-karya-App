package com.ekarya.controller;

import java.io.IOException;

import com.ekarya.DAO.PropertyDAO;
import com.ekarya.Models.Property;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PropertyDashboardController {

    @FXML
    private Text bathroomsText;

    @FXML
    private Text bedroomsText;

    @FXML
    private Text bedsText;

    @FXML
    private Button deleteButton;

    @FXML
    private Text descriptionText;

    @FXML
    private Button editButton;

    @FXML
    private Text guestsText;

    @FXML
    private ImageView image1View;

    @FXML
    private ImageView image2View;

    @FXML
    private ImageView image3View;

    @FXML
    private ImageView image4View;

    @FXML
    private Text locationText;

    @FXML
    private ImageView mainImageView;

    @FXML
    private VBox notificationsContainer;

    @FXML
    private Text priceText;

    @FXML
    private VBox propertiesContainer;

    @FXML
    private Text propertyTitle;

    @FXML
    private Text titleText;

    @FXML
    void handleAddPropertyButton(ActionEvent event) {
        try {
            // Load the home page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddProperty.fxml"));
            Parent homePageRoot = loader.load();

            // Get the current stage
            Stage stage = (Stage) titleText.getScene().getWindow();

            // Set the home page scene
            Scene scene = new Scene(homePageRoot);
            stage.setScene(scene);
            stage.show();
            stage.setFullScreen(true);
        } catch (IOException e) {
            System.err.println("Error loading AddProperty.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleBackToHome(ActionEvent event) {
        try {
            // Load the home page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            Parent homePageRoot = loader.load();

            // Get the current stage
            Stage stage = (Stage) titleText.getScene().getWindow();

            // Set the home page scene
            Scene scene = new Scene(homePageRoot);
            stage.setScene(scene);
            stage.show();
            stage.setFullScreen(true);
        } catch (IOException e) {
            System.err.println("Error loading Main.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleDeleteProperty(ActionEvent event) {

    }

    @FXML
    void handleEditProperty(ActionEvent event) {

    }

    @FXML
    void loadPropertyData(ActionEvent event) {
        for (Property p : PropertyDAO.properties) {

        }
    }

    @FXML
    void loadPropertyDetails(ActionEvent event) {

    }

}
