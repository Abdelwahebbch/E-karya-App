package com.ekarya.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ekarya.DAO.PropertyDAO;
import com.ekarya.Models.House;
import com.ekarya.Models.Image;
import com.ekarya.utile.DatabaseConnection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controller class for the Add Property form.
 * Handles user interactions and property data management.
 */
public class AddPropertyController {

    // FXML injected fields
    @FXML
    private ComboBox<Integer> bathroomsCombo;

    @FXML
    private ComboBox<Integer> bedroomsCombo;

    @FXML
    private ComboBox<Integer> bedsCombo;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private ComboBox<Integer> guestsCombo;

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
    
    // Image file references
    private File mainImageFile;
    private File image1File;
    private File image2File;
    private File image3File;
    private File image4File;
    
    // Directory to store uploaded images
    private final String IMAGE_DIRECTORY = "src/main/resources/images/properties/";
    
    // Data access object for database operations
    private PropertyDAO propertyDAO;
    
    /**
     * Initializes the controller.
     * This method is automatically called after the FXML fields are injected.
     */
    public void initialize() {
        System.out.println("Initializing AddPropertyController");
        
        try {
            // Test database connection
            DatabaseConnection.getConnection();
            System.out.println("Database connection successful");
            
            // Initialize DAO
            propertyDAO = new PropertyDAO();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", 
                    "Failed to connect to database", e.getMessage());
        }
        
        // Initialize combo boxes
        initializeComboBoxes();
        
        // Set up image button handlers
        setupImageButtons();
        
        // Set up submit button handler explicitly (in case FXML binding fails)
        if (submitButton != null) {
            submitButton.setOnAction(this::handleSubmit);
            System.out.println("Submit button handler set up");
        } else {
            System.err.println("Submit button is null - FXML injection failed");
        }
    }
    // In your AddPropertyController.java

    @FXML
    private void createPropertyButton(ActionEvent event) {
        try {
            // Validate input fields
            if (validateInput()) {
                // Create new house
                House newHouse = new House();
                newHouse.setTitle(titleField.getText());
                newHouse.setDescription(descriptionArea.getText());
                newHouse.setLocation(locationField.getText());
                newHouse.setPrice(Double.parseDouble(priceField.getText()));
                newHouse.setBedrooms(bedroomsCombo.getValue());
                newHouse.setBathrooms(bathroomsCombo.getValue());
                newHouse.setBeds(bedsCombo.getValue());
                newHouse.setMaxGuests(guestsCombo.getValue());
                
                // Save house to database
                PropertyDAO propertyDAO = new PropertyDAO();
                Long houseId = propertyDAO.saveHouse(newHouse);
                
                if (houseId != null) {
                    // Save images if any
                    List<Image> images = new ArrayList<>();
                    
                    // Add main image as primary
                    if (mainImageFile != null) {
                        String mainImagePath = saveImageFile(mainImageFile, houseId, "main");
                        Image mainImage = new Image();
                        mainImage.setHouseId(houseId);
                        mainImage.setUrl(mainImagePath);
                        mainImage.setPrimary(true);
                        images.add(mainImage);
                    }
                    
                    // Add other images
                    if (image1File != null) {
                        String imagePath = saveImageFile(image1File, houseId, "1");
                        Image image = new Image();
                        image.setHouseId(houseId);
                        image.setUrl(imagePath);
                        images.add(image);
                    }
                    
                    // Add more images if needed
                    
                    if (!images.isEmpty()) {
                        propertyDAO.saveHouseImages(images);
                    }
                    
                    // Show success message
                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Property Added");
                    alert.setContentText("The property has been successfully added.");
                    alert.showAndWait();
                    
                    // Return to main page and refresh properties
                    returnToMainPage(event);
                } else {
                    // Show error message
                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to Add Property");
                    alert.setContentText("There was an error adding the property. Please try again.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            System.err.println("Error adding property: " + e.getMessage());
            e.printStackTrace();
            
            // Show error message
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to Add Property");
            alert.setContentText("There was an error adding the property: " + e.getMessage());
            alert.showAndWait();
        }
    }

private void returnToMainPage(ActionEvent event) {
    try {
        // Load the main page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        Parent root = loader.load();
        
        // Get the controller and refresh properties
        MainController controller = loader.getController();
        controller.refreshHouses();
        
        // Show the main page
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    } catch (IOException e) {
        System.err.println("Error returning to main page: " + e.getMessage());
        e.printStackTrace();
    }
}
    /**
     * Initializes combo boxes with appropriate values.
     */
    private void initializeComboBoxes() {
        try {
            // Bedrooms (1-10)
            for (int i = 1; i <= 10; i++) {
                bedroomsCombo.getItems().add(i);
            }
            bedroomsCombo.setValue(1);
            
            // Bathrooms (1-10)
            for (int i = 1; i <= 10; i++) {
                bathroomsCombo.getItems().add(i);
            }
            bathroomsCombo.setValue(1);
            
            // Beds (1-20)
            for (int i = 1; i <= 20; i++) {
                bedsCombo.getItems().add(i);
            }
            bedsCombo.setValue(1);
            
            // Guests (1-20)
            for (int i = 1; i <= 20; i++) {
                guestsCombo.getItems().add(i);
            }
            guestsCombo.setValue(1);
            
            System.out.println("Combo boxes initialized");
        } catch (Exception e) {
            System.err.println("Error initializing combo boxes: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Sets up event handlers for image buttons.
     */
    private void setupImageButtons() {
        try {
            mainImageButton.setOnAction(event -> selectImage(mainImageView, "Main Image", file -> mainImageFile = file));
            image1Button.setOnAction(event -> selectImage(image1View, "Image 1", file -> image1File = file));
            image2Button.setOnAction(event -> selectImage(image2View, "Image 2", file -> image2File = file));
            image3Button.setOnAction(event -> selectImage(image3View, "Image 3", file -> image3File = file));
            image4Button.setOnAction(event -> selectImage(image4View, "Image 4", file -> image4File = file));
            
            System.out.println("Image buttons set up");
        } catch (Exception e) {
            System.err.println("Error setting up image buttons: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Interface for handling image selection callback.
     */
    private interface ImageSelectedCallback {
        void onImageSelected(File file);
    }
    
    /**
     * Handles image selection for a specific ImageView.
     * 
     * @param imageView The ImageView to display the selected image
     * @param title The title for the file chooser dialog
     * @param callback The callback to handle the selected file
     */
    private void selectImage(ImageView imageView, String title, ImageSelectedCallback callback) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select " + title);
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );
            
            // Show open file dialog
            File selectedFile = fileChooser.showOpenDialog(imageView.getScene().getWindow());
            
            if (selectedFile != null) {
                // Create image from file
                String fileUrl = selectedFile.toURI().toString();
                System.out.println("Loading image from: " + fileUrl);
                
                javafx.scene.image.Image image = new javafx.scene.image.Image(fileUrl, 
                        true); // true for background loading
                
                // Wait for image to load
                if (image.isError()) {
                    throw new IOException("Failed to load image: " + image.getException().getMessage());
                }
                
                // Set the image to the ImageView
                imageView.setImage(image);
                
                // Configure ImageView
                imageView.setFitWidth(150);  // Set appropriate size
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                
                // Call the callback with the selected file
                callback.onImageSelected(selectedFile);
                
                System.out.println("Image loaded successfully: " + selectedFile.getName());
            }
        } catch (Exception e) {
            System.err.println("Error selecting image: " + e.getMessage());
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load image", e.getMessage());
        }
    }
    
    /**
     * Handles the submit button action.
     * Validates input and saves the property.
     * 
     * @param event The action event
     */
    @FXML
    public void handleSubmit(ActionEvent event) {
        System.out.println("Submit button clicked");
        debugDatabase();
        
        try {
            // Validate input
            if (validateInput()) {
                // Process the form data and save to database
                saveProperty();
                
                // Navigate back to dashboard
                handleBackToDashboard(event);
            }
        } catch (Exception e) {
            System.err.println("Error in handleSubmit: " + e.getMessage());
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", 
                    "An error occurred while submitting the form", e.getMessage());
        }
        saveProperty();
    }
    
    /**
     * Validates the form input.
     * 
     * @return true if input is valid, false otherwise
     */
    private boolean validateInput() {
        StringBuilder errors = new StringBuilder();
        
        if (titleField.getText() == null || titleField.getText().trim().isEmpty()) {
            errors.append("- Title is required\n");
        }
        
        if (locationField.getText() == null || locationField.getText().trim().isEmpty()) {
            errors.append("- Location is required\n");
        }
        
        if (priceField.getText() == null || priceField.getText().trim().isEmpty()) {
            errors.append("- Price is required\n");
        } else {
            try {
                Double.parseDouble(priceField.getText());
            } catch (NumberFormatException e) {
                errors.append("- Price must be a valid number\n");
            }
        }
        
        if (mainImageFile == null) {
            errors.append("- Main image is required\n");
        }
        
        if (errors.length() > 0) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", 
                    "Please correct the following errors:", errors.toString());
            return false;
        }
        
        return true;
    }
    
    /**
     * Saves the property data and images to the database and file system.
     */
    private void saveProperty() {
        try {
            // 1. Create House object
            House house = new House();
            house.setTitle(titleField.getText());
            house.setDescription(descriptionArea.getText());
            house.setLocation(locationField.getText());
            house.setPrice(Double.parseDouble(priceField.getText()));
            house.setBedrooms(bedroomsCombo.getValue());
            house.setBathrooms(bathroomsCombo.getValue());
            house.setBeds(bedsCombo.getValue());
            house.setMaxGuests(guestsCombo.getValue());
            
            System.out.println("Created house object: " + house.getTitle());
            
            // 2. Save House to database using DAO
            Long houseId = propertyDAO.saveHouse(house);
            house.setId(houseId);
            
            System.out.println("Saved house to database with ID: " + houseId);
            
            // 3 & 4. Save images to file system and create Image objects
            List<Image> images = new ArrayList<>();
            
            // Create directory if it doesn't exist
            Path directoryPath = Paths.get(IMAGE_DIRECTORY);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
                System.out.println("Created directory: " + directoryPath);
            }
            
            // Save main image
            if (mainImageFile != null) {
                String mainImagePath = saveImageFile(mainImageFile, houseId, "main");
                Image mainImage = new Image();
                mainImage.setHouseId(houseId);
                mainImage.setUrl(mainImagePath);
                mainImage.setPrimary(true);
                images.add(mainImage);
                System.out.println("Added main image: " + mainImagePath);
            }
            
            // Save additional images
            if (image1File != null) {
                String imagePath = saveImageFile(image1File, houseId, "1");
                Image image = new Image();
                image.setHouseId(houseId);
                image.setUrl(imagePath);
                images.add(image);
                System.out.println("Added image 1: " + imagePath);
            }
            
            if (image2File != null) {
                String imagePath = saveImageFile(image2File, houseId, "2");
                Image image = new Image();
                image.setHouseId(houseId);
                image.setUrl(imagePath);
                images.add(image);
                System.out.println("Added image 2: " + imagePath);
            }
            
            if (image3File != null) {
                String imagePath = saveImageFile(image3File, houseId, "3");
                Image image = new Image();
                image.setHouseId(houseId);
                image.setUrl(imagePath);
                images.add(image);
                System.out.println("Added image 3: " + imagePath);
            }
            
            if (image4File != null) {
                String imagePath = saveImageFile(image4File, houseId, "4");
                Image image = new Image();
                image.setHouseId(houseId);
                image.setUrl(imagePath);
                images.add(image);
                System.out.println("Added image 4: " + imagePath);
            }
            
            // 5. Save Image objects to database
            if (!images.isEmpty()) {
                propertyDAO.saveHouseImages(images);
                System.out.println("Saved " + images.size() + " images to database");
            }
            
            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Success", "Property Added", 
                    "Property has been successfully added to the database.");
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", 
                    "Failed to save property to database", e.getMessage());
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "File Error", 
                    "Failed to save image files", e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", 
                    "An unexpected error occurred", e.getMessage());
        }
    }
    
    /**
     * Saves an image file to the file system and returns its path.
     * 
     * @param sourceFile The source image file
     * @param houseId The house ID
     * @param suffix A suffix to identify the image
     * @return The relative path to the saved image
     * @throws IOException If an I/O error occurs
     */
    private String saveImageFile(File sourceFile, Long houseId, String suffix) throws IOException {
        // Generate unique filename
        String extension = sourceFile.getName().substring(sourceFile.getName().lastIndexOf('.'));
        String filename = houseId + "_" + suffix + "_" + UUID.randomUUID().toString().substring(0, 8) + extension;
        
        // Create destination path
        Path destinationPath = Paths.get(IMAGE_DIRECTORY, filename);
        
        // Copy file to destination
        Files.copy(sourceFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Saved image file to: " + destinationPath);
        
        // Return relative path for database storage
        return "images/properties/" + filename;
    }
    
    /**
     * Navigates back to the dashboard.
     * 
     * @param event The action event
     */
    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        try {
            // Load the dashboard page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropretyDashboard.fxml"));
            Parent dashboardRoot = loader.load();
            
            // Get the current stage
            Stage stage = (Stage) submitButton.getScene().getWindow();
            
            // Set the dashboard scene
            Scene scene = new Scene(dashboardRoot);
            stage.setScene(scene);
            stage.show();
            stage.setFullScreen(true);
            
            System.out.println("Navigated back to dashboard");
        } catch (IOException e) {
            System.err.println("Error loading PropretyDashboard.fxml: " + e.getMessage());
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", 
                    "Failed to navigate to dashboard", e.getMessage());
        }
    }
    
    /**
     * Shows an alert dialog.
     * 
     * @param alertType The type of alert
     * @param title The alert title
     * @param header The alert header
     * @param content The alert content
     */
    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }/**
 * Debug method to print database structure information.
 * Call this method before attempting to save a property to diagnose issues.
 */
private void debugDatabase() {
    try {
        System.out.println("\n=== DATABASE STRUCTURE DEBUG ===");
        PropertyDAO dao = new PropertyDAO();
        dao.debugDatabaseStructure();
        System.out.println("=== END DATABASE STRUCTURE DEBUG ===\n");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}