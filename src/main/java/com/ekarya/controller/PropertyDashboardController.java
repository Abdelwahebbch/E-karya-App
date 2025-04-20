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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PropertyDashboardController {

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

    @FXML
    private Text bedroomsText;

    @FXML
    private Text bedsText;

    @FXML
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
        }
    }

    @FXML
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

        Text priceText = new Text(property.getPrice() + " TND per night");
        priceText.setStyle("-fx-font-family: 'Montserrat';");

        VBox textVBox = new VBox(propertyNameText, priceText);
        HBox hbox = new HBox(10, textVBox);
        propertyButton.setGraphic(hbox);

        return propertyButton;
    }

    
}
