package com.ekarya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.ekarya.Models.Property;

public class PropertyDetailController {

    @FXML
    private DatePicker checkInDatePicker;

    @FXML
    private DatePicker checkOutDatePicker;

    @FXML
    private ComboBox<Integer> nbVoyage;

    @FXML
    private Button bookButton;

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    // @FXML
    // private void initialize() {
    //     // Initialize the number of guests combo box
    //     for (int i = 1; i <= 10; i++) {
    //         nbVoyage.getItems().add(i);
    //     }
    //     nbVoyage.setValue(2); // Default to 2 guests
        
    //     // Set default dates (current date and 5 days later)
    //     LocalDate today = LocalDate.now();
    //     checkInDatePicker.setValue(today);
    //     checkOutDatePicker.setValue(today.plusDays(5));
        
    //     // Add listeners to update price calculation when dates change
    //     checkInDatePicker.valueProperty().addListener((obs, oldVal, newVal) -> updatePriceCalculation());
    //     checkOutDatePicker.valueProperty().addListener((obs, oldVal, newVal) -> updatePriceCalculation());
    // }
    public void initData(Property p)
    {
        System.out.println(p);
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
            Stage stage = (Stage) bookButton.getScene().getWindow();
            
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
     * Handles the booking action
     */
    @FXML
    private void handleBooking(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BookingConfirmation.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) bookButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setFullScreen(true);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately, perhaps show an error dialog
        }
        /*  zid logique mta3 mou9arent datet w ychouf dar fer8a fl wa9t adheka w 3dad  lkareya 
        
        // Get the selected dates and number of guests
        LocalDate checkInDate = checkInDatePicker.getValue();
        LocalDate checkOutDate = checkOutDatePicker.getValue();
        Integer guests = nbVoyage.getValue();
        
        // Validate the input
        if (checkInDate == null || checkOutDate == null || guests == null) {
            System.out.println("Please fill in all fields");
            return;
        }
        
        if (checkOutDate.isBefore(checkInDate)) {
            System.out.println("Check-out date must be after check-in date");
            return;
        }
        
        // Calculate the total price
        long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        double nightlyRate = 104.0; // This should be dynamic in a real application
        double totalNights = nightlyRate * nights;
        double serviceFee = totalNights * 0.15; // Assuming 15% service fee
        double total = totalNights + serviceFee;
        
        System.out.println("Booking confirmed!");
        System.out.println("Check-in: " + checkInDate);
        System.out.println("Check-out: " + checkOutDate);
        System.out.println("Guests: " + guests);
        System.out.println("Total nights: " + nights);
        System.out.println("Total price: TND" + total);
        
        // In a real application, you would:
        // 1. Save the booking to a database
        // 2. Navigate to a confirmation page
        // 3. Send a confirmation email, etc.
        
        try {
            // Navigate to a booking confirmation page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ekarya/view/BookingConfirmation.fxml"));
            Parent confirmationRoot = loader.load();
            
            // Pass booking details to the confirmation controller
            // BookingConfirmationController controller = loader.getController();
            // controller.setBookingDetails(checkInDate, checkOutDate, guests, total);
            
            // Get the current stage
            Stage stage = (Stage) bookButton.getScene().getWindow();
            
            // Set the confirmation scene
            Scene scene = new Scene(confirmationRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading BookingConfirmation.fxml: " + e.getMessage());
            e.printStackTrace();
        }*/
    }
    
    /**
     * Updates the price calculation based on the selected dates
     */
    private void updatePriceCalculation() {
        LocalDate checkInDate = checkInDatePicker.getValue();
        LocalDate checkOutDate = checkOutDatePicker.getValue();
        
        if (checkInDate != null && checkOutDate != null && !checkOutDate.isBefore(checkInDate)) {
            long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            // Update the UI with the new calculation
            // In a real application, you would update Text elements with the new values
            System.out.println("Updated price calculation: " + nights + " nights");
        }
    }
}