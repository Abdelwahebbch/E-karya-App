package com.ekarya.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

<<<<<<< HEAD
import com.ekarya.Models.House;
=======
import com.ekarya.DAO.PropertyDAO;
import com.ekarya.Models.Property;
>>>>>>> 643576f3484ec0eacbe72e6e4b3369557d286e9e
import com.ekarya.Models.User;
import com.ekarya.Models.Property;
import com.ekarya.DAO.PropertyDAO;
import com.ekarya.utile.DatabaseConnection;
import com.ekarya.controller.PropertyDashboardController;
import com.ekarya.controller.RentalInterfaceController;

<<<<<<< HEAD
import java.io.File;
import java.io.InputStream;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
=======
import javafx.application.Application;
//import javafx.application.Platform;
>>>>>>> 643576f3484ec0eacbe72e6e4b3369557d286e9e
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController  {

    // User and data fields
    private User currentUser = new User();
    private PropertyDAO propertyDAO = new PropertyDAO();
    private List<House> currentHouses = new ArrayList<>();
    private List<House> allHouses = new ArrayList<>();
    private int currentPage = 0;
    private final int HOUSES_PER_PAGE = 6;

    // FXML injected fields
    @FXML
    private DatePicker arrivalDate;
    @FXML
    private DatePicker departureDate;
    @FXML
    private TextField destinationField;
    @FXML
    private GridPane propertiesGrid;
    @FXML
    private VBox listing1;
    @FXML
    private VBox listing2;
    @FXML
    private VBox listing3;
    @FXML
    private VBox listing4;
    @FXML
    private VBox listing5;
    @FXML
    private VBox listing6;
    @FXML
    private ComboBox<String> travelersCombo;

    // Initialize method - called after FXML is loaded
    @FXML
    public void initialize() {
        // Initialize travelers combo box with values
        if (travelersCombo != null) {
            travelersCombo.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5+"));
        }
        
        // Set default dates (today and tomorrow)
        if (arrivalDate != null) {
            arrivalDate.setValue(LocalDate.now());
        }
        
        if (departureDate != null) {
            departureDate.setValue(LocalDate.now().plusDays(1));
        }
        
        // Load houses from database
        Platform.runLater(this::loadHouses);
    }
    // Add this method to your MainController.java class
    public User getCurrentUser() {
        return currentUser;
    }

    // Event handlers
    public void initData(User user) {
        this.currentUser = user;
        System.out.println("User initialized: " + (user != null ? user.getFullname() : "null"));
    }

<<<<<<< HEAD
    // Method to load houses from database and display them
    private void loadHouses() {
        try {
            // Clear existing listings
            clearListings();
            
            // Get houses from database
            allHouses = propertyDAO.getAllHouses();
            
            // Apply pagination
            updateCurrentHouses();
            
            // Display houses in the VBox listings
            displayHouses(currentHouses);
            
            System.out.println("Loaded " + allHouses.size() + " houses from database");
        } catch (SQLException e) {
            handleException("Error loading houses", e);
        }
    }
    
    // Method to update current houses based on pagination
    private void updateCurrentHouses() {
        int startIndex = currentPage * HOUSES_PER_PAGE;
        int endIndex = Math.min(startIndex + HOUSES_PER_PAGE, allHouses.size());
        
        if (startIndex < allHouses.size()) {
            currentHouses = allHouses.subList(startIndex, endIndex);
        } else {
            currentHouses = new ArrayList<>();
        }
    }
    
    // Method to clear all listings
    private void clearListings() {
        VBox[] listings = {listing1, listing2, listing3, listing4, listing5, listing6};
        
        for (VBox listing : listings) {
            if (listing != null) {
                listing.getChildren().clear();
                listing.setStyle("-fx-background-color: transparent;");
                listing.setOnMouseClicked(null);
            }
        }
    }
    
    // Method to display houses in the VBox listings
    private void displayHouses(List<House> houses) {
        // Get array of VBox listings
        VBox[] listings = {listing1, listing2, listing3, listing4, listing5, listing6};
        
        // Display up to 6 houses (or however many VBoxes we have)
        int count = Math.min(houses.size(), listings.length);
        
        for (int i = 0; i < count; i++) {
            House house = houses.get(i);
            VBox listingBox = listings[i];
            
            if (listingBox != null) {
                // Create house card
                createHouseCard(house, listingBox);
                
                // Add click event to the VBox
                final House finalHouse = house;
                listingBox.setUserData(house.getId());
                listingBox.setOnMouseClicked(event -> handleHouseClick(event, finalHouse));
            }
        }
        
        // Clear any unused listings
        for (int i = count; i < listings.length; i++) {
            if (listings[i] != null) {
                listings[i].getChildren().clear();
                listings[i].setStyle("-fx-background-color: transparent;");
                listings[i].setOnMouseClicked(null);
            }
        }
        
        // Add pagination controls if needed
        if (allHouses.size() > HOUSES_PER_PAGE) {
            addPaginationControls();
        }
    }
    
    // Method to add pagination controls
    private void addPaginationControls() {
        // This would be implemented in a future enhancement
        // For now, we'll just use the first 6 houses
    }
    private void loadPlaceholderImage(ImageView imageView) {
        try {
            // Try multiple approaches to load the placeholder
            
            // 1. Try resource stream first
            InputStream stream = getClass().getResourceAsStream("/images/property/placeholder.jpg");
            if (stream != null) {
                imageView.setImage(new Image(stream));
                return;
            }
            
            // 2. Try file in project root
            File file = new File("placeholder.jpg");
            if (file.exists()) {
                imageView.setImage(new Image(file.toURI().toString()));
                return;
            }
            
            // 3. Create a simple colored rectangle as placeholder
            Rectangle placeholder = new Rectangle(300, 200, Color.LIGHTGRAY);
            SnapshotParameters params = new SnapshotParameters();
            WritableImage image = placeholder.snapshot(params, null);
            imageView.setImage(image);
            
        } catch (Exception ex) {
            System.err.println("Could not load placeholder image: " + ex.getMessage());
        }
    }
    private Node createPropertyCard(Property house) {
        try {
            VBox card = new VBox(10);
            card.getStyleClass().add("property-card");
            card.setPadding(new Insets(10));
            card.setMaxWidth(300);
            
            // Create image view
            ImageView imageView = new ImageView();
            imageView.setFitWidth(280);
            imageView.setFitHeight(180);
            imageView.setPreserveRatio(true);
            
            // Load image with error handling
            loadPropertyImage(house, imageView);
            
            // Create property info
            Label titleLabel = new Label(house.getTitle());
            titleLabel.getStyleClass().add("property-title");
            
            Label priceLabel = new Label(String.format("$%.2f / night", house.getPrice()));
            priceLabel.getStyleClass().add("property-price");
            
            Label locationLabel = new Label(house.getLocation());
            locationLabel.getStyleClass().add("property-location");
            
            // Add guest info if needed
            Label guestsLabel = new Label(String.format("%d guests • %d bedrooms • %d beds • %d baths", 
                    house.getGuests(), house.getBedrooms(), house.getBeds(), house.getBathrooms()));
            guestsLabel.getStyleClass().add("property-details");
            
            // Add all elements to card
            card.getChildren().addAll(imageView, titleLabel, priceLabel, locationLabel, guestsLabel);
            
            // Add click event to the entire card
            card.setOnMouseClicked(event -> openPropertyDetails(house));
            
            // Add hover effect
            card.setOnMouseEntered(e -> card.setStyle("-fx-background-color: #f0f0f0; -fx-cursor: hand;"));
            card.setOnMouseExited(e -> card.setStyle("-fx-background-color: white;"));
            
            return card;
        } catch (Exception e) {
            System.err.println("Error creating property card: " + e.getMessage());
            e.printStackTrace();
            return new Label("Error loading property");
        }
    }
    // Method to create a house card in a VBox
    private void createHouseCard(House house, VBox listingBox) {
        try {
            // Clear the VBox
            listingBox.getChildren().clear();
            
            // Create the card structure similar to the original design
            
            // Image container
            StackPane imageContainer = new StackPane();
            ImageView imageView = new ImageView();
            
            if (house.getPrimaryImage() != null && house.getPrimaryImage().getUrl() != null) {
                try {
                    Image image = new Image(house.getPrimaryImage().getUrl(), true);
                    imageView.setImage(image);
                } catch (Exception e) {
                    // Use placeholder image if the image URL is invalid
                    try {
                        imageView.setImage(new Image(getClass().getResourceAsStream("/images/properties/homerental.jpg")));
                    } catch (Exception ex) {
                        System.err.println("Could not load placeholder image: " + ex.getMessage());
                    }
                }
            } else {
                // Use placeholder image if no image URL
                try {
                    imageView.setImage(new Image(getClass().getResourceAsStream("/images/properties/homerental.jpg")));
                } catch (Exception e) {
                    System.err.println("Could not load placeholder image: " + e.getMessage());
                }
            }
            
            // Set image properties
            imageView.setFitHeight(220);
            imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);
            imageView.setStyle("-fx-background-radius: 12 12 0 0;");
            
            imageContainer.getChildren().add(imageView);
            
            // Details container
            VBox detailsContainer = new VBox();
            detailsContainer.setSpacing(8);
            detailsContainer.setPadding(new Insets(16));
            
            // Title and rating row
            HBox titleRow = new HBox();
            titleRow.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
            titleRow.setSpacing(8);
            
            Text titleText = new Text(house.getTitle());
            titleText.setStyle("-fx-font-family: 'Montserrat'; -fx-font-weight: bold; -fx-font-size: 16; -fx-fill: #000000;");
            
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            
            // Rating display (you might want to add actual ratings to your House model)
            HBox ratingBox = new HBox();
            ratingBox.setAlignment(javafx.geometry.Pos.CENTER);
            
            Text starText = new Text("★");
            starText.setStyle("-fx-fill: #000000; -fx-font-weight: bold;");
            
            Text ratingText = new Text(" 4.9"); // Placeholder rating
            ratingText.setStyle("-fx-font-family: 'Montserrat'; -fx-font-weight: bold; -fx-fill: #000000;");
            
            ratingBox.getChildren().addAll(starText, ratingText);
            
            titleRow.getChildren().addAll(titleText, spacer, ratingBox);
            
            // Location
            Text locationText = new Text(house.getLocation());
            locationText.setStyle("-fx-font-family: 'Montserrat'; -fx-fill: #555555; -fx-font-size: 14;");
            
            // Features
            Text featuresText = new Text(house.getBedrooms() + " bedrooms • " + 
                                        house.getBathrooms() + " bathrooms • " +
                                        house.getMaxGuests() + " guests max");
            featuresText.setStyle("-fx-font-family: 'Montserrat'; -fx-fill: #555555; -fx-font-size: 14;");
            
            // Price row
            HBox priceRow = new HBox();
            priceRow.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
            priceRow.setSpacing(4);
            priceRow.setPadding(new Insets(8, 0, 0, 0));
            
            Text priceText = new Text("$" + house.getPrice());
            priceText.setStyle("-fx-font-family: 'Montserrat'; -fx-font-weight: bold; -fx-font-size: 16; -fx-fill: #000000;");
            
            Text perNightText = new Text("per night");
            perNightText.setStyle("-fx-font-family: 'Montserrat'; -fx-fill: #555555; -fx-font-size: 14;");
            
            priceRow.getChildren().addAll(priceText, perNightText);
            
            // Add all elements to the details container
            detailsContainer.getChildren().addAll(titleRow, locationText, featuresText, priceRow);
            
            // Add all containers to the listing box
            listingBox.getChildren().addAll(imageContainer, detailsContainer);
            
            // Add styling
            listingBox.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 12, 0, 0, 2); -fx-cursor: hand; -fx-border-color: transparent; -fx-border-width: 1; -fx-border-radius: 12;");
            
        } catch (Exception e) {
            System.err.println("Error creating house card: " + e.getMessage());
            e.printStackTrace();
            
            // Create a simple error card instead
            createErrorCard(listingBox, "Could not load house details");
        }
    }
    
    // Method to create a simple error card
    private void createErrorCard(VBox listingBox, String errorMessage) {
        listingBox.getChildren().clear();
        
        Label errorLabel = new Label(errorMessage);
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        
        listingBox.getChildren().add(errorLabel);
        listingBox.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-padding: 20; -fx-alignment: center;");
    }
    
    // Method to handle house click
    private void handleHouseClick(MouseEvent event, House house) {
        try {
            // Load the property detail view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropertyDetail.fxml"));
            Parent root = loader.load();
            
            // Get the controller and pass the house data
            PropertyDetailController controller = loader.getController();
            controller.setHouse(house);
            controller.setMainController(this);
            
            // Show the property detail view
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            handleException("Error loading property description", e);
        }
    }

    @FXML
    void handleSearch(ActionEvent event) {
        String destination = destinationField.getText();
        LocalDate checkIn = arrivalDate.getValue();
        LocalDate checkOut = departureDate.getValue();
        String guests = travelersCombo.getValue();
        
=======
   

    @FXML
    public void handleListingClick(MouseEvent event) {
>>>>>>> 643576f3484ec0eacbe72e6e4b3369557d286e9e
        try {
            List<House> filteredHouses = allHouses;
            
            // Filter by destination if provided
            if (destination != null && !destination.isEmpty()) {
                filteredHouses = filteredHouses.stream()
                    .filter(house -> house.getLocation().toLowerCase().contains(destination.toLowerCase()))
                    .collect(Collectors.toList());
            }
            
            // Filter by guests if provided
            if (guests != null && !guests.isEmpty() && !guests.equals("5+")) {
                int guestCount = Integer.parseInt(guests);
                filteredHouses = filteredHouses.stream()
                    .filter(house -> house.getMaxGuests() >= guestCount)
                    .collect(Collectors.toList());
            } else if (guests != null && guests.equals("5+")) {
                filteredHouses = filteredHouses.stream()
                    .filter(house -> house.getMaxGuests() >= 5)
                    .collect(Collectors.toList());
            }
            
            // Update current houses and display
            allHouses = filteredHouses;
            currentPage = 0;
            updateCurrentHouses();
            displayHouses(currentHouses);
            
            // Show search results message
            if (filteredHouses.isEmpty()) {
                showAlert(AlertType.INFORMATION, "Search Results", "No properties found", 
                          "No properties match your search criteria. Try different filters.");
            } else {
                System.out.println("Found " + filteredHouses.size() + " properties matching search criteria");
            }
        } catch (Exception e) {
            handleException("Error during search", e);
        }
    }

    @FXML
    private void handleFilterClick() {
        try {
            // Load the filter FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropertyFilter.fxml"));
            Parent root = loader.load();

            // Create a new stage for the filter dialog
            Stage filterStage = new Stage();
            filterStage.setTitle("Filters");

            // Make it a modal dialog (blocks interaction with the main window)
            filterStage.initModality(Modality.APPLICATION_MODAL);
            filterStage.initStyle(StageStyle.UNDECORATED);

            // Set the scene and display
            Scene scene = new Scene(root);
            filterStage.setScene(scene);
            filterStage.centerOnScreen();
            
            // Get the controller and set up communication
            PropertyFilterController controller = loader.getController();
            controller.setMainController(this);
            
            // Show the dialog
            filterStage.showAndWait();
            
        } catch (IOException e) {
            handleException("Error loading filter interface", e);
        }
    }
    
    // Method to apply filters from the filter dialog
    public void applyFilters(double minPrice, double maxPrice, int minBedrooms, int minBathrooms) {
        try {
            List<House> filteredHouses = allHouses.stream()
                .filter(house -> house.getPrice() >= minPrice && house.getPrice() <= maxPrice)
                .filter(house -> house.getBedrooms() >= minBedrooms)
                .filter(house -> house.getBathrooms() >= minBathrooms)
                .collect(Collectors.toList());
            
            // Update current houses and display
            allHouses = filteredHouses;
            currentPage = 0;
            updateCurrentHouses();
            displayHouses(currentHouses);
            
            // Show filter results message
            if (filteredHouses.isEmpty()) {
                showAlert(AlertType.INFORMATION, "Filter Results", "No properties found", 
                          "No properties match your filter criteria. Try different filters.");
            } else {
                System.out.println("Found " + filteredHouses.size() + " properties matching filter criteria");
            }
        } catch (Exception e) {
            handleException("Error applying filters", e);
        }
    }

    // Refresh method to be called after adding a new property
    public void refreshHouses() {
        loadHouses();
    }

    // Rest of your existing methods...
    @FXML
    void ToDashBoard(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropertyDashboard.fxml"));
            Parent root = loader.load();
            
            // Pass the current user to the dashboard controller
            PropertyDashboardController controller = loader.getController();
            controller.initData(currentUser);
            
            scene.setRoot(root);
        } catch (IOException e) {
            handleException("Error loading dashboard", e);
        }
    }

    @FXML
    void ToBookings(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RentalInterface.fxml"));
            Parent root = loader.load();
            
            // Pass the current user to the rentals controller if needed
            RentalInterfaceController controller = loader.getController();
            controller.initData(currentUser);
            
            scene.setRoot(root);
        } catch (IOException e) {
            handleException("Error loading bookings", e);
        }
    }

    @FXML
    void ToProfile(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProfileManager.fxml"));
            Parent root = loader.load();
            ProfileManagementController profilcontroller = loader.getController();
            profilcontroller.initData(currentUser);
            scene.setRoot(root);
        } catch (IOException e) {
            handleException("Error loading profile", e);
        }
    }

    @FXML
    void handelLogOut(ActionEvent event) {
        for (Property property : PropertyDAO.properties) {
            PropertyDAO.saveProperty(property);
        }
        System.out.println("All properties saved before exit.");
        try {
            DatabaseConnection.closeConnection();

            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Logout Confirmation");
            confirmDialog.setHeaderText("Are you sure you want to logout?");
            //confirmDialog.setContentText("Any unsaved changes will be lost.");

            Optional<ButtonType> result = confirmDialog.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("User logged out successfully");
                
                // Return to login screen instead of exiting
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
                    Parent root = loader.load();
                    
                    Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    System.err.println("Error returning to login screen: " + e.getMessage());
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            handleException("Error during logout", e);
        }
    }

    // Helper method to show alerts
    private void showAlert(AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Helper method for exception handling
    private void handleException(String message, Exception e) {
        System.err.println(message + ": " + e.getMessage());
        e.printStackTrace();
        
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(message);
            alert.setContentText("An error occurred: " + e.getMessage());
            alert.showAndWait();
        });
    }

}