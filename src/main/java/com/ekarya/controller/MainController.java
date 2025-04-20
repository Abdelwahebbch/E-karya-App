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

import com.ekarya.DAO.PropertyDAO;
import com.ekarya.Models.Property;
import com.ekarya.Models.User;
import com.ekarya.Models.Property;
import com.ekarya.DAO.PropertyDAO;
import com.ekarya.utile.DatabaseConnection;

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
    }

   

    @FXML
    public void handleListingClick(MouseEvent event) {
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