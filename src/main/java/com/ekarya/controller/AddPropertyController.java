package com.ekarya.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddPropertyController {

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
    private Button image1Button;

    @FXML
    private ImageView image1View;

    @FXML
    private Button image2Button;

    @FXML
    private ImageView image2View;

    @FXML
    private Button image3Button;

    @FXML
    private ImageView image3View;

    @FXML
    private Button image4Button;

    @FXML
    private ImageView image4View;

    @FXML
    private TextField locationField;

    @FXML
    private Button mainImageButton;

    @FXML
    private ImageView mainImageView;

    @FXML
    private TextField priceField;

    @FXML
    private Button submitButton;

    @FXML
    private TextField titleField;

        @FXML
    private void handleBackToDashboard(ActionEvent event) {
        try {
            // Load the home page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
            Parent homePageRoot = loader.load();
            
            // Get the current stage
            Stage stage = (Stage) submitButton.getScene().getWindow();
            
            // Set the home page scene
            Scene scene = new Scene(homePageRoot);
            stage.setScene(scene);
            stage.show();
            stage.setFullScreen(true);
        } catch (IOException e) {
            System.err.println("Error loading Dashboard.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
