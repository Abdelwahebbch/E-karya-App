package com.ekarya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ekarya.Models.User;

public class RentalInterfaceController {

    @FXML
    private VBox rentedHomesContainer;

    @FXML
    private VBox notificationsContainer;

    @FXML
    private Button searchPropertiesButton;

    @FXML
    private Button reviewButton;

    @FXML
    private Text propertyTitle;

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
    private HBox ratingStarsContainer;

    @FXML
    private TextArea commentsField;

    private int currentRating = 3; // Default rating (3 stars)
    private List<Button> ratingStars = new ArrayList<>();
    private User currentUser;

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the controller, load data, etc.
        loadRentalData();
        setupRatingStars();
    }

    /**
     * Handles the back button action to navigate back to the home page
     */
    @FXML
    private void handleBackToHome(ActionEvent event) {
        try {
            // Get the source of the event instead of using searchPropertiesButton
            Button sourceButton = (Button) event.getSource();
            
            // Load the home page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            Parent homePageRoot = loader.load();
            
            // Get the current stage using the source button
            Stage stage = (Stage) sourceButton.getScene().getWindow();
            
            // Set the home page scene
            Scene scene = new Scene(homePageRoot);
            stage.setScene(scene);
            stage.show();
            stage.setFullScreen(true);
        } catch (IOException e) {
            System.err.println("Error loading HomePage.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads rental details when a property is selected from the list
     */
    @FXML
    private void loadRentalDetails(ActionEvent event) {
        // Get the source button that was clicked
        //Button clickedButton = (Button) event.getSource();
        
        // Extract property information from the button or its data
        // This is a simplified example - in a real app, you would get the property ID
        // and load the data from your data model or database
        String propertyName = "Selected Property";
        
        // For demonstration, we'll just update the UI with some hardcoded values
        propertyTitle.setText(propertyName + " Details");
        
        // In a real application, you would load the actual property data here
        // and update all the fields with the property's information
    }



    /**
     * Handles submitting a review for the property
     */
    @FXML
    private void handleSubmitReview(ActionEvent event) {
        String comments = commentsField.getText();
        
        // In a real application, you would:
        // 1. Validate the input
        // 2. Submit the review to your data model or database
        // 3. Show a confirmation message
        
        System.out.println("Submitting review with rating: " + currentRating + " and comments: " + comments);
        
        // Clear the comments field after submission
        commentsField.clear();
        
        // Show a confirmation message (in a real app, you might use a dialog)
        // For now, we'll just update the button text temporarily
        reviewButton.setText("Review Submitted!");
        
        // Reset the button text after a delay
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                javafx.application.Platform.runLater(() -> {
                    reviewButton.setText("Submit Review");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    // Add to RentalInterfaceController
    public void initData(User user) {
        this.currentUser = user;
        // Initialize rental interface with user data
    }
    /**
     * Sets up the rating stars with click handlers
     */
    private void setupRatingStars() {
        // Clear any existing stars
        ratingStarsContainer.getChildren().clear();
        ratingStars.clear();
        
        // Create 5 star buttons
        for (int i = 1; i <= 5; i++) {
            final int rating = i;
            Button starButton = new Button("★");
            starButton.setStyle("-fx-background-color: transparent; -fx-text-fill: " + 
                               (i <= currentRating ? "gold" : "#cccccc") + ";");
            
            starButton.setOnAction(event -> {
                setRating(rating);
            });
            
            ratingStars.add(starButton);
            ratingStarsContainer.getChildren().add(starButton);
        }
    }

    /**
     * Sets the rating and updates the star display
     */
    private void setRating(int rating) {
        currentRating = rating;
        
        // Update star colors
        for (int i = 0; i < ratingStars.size(); i++) {
            Button star = ratingStars.get(i);
            star.setStyle("-fx-background-color: transparent; -fx-text-fill: " + 
                         (i < rating ? "gold" : "#cccccc") + ";");
        }
    }

    /**
     * Loads sample rental data for demonstration purposes
     */
    private void loadRentalData() {
        // In a real application, you would load this data from a database or service
        // This is just for demonstration purposes
        
        // Set default property details
        propertyTitle.setText("Property Details");
        titleText.setText("Cozy apartment in the heart of Paris");
        locationText.setText("Paris, Île-de-France, France");
        priceText.setText("104 TND");
        guestsText.setText("4 guests");
        bedroomsText.setText("2 bedrooms");
        bedsText.setText("3 beds");
        bathroomsText.setText("1 bathroom");
        descriptionText.setText("Discover this charming apartment located in the heart of Paris. " +
                "Perfectly situated to explore the city, this comfortable space offers everything " +
                "you need for a pleasant stay. Enjoy the equipped kitchen, bright living room, " +
                "and cozy bedroom. Just steps away from major attractions and public transportation.");
    }
}