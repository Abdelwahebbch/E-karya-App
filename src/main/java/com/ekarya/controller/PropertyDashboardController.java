package com.ekarya.controller;

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

import java.io.IOException;

public class PropertyDashboardController {

    @FXML
    private VBox propertiesContainer;

    @FXML
    private VBox notificationsContainer;

    @FXML
    private Button addPropertyButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

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

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the controller, load data, etc.
        loadPropertyData();
    }

    /**
     * Handles the back button action to navigate back to the home page
     */
    @FXML
    private void handleBackToHome(ActionEvent event) {
        try {
            // Load the home page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            Parent homePageRoot = loader.load();
            
            // Get the current stage
            Stage stage = (Stage) addPropertyButton.getScene().getWindow();
            
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
     * Switches the interface to the home page
     * This method can be called from anywhere to navigate to the home page
     */
    @FXML
    public void ToAddHome() {
        try {
            // Load the home page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddProprety.fxml"));
            Parent homePageRoot = loader.load();
            
            // Get the current stage
            Stage stage = (Stage) addPropertyButton.getScene().getWindow();
            
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
     * Loads property details when a property is selected from the list
     */
    @FXML
    private void loadPropertyDetails(ActionEvent event) {
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
     * Handles adding a new property
     */
    @FXML
    private void handleAddProperty(ActionEvent event) {
        try {
            // Load the add property form FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ekarya/view/AddPropertyForm.fxml"));
            Parent addPropertyRoot = loader.load();
            
            // Get the current stage
            Stage stage = (Stage) addPropertyButton.getScene().getWindow();
            
            // Set the add property form scene
            Scene scene = new Scene(addPropertyRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading AddPropertyForm.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles editing the currently selected property
     */
    @FXML
    private void handleEditProperty(ActionEvent event) {
        try {
            // Load the edit property form FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ekarya/view/EditPropertyForm.fxml"));
            Parent editPropertyRoot = loader.load();
            
            // Get the controller and pass the current property data
            // EditPropertyFormController controller = loader.getController();
            // controller.setPropertyData(currentPropertyData);
            
            // Get the current stage
            Stage stage = (Stage) editButton.getScene().getWindow();
            
            // Set the edit property form scene
            Scene scene = new Scene(editPropertyRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading EditPropertyForm.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles deleting the currently selected property
     */
    @FXML
    private void handleDeleteProperty(ActionEvent event) {
        // In a real application, you would:
        // 1. Show a confirmation dialog
        // 2. Delete the property from your data model or database if confirmed
        // 3. Update the UI to reflect the deletion
        
        System.out.println("Delete property functionality would be implemented here");
        
        // For demonstration purposes, we'll just clear the property details
        propertyTitle.setText("Property Details");
        titleText.setText("");
        locationText.setText("");
        priceText.setText("");
        guestsText.setText("");
        bedroomsText.setText("");
        bedsText.setText("");
        bathroomsText.setText("");
        descriptionText.setText("");
    }

    /**
     * Loads sample property data for demonstration purposes
     */
    private void loadPropertyData() {
        // In a real application, you would load this data from a database or service
        // This is just for demonstration purposes
        
        // Set default property details
        propertyTitle.setText("Property Details");
        titleText.setText("Cozy apartment in the heart of Paris");
        locationText.setText("Paris, Île-de-France, France");
        priceText.setText("104 €");
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