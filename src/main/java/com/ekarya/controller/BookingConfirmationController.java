package com.ekarya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingConfirmationController {

    @FXML
    private Button homeButton;

    @FXML
    private Button homeButton2;

    @FXML
    private Button viewBookingsButton;

    @FXML
    private Text propertyNameText;

    @FXML
    private Text datesText;

    @FXML
    private Text guestsText;

    @FXML
    private Text totalPriceText;

    private String propertyName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int adultGuests;
    private int childGuests;
    private double totalPrice;
    private String currency = "TND";

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
        // Default initialization if needed
    }

    /**
     * Sets the booking details to be displayed in the confirmation screen.
     */
    public void setBookingDetails(String propertyName, LocalDate checkInDate, LocalDate checkOutDate,
            int adultGuests, int childGuests, double totalPrice, String currency) {
        this.propertyName = propertyName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.adultGuests = adultGuests;
        this.childGuests = childGuests;
        this.totalPrice = totalPrice;
        this.currency = currency != null ? currency : "TND";

        updateUI();
    }

    /**
     * Updates the UI elements with the booking details.
     */
    private void updateUI() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");

        propertyNameText.setText(propertyName);

        String checkInStr = checkInDate != null ? checkInDate.format(formatter) : "N/A";
        String checkOutStr = checkOutDate != null ? checkOutDate.format(formatter) : "N/A";
        datesText.setText("Check-in: " + checkInStr + " | Check-out: " + checkOutStr);

        String guestText = adultGuests + " adult" + (adultGuests != 1 ? "s" : "");
        if (childGuests > 0) {
            guestText += ", " + childGuests + " child" + (childGuests != 1 ? "ren" : "");
        }
        guestsText.setText("Guests: " + guestText);

        totalPriceText.setText("Total Price: " + String.format("%.0f", totalPrice) + " " + currency);
    }

    /**
     * Handles the action when the home button is clicked.
     */
    @FXML
    private void handleBackToHome(ActionEvent event) {
        try {
            // Load the home page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            Parent homePageRoot = loader.load();
            
            // Get the current stage
            Stage stage = (Stage) viewBookingsButton.getScene().getWindow();
            
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
     * Handles the action when the view bookings button is clicked.
     */
    @FXML
    private void handleViewBookings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RentalInterface.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) viewBookingsButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setFullScreen(true);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately, perhaps show an error dialog
        }
    }

    /**
     * Example method to demonstrate how to call this controller with sample data
     */
    public static void showWithSampleData(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                BookingConfirmationController.class.getResource("/fxml/BookingConfirmation.fxml"));
        Parent root = loader.load();

        BookingConfirmationController controller = loader.getController();
        controller.setBookingDetails(
                "Appartement cosy au cœur de Paris",
                LocalDate.of(2025, 3, 1),
                LocalDate.of(2025, 3, 6),
                2,
                0,
                594.0,
                "TND");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}