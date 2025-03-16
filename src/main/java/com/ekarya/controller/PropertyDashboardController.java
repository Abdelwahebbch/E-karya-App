package com.ekarya.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PropertyDashboardController {

    @FXML
    private Button addPropertyButton;

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
    private ListView<?> notificationsList;

    @FXML
    private Text priceText;

    @FXML
    private VBox propertiesContainer;

    @FXML
    private Text propertyTitle;

    @FXML
    private Text titleText;

    @FXML
    void handleAddProperty(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addHome.fxml"));
            Parent root = loader.load();

            Stage filterStage = new Stage();
            filterStage.setTitle("Filtres");

            Scene scene = new Scene(root);
            filterStage.setScene(scene);

            filterStage.centerOnScreen();
            filterStage.showAndWait();

        } catch (IOException e) {
            System.err.println("Error loading filter interface: " + e.getMessage());
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
    void handleCloseButton(MouseEvent event) {
        // Get the current stage from the event source
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        // Close the stage
        stage.close();
    }

}
