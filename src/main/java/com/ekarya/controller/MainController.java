package com.ekarya.controller;

import com.ekarya.app.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController extends BaseController implements Initializable {

    // Top Navigation Bar
    @FXML
    private Button accommodationsButton;
    
    @FXML
    private Button experiencesButton;
    
    @FXML
    private Button hostButton;
    
    @FXML
    private MenuButton profileMenu;
    
    @FXML
    private MenuItem settingsMenuItem;
    
    @FXML
    private MenuItem logoutMenuItem;
    
    // Search Section
    @FXML
    private TextField destinationField;
    
    @FXML
    private DatePicker arrivalDatePicker;
    
    @FXML
    private DatePicker departureDatePicker;
    
    @FXML
    private ComboBox<Integer> travelersComboBox;
    
    @FXML
    private Button searchButton;
    
    // Category Buttons
    @FXML
    private Button roomsButton;
    
    @FXML
    private Button iconicButton;
    
    @FXML
    private Button guestHousesButton;
    
    @FXML
    private Button seasideButton;
    
    @FXML
    private Button castlesButton;
    
    @FXML
    private Button windmillsButton;
    
    // Listings Grid
    @FXML
    private GridPane listingsGrid;
    
    // Default placeholder image
    private final String DEFAULT_IMAGE = "placeholder.jpg";
    
    // Model data
    private List<PropertyListing> allListings = new ArrayList<>();
    private String currentCategory = "all";
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize travelers combo box
        ObservableList<Integer> travelersOptions = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6);
        travelersComboBox.setItems(travelersOptions);
        travelersComboBox.setValue(2); // Default value
        
        // Set default dates
        arrivalDatePicker.setValue(LocalDate.now());
        departureDatePicker.setValue(LocalDate.now().plusDays(5));
        
        // Load mock data
        loadMockListings();
        
        // Display listings
        displayListings(allListings);
        
        // Call the initialize method from BaseController
        initialize();
    }
    
    @Override
    public void initialize() {
        // Additional initialization if needed
    }
    
    /**
     * Loads mock property listings data
     */
    private void loadMockListings() {
        // In a real application, this would fetch data from a database or API
        allListings = Arrays.asList(
            new PropertyListing("Paris, France", 4.96, "Séjournez chez Marie", "1-6 mars", 104, "Appartement", "placeholder.jpg"),
            new PropertyListing("Nice, France", 4.85, "Séjournez chez Jean", "10-15 mars", 89, "Chambres d'hôtes", "placeholder.jpg"),
            new PropertyListing("Lyon, France", 4.92, "Séjournez chez Pierre", "5-10 avril", 95, "Iconiques", "placeholder.jpg"),
            new PropertyListing("Marseille, France", 4.78, "Séjournez chez Sophie", "15-20 mars", 75, "Bord de mer", "placeholder.jpg"),
            new PropertyListing("Bordeaux, France", 4.88, "Séjournez chez Thomas", "20-25 mars", 110, "Chambres", "placeholder.jpg"),
            new PropertyListing("Strasbourg, France", 4.91, "Séjournez chez Claire", "1-5 avril", 98, "Châteaux", "placeholder.jpg")
        );
    }
    
    /**
     * Displays property listings in the grid
     */
    private void displayListings(List<PropertyListing> listings) {
        // Clear existing listings
        listingsGrid.getChildren().clear();
        
        int column = 0;
        int row = 0;
        final int COLUMNS = 3;
        
        for (PropertyListing listing : listings) {
            VBox listingCard = createListingCard(listing);
            
            // Add click event to the listing card
            listingCard.setOnMouseClicked(event -> openPropertyDetail(listing));
            
            // Add to grid
            listingsGrid.add(listingCard, column, row);
            
            // Update column and row indices
            column++;
            if (column >= COLUMNS) {
                column = 0;
                row++;
            }
        }
    }
    
    /**
     * Creates a listing card VBox for a property
     */
    private VBox createListingCard(PropertyListing listing) {
        // Create listing card
        VBox card = new VBox();
        card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #ddd;");
        card.getStyleClass().add("listing-card");
        
        // Image
        ImageView imageView = new ImageView();
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
        
        // Try to load the image, use default if not found
        try {
            InputStream imageStream = getClass().getResourceAsStream("/" + listing.getImageUrl());
            if (imageStream != null) {
                imageView.setImage(new Image(imageStream));
            } else {
                // Try to load the default image
                InputStream defaultStream = getClass().getResourceAsStream("/" + DEFAULT_IMAGE);
                if (defaultStream != null) {
                    imageView.setImage(new Image(defaultStream));
                } else {
                    // Create a blank image if even the default is not found
                    System.out.println("Warning: Could not load image: " + listing.getImageUrl() + " or default image");
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
        
        // Details
        VBox details = new VBox(5);
        details.setStyle("-fx-padding: 10;");
        
        HBox locationRating = new HBox(10);
        Text location = new Text(listing.getLocation());
        location.setStyle("-fx-font-weight: bold;");
        Text rating = new Text("★ " + listing.getRating());
        locationRating.getChildren().addAll(location, rating);
        
        Text host = new Text(listing.getHostInfo());
        Text dates = new Text(listing.getAvailableDates());
        Text price = new Text(listing.getPrice() + " € par nuit");
        price.setStyle("-fx-font-weight: bold;");
        
        details.getChildren().addAll(locationRating, host, dates, price);
        
        // Add all to card
        card.getChildren().addAll(imageView, details);
        
        return card;
    }
    
    /**
     * Opens the property detail view for a listing
     */
    private void openPropertyDetail(PropertyListing listing) {
        try {
            // Get the RoomDetailController
            RoomDetailController controller = SceneManager.getInstance().getController(SceneManager.AppScene.ROOM_DETAIL);
        
            // Set the property data
            controller.setPropertyData(listing);
        
            // Switch to the room detail scene with transition
            navigateTo(SceneManager.AppScene.ROOM_DETAIL);
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not open property details: " + e.getMessage());
        }
    }
    
    /**
     * Filters listings by category
     */
    @FXML
    private void filterByCategory(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        String category = sourceButton.getText();
        
        // Update current category
        currentCategory = category;
        
        // Filter listings
        List<PropertyListing> filteredListings;
        if (category.equals("all")) {
            filteredListings = allListings;
        } else {
            filteredListings = allListings.stream()
                .filter(listing -> listing.getCategory().equals(category))
                .collect(Collectors.toList());
        }
        
        // Update UI
        displayListings(filteredListings);
        
        // Highlight selected button
        resetCategoryButtonStyles();
        sourceButton.setStyle("-fx-background-color: #f7f7f7; -fx-border-color: #ddd; -fx-border-radius: 20;");
    }
    
    /**
     * Resets styles for all category buttons
     */
    private void resetCategoryButtonStyles() {
        roomsButton.setStyle("-fx-background-color: transparent;");
        iconicButton.setStyle("-fx-background-color: transparent;");
        guestHousesButton.setStyle("-fx-background-color: transparent;");
        seasideButton.setStyle("-fx-background-color: transparent;");
        castlesButton.setStyle("-fx-background-color: transparent;");
        windmillsButton.setStyle("-fx-background-color: transparent;");
    }
    
    /**
     * Handles search button click
     */
    @FXML
    private void handleSearch(ActionEvent event) {
        String destination = destinationField.getText();
        LocalDate arrivalDate = arrivalDatePicker.getValue();
        LocalDate departureDate = departureDatePicker.getValue();
        Integer travelers = travelersComboBox.getValue();
        
        // Validate input
        if (destination == null || destination.trim().isEmpty()) {
            showError("Please enter a destination");
            return;
        }
        
        if (arrivalDate == null) {
            showError("Please select an arrival date");
            return;
        }
        
        if (departureDate == null) {
            showError("Please select a departure date");
            return;
        }
        
        if (departureDate.isBefore(arrivalDate)) {
            showError("Departure date must be after arrival date");
            return;
        }
        
        if (travelers == null) {
            showError("Please select number of travelers");
            return;
        }
        
        // In a real application, this would search the database or API
        // For now, just filter by destination (case-insensitive partial match)
        List<PropertyListing> searchResults = allListings.stream()
            .filter(listing -> listing.getLocation().toLowerCase().contains(destination.toLowerCase()))
            .collect(Collectors.toList());
        
        // Update UI
        displayListings(searchResults);
    }
    
    /**
     * Handles accommodations button click
     */
    @FXML
    private void handleAccommodations(ActionEvent event) {
        // Already on accommodations page, could refresh or reset filters
        resetCategoryButtonStyles();
        currentCategory = "all";
        displayListings(allListings);
    }
    
    /**
     * Handles experiences button click
     */
    @FXML
    private void handleExperiences(ActionEvent event) {
        showAlert("Information", "Experiences feature coming soon!");
    }
    
    /**
     * Handles host property button click
     */
    @FXML
    private void handleHostProperty(ActionEvent event) {
        navigateTo(SceneManager.AppScene.HOST_PROPERTY);
    }
    
    /**
     * Handles settings menu item click
     */
    @FXML
    private void handleSettings(ActionEvent event) {
        showAlert("Information", "Settings feature coming soon!");
    }
    
    /**
     * Handles logout menu item click
     */
    @FXML
    private void handleLogout(ActionEvent event) {
        // In a real application, this would handle user logout
        showAlert("Information", "Logout successful");
    
        // Navigate to login screen
        navigateTo(SceneManager.AppScene.LOGIN);
    }
    
    /**
     * Inner class to represent a property listing
     */
    public static class PropertyListing {
        private String location;
        private double rating;
        private String hostInfo;
        private String availableDates;
        private double price;
        private String category;
        private String imageUrl;
        
        public PropertyListing(String location, double rating, String hostInfo, String availableDates, 
                              double price, String category, String imageUrl) {
            this.location = location;
            this.rating = rating;
            this.hostInfo = hostInfo;
            this.availableDates = availableDates;
            this.price = price;
            this.category = category;
            this.imageUrl = imageUrl;
        }
        
        // Getters
        public String getLocation() { return location; }
        public double getRating() { return rating; }
        public String getHostInfo() { return hostInfo; }
        public String getAvailableDates() { return availableDates; }
        public double getPrice() { return price; }
        public String getCategory() { return category; }
        public String getImageUrl() { return imageUrl; }
    }
}