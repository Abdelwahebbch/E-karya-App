package com.ekarya.controller;

import com.ekarya.app.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class RoomDetailController extends BaseController implements Initializable {

    @FXML
    private ComboBox<Integer> nbVoyage;
    
    @FXML
    private Button backButton;
    
    @FXML
    private DatePicker arrivalDatePicker;
    
    @FXML
    private DatePicker departureDatePicker;
    
    @FXML
    private Text totalPriceText;
    
    @FXML
    private Text nightsCalculationText;
    
    @FXML
    private Text serviceFeesText;
    
    @FXML
    private Text roomTitleText;
    
    @FXML
    private Text ratingText;
    
    @FXML
    private Text locationText;
    
    // Room price per night
    private double pricePerNight = 104.0;
    // Service fee percentage
    private final double serviceFeePercentage = 0.15;
    
    // Property data
    private MainController.PropertyListing propertyData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the number of travelers ComboBox
        ObservableList<Integer> travelersOptions = FXCollections.observableArrayList(1, 2, 3, 4);
        nbVoyage.setItems(travelersOptions);
        nbVoyage.setValue(2); // Default value
        
        // Add listeners to date pickers to update price calculation
        arrivalDatePicker.valueProperty().addListener((obs, oldVal, newVal) -> updatePriceCalculation());
        departureDatePicker.valueProperty().addListener((obs, oldVal, newVal) -> updatePriceCalculation());
        
        // Set default dates (today and today + 5 days)
        arrivalDatePicker.setValue(LocalDate.now());
        departureDatePicker.setValue(LocalDate.now().plusDays(5));
        
        // Initial price calculation
        updatePriceCalculation();
        
        // Call the initialize method from BaseController
        initialize();
    }
    
    @Override
    public void initialize() {
        // Additional initialization if needed
    }
    
    /**
     * Sets the property data and updates the UI
     */
    public void setPropertyData(MainController.PropertyListing listing) {
        this.propertyData = listing;
        
        // Update price per night
        if (listing != null) {
            this.pricePerNight = listing.getPrice();
            
            // Update UI elements with property data
            if (roomTitleText != null) {
                roomTitleText.setText("Appartement cosy à " + listing.getLocation());
            }
            
            if (ratingText != null) {
                ratingText.setText("★ " + listing.getRating());
            }
            
            if (locationText != null) {
                locationText.setText("• " + listing.getLocation());
            }
            
            // Update price calculation
            updatePriceCalculation();
        }
    }
    
    /**
     * Updates the price calculation based on selected dates
     */
    private void updatePriceCalculation() {
        LocalDate arrivalDate = arrivalDatePicker.getValue();
        LocalDate departureDate = departureDatePicker.getValue();
        
        if (arrivalDate != null && departureDate != null && !departureDate.isBefore(arrivalDate)) {
            // Calculate number of nights
            long nights = ChronoUnit.DAYS.between(arrivalDate, departureDate);
            
            // Calculate costs
            double accommodationCost = nights * pricePerNight;
            double serviceFees = accommodationCost * serviceFeePercentage;
            double totalCost = accommodationCost + serviceFees;
            
            // Update UI
            if (nightsCalculationText != null) {
                nightsCalculationText.setText(String.format("%.0f € x %d nuits", pricePerNight, nights));
            }
            
            if (serviceFeesText != null) {
                serviceFeesText.setText(String.format("%.0f €", serviceFees));
            }
            
            if (totalPriceText != null) {
                totalPriceText.setText(String.format("%.0f €", totalCost));
            }
        }
    }
    
    /**
     * Handles the back button click
     */
// To this:
    @FXML
    private void handleBackButton(ActionEvent event) {
        // Navigate back to the home page
        navigateTo(SceneManager.AppScene.HOME);
    }
    
    /**
     * Handles the booking button click
     */
    @FXML
    private void handleBookingButton(ActionEvent event) {
        LocalDate arrivalDate = arrivalDatePicker.getValue();
        LocalDate departureDate = departureDatePicker.getValue();
        Integer travelers = nbVoyage.getValue();
        
        if (arrivalDate == null || departureDate == null || travelers == null) {
            // Show error message - all fields must be filled
            System.out.println("Please fill all required fields");
            return;
        }
        
        if (departureDate.isBefore(arrivalDate)) {
            // Show error message - departure date must be after arrival date
            System.out.println("Departure date must be after arrival date");
            return;
        }
        
        // Process booking
        // This would typically involve calling a service to create a booking
        String location = propertyData != null ? propertyData.getLocation() : "Unknown location";
        System.out.println("Booking processed for " + travelers + " travelers at " + location + 
                           " from " + arrivalDate + " to " + departureDate);
        
        // Show confirmation
        System.out.println("Booking confirmed!");
        
        // Navigate to booking confirmation
        navigateTo(SceneManager.AppScene.BOOKING_CONFIRMATION);
    }
    
}