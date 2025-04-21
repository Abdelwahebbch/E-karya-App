package com.ekarya.controller;

import java.io.IOException;

import com.ekarya.DAO.PropertyDAO;
import com.ekarya.FilePicker.FilePicker;
import com.ekarya.Models.Property;
import com.ekarya.Models.User;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PropertyDashboardController {
    User currentUser = new User();

    public Property currentProperty;

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
    public void initialize(User user) {
        this.currentUser = user;
        PropertyDAO.loadAllProperties();
        refreshPropertyList();
    }

    private void refreshPropertyList() {
        propertiesContainer.getChildren().clear();
        for (Property p : PropertyDAO.properties) {
            if (p.getLandlord_id() == currentUser.getId())
                addPropertyToList(p);
        }
    }

    public void addPropertyToList(Property property) {
        Button propertyButton = createPropertyButton(property);
        propertiesContainer.getChildren().add(propertyButton);
    }

    @FXML
    void handleAddPropertyButton(ActionEvent event) {
        Node node = (Node) event.getSource(); // Works for Button, MenuItem, etc.
        Scene scene = node.getScene();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddProperty.fxml"));
            Parent addPropertyRoot = loader.load();

            AddPropertyController addPropertyController = loader.getController();
            addPropertyController.initialize(currentUser);

            // Apply fade-in transition
            addPropertyRoot.setOpacity(0); // Start invisible
            scene.setRoot(addPropertyRoot); // Set the new root

            FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.millis(01), addPropertyRoot);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();
        } catch (IOException e) {
            System.err.println("Error loading AddProperty.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToHome(ActionEvent event) {
        Node node = (Node) event.getSource(); // Works for Button, MenuItem, etc.
        Scene scene = node.getScene();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            Parent root = loader.load();

            MainController controller = loader.getController();
            controller.initData(currentUser);

            // Apply fade-in transition
            root.setOpacity(0); // Start invisible
            scene.setRoot(root); // Set the new root

            FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.millis(01), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();

        } catch (IOException e) {
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
            priceText.setText(currentProperty.getPrice() + "");
        }
    }

    private Button createPropertyButton(Property property) {
        Button propertyButton = new Button();
        propertyButton.setId("PropertyBtn_" + property.getId());
        propertyButton.setMaxWidth(Double.MAX_VALUE);
        propertyButton.setOnAction(event -> loadPropertyData(property.getId()));
        propertyButton.setStyle("""
                    -fx-background-color: white;
                    -fx-border-color: #E0E0E0;
                    -fx-border-radius: 12;
                    -fx-background-radius: 12;
                    -fx-alignment: center-left;
                    -fx-padding: 10;
                    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.04), 8, 0, 0, 2);
                """);

        Text propertyNameText = new Text(property.getTitle());
        propertyNameText.setStyle(
                "-fx-font-family: 'Montserrat'; -fx-font-weight: bold; -fx-font-size: 14; -fx-fill: #000000;");

        Text priceText = new Text(property.getPrice() + " TND per night");
        priceText.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 12; -fx-fill: #555555;");

        VBox textVBox = new VBox(propertyNameText, priceText);
        HBox hbox = new HBox(10, textVBox);
        propertyButton.setGraphic(hbox);

        return propertyButton;

    }

  

}
