package com.ekarya.controller;

import java.io.IOException;
import java.util.List;

import com.ekarya.Models.House;
import com.ekarya.Models.Image;
import com.ekarya.Models.User;
import com.ekarya.Models.Property;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PropertyDetailController {

    @FXML
    private Label titleLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label bedroomsLabel;
    @FXML
    private Label bathroomsLabel;
    @FXML
    private Label bedsLabel;
    @FXML
    private Label guestsLabel;
    @FXML
    private ImageView mainImageView;
    @FXML
    private HBox thumbnailsContainer;
    @FXML
    private Button backButton;
    @FXML
    private Button bookButton;
    
    private House house;
    private MainController mainController;
    
    public void setHouse(House house) {
        this.house = house;
        displayHouseDetails();
    }
    
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    public void initData(Property property, User currentUser) {
    this.property = property;
    this.currentUser = currentUser;
    
    // Populate the UI with property details
    displayPropertyDetails();
}

private void displayPropertyDetails() {
    if (property == null) {
        System.err.println("Property is null in PropertyDetailController");
        return;
    }
    
    titleLabel.setText(property.getTitle());
    priceLabel.setText(String.format("$%.2f / night", property.getPrice()));
    locationLabel.setText(property.getLocation());
    descriptionLabel.setText(property.getDescription());
    bedroomsLabel.setText(String.valueOf(property.getBedrooms()));
    bathroomsLabel.setText(String.valueOf(property.getBathrooms()));
    guestsLabel.setText(String.valueOf(property.getGuests()));
    bedsLabel.setText(String.valueOf(property.getBeds()));
    
    // Load property image
    try {
        if (property.getMainImage() != null) {
            Image image = new Image(property.getMainImage().toURI().toString());
            propertyImageView.setImage(image);
        } else {
            loadPlaceholderImage();
        }
    } catch (Exception e) {
        System.err.println("Error loading property image: " + e.getMessage());
        loadPlaceholderImage();
    }
}
    private void displayHouseDetails() {
        if (house != null) {
            titleLabel.setText(house.getTitle());
            locationLabel.setText(house.getLocation());
            priceLabel.setText("$" + house.getPrice() + " / night");
            descriptionLabel.setText(house.getDescription());
            bedroomsLabel.setText(String.valueOf(house.getBedrooms()));
            bathroomsLabel.setText(String.valueOf(house.getBathrooms()));
            bedsLabel.setText(String.valueOf(house.getBeds()));
            guestsLabel.setText(String.valueOf(house.getMaxGuests()));
            
            // Load main image
            if (house.getPrimaryImage() != null && house.getPrimaryImage().getUrl() != null) {
                try {
                    javafx.scene.image.Image image = new javafx.scene.image.Image(house.getPrimaryImage().getUrl());
                    mainImageView.setImage(image);
                } catch (Exception e) {
                    System.err.println("Error loading main image: " + e.getMessage());
                }
            }
            
            // Load thumbnails if available
            List<Image> images = house.getImages();
            if (images != null && !images.isEmpty()) {
                thumbnailsContainer.getChildren().clear();
                
                for (Image img : images) {
                    try {
                        ImageView thumbnail = new ImageView();
                        thumbnail.setImage(new javafx.scene.image.Image(img.getUrl()));
                        thumbnail.setFitHeight(80);
                        thumbnail.setFitWidth(80);
                        thumbnail.setPreserveRatio(true);
                        
                        // Add click event to show this image as main image
                        thumbnail.setOnMouseClicked(event -> mainImageView.setImage(thumbnail.getImage()));
                        
                        thumbnailsContainer.getChildren().add(thumbnail);
                    } catch (Exception e) {
                        System.err.println("Error loading thumbnail: " + e.getMessage());
                    }
                }
            }
        }
    }
    
    @FXML
    private void handleBackButton() {
        try {
            // Load the main page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            Parent root = loader.load();
            
            // Get the controller and initialize it
            MainController controller = loader.getController();
            if (mainController != null && mainController.getCurrentUser() != null) {
                controller.initData(mainController.getCurrentUser());
            }
            
            // Show the main page
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.err.println("Error returning to main page: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleBookButton() {
        // Implement booking functionality
        try {
            // For now, just show an alert that booking is not implemented
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setTitle("Booking");
            alert.setHeaderText("Booking Not Implemented");
            alert.setContentText("The booking functionality is not yet implemented.");
            alert.showAndWait();
        } catch (Exception e) {
            System.err.println("Error handling booking: " + e.getMessage());
            e.printStackTrace();
        }
    }
}