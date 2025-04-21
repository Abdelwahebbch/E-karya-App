package com.ekarya.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.ekarya.Models.Property;
import com.ekarya.Models.User;

public class PropertyDetailController {

    double totalPrice;

    User currentUser = new User();
    Property currentProperty = new Property();
    @FXML
    private Button bookButton;

    @FXML
    private DatePicker checkInDatePicker;

    @FXML
    private DatePicker checkOutDatePicker;

    @FXML
    private Text descriptionField;

    @FXML
    private Text detailsField;

    @FXML
    private Text equationField;

    @FXML
    private Text equationResultField;

    @FXML
    private Text finalPriceField;

    @FXML
    private Text locationField;

    @FXML
    private TextField nbGuestField;

    @FXML
    private Text priceField;

    @FXML
    private Text propNameField;

    @FXML
    private Text titleField;

    @FXML
    private Text tvaField;

    public void initialize() {
        checkInDatePicker.valueProperty().addListener((obs, oldDate, newDate) -> {
            calculateAndDisplayDays();
        });

        checkOutDatePicker.valueProperty().addListener((obs, oldDate, newDate) -> {
            calculateAndDisplayDays();
        });

    }

    private void calculateAndDisplayDays() {
        LocalDate checkInDate = checkInDatePicker.getValue();
        LocalDate checkOutDate = checkOutDatePicker.getValue();

        if (checkInDate != null && checkOutDate != null) {
            int numberOfDays = (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);

            if (numberOfDays < 0) {
                System.out.println("Check-out date must be after check-in date!");
                checkOutDatePicker.setValue(null);
                equationField.setText("");
                equationResultField.setText("");
                return;
            }

            equationField.setText(currentProperty.getPrice() + " x " + numberOfDays);
            equationResultField.setText(String.valueOf(currentProperty.getPrice() * numberOfDays));
            tvaField.setText(String.valueOf((currentProperty.getPrice() * numberOfDays) * 0.1));
            finalPriceField.setText(((currentProperty.getPrice() * numberOfDays) * 0.1)
                    + (currentProperty.getPrice() * numberOfDays) + "");
            totalPrice= (currentProperty.getPrice() * numberOfDays) * 0.1 + (currentProperty.getPrice() * numberOfDays);
        } else {
            equationField.setText("");
            equationResultField.setText("");
        }
    }

    public void initData(Property p, User u) {
        currentUser = u;
        currentProperty = p;
        descriptionField.setText(p.getDescription());
        detailsField.setText(
                p.getGuests() + " Guestes " + p.getBathrooms() + " Bathrooms " + p.getBedrooms() + " Bedrooms");
        locationField.setText("• " + p.getLocation());
        priceField.setText(p.getPrice() + " TND per night ");
        propNameField.setText(p.getTitle());
        titleField.setText(p.getTitle());

    }

    /**
     * Handles the back button action to navigate back to the home page
     */
    @FXML
    private void handleBackToHome(ActionEvent event) {
        Node node = (Node) event.getSource(); // Works for Button, MenuItem, etc.
        Scene scene = node.getScene();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            Parent root = loader.load();

            MainController controller = loader.getController();
            controller.initData(currentUser);

            // Apply fade-in transition
            root.setOpacity(0); // Start invisible
            scene.setRoot(root); // Set the new root

            FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.millis(01), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the booking action
     */
    @FXML
    private void handleBooking(ActionEvent event) {
        Node node = (Node) event.getSource(); // Works for Button, MenuItem, etc.
        Scene scene = node.getScene();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BookingConfirmation.fxml"));
            Parent root = loader.load();

            BookingConfirmationController bookingConfirmationController = loader.getController();
            bookingConfirmationController.initData(currentProperty,currentUser,totalPrice);
           
            root.setOpacity(0); 
            scene.setRoot(root); 

            FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.millis(01), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately, perhaps show an error dialog
        }
        /*
         * zid logique mta3 mou9arent datet w ychouf dar fer8a fl wa9t adheka w 3dad
         * lkareya
         * 
         * // Get the selected dates and number of guests
         * LocalDate checkInDate = checkInDatePicker.getValue();
         * LocalDate checkOutDate = checkOutDatePicker.getValue();
         * Integer guests = nbVoyage.getValue();
         * 
         * // Validate the input
         * if (checkInDate == null || checkOutDate == null || guests == null) {
         * System.out.println("Please fill in all fields");
         * return;
         * }
         * 
         * if (checkOutDate.isBefore(checkInDate)) {
         * System.out.println("Check-out date must be after check-in date");
         * return;
         * }
         * 
         * // Calculate the total price
         * long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
         * double nightlyRate = 104.0; // This should be dynamic in a real application
         * double totalNights = nightlyRate * nights;
         * double serviceFee = totalNights * 0.15; // Assuming 15% service fee
         * double total = totalNights + serviceFee;
         * 
         * System.out.println("Booking confirmed!");
         * System.out.println("Check-in: " + checkInDate);
         * System.out.println("Check-out: " + checkOutDate);
         * System.out.println("Guests: " + guests);
         * System.out.println("Total nights: " + nights);
         * System.out.println("Total price: TND" + total);
         * 
         * // In a real application, you would:
         * // 1. Save the booking to a database
         * // 2. Navigate to a confirmation page
         * // 3. Send a confirmation email, etc.
         * 
         * try {
         * // Navigate to a booking confirmation page
         * FXMLLoader loader = new
         * FXMLLoader(getClass().getResource("/com/ekarya/view/BookingConfirmation.fxml"
         * ));
         * Parent confirmationRoot = loader.load();
         * 
         * // Pass booking details to the confirmation controller
         * // BookingConfirmationController controller = loader.getController();
         * // controller.setBookingDetails(checkInDate, checkOutDate, guests, total);
         * 
         * // Get the current stage
         * Stage stage = (Stage) bookButton.getScene().getWindow();
         * 
         * // Set the confirmation scene
         * Scene scene = new Scene(confirmationRoot);
         * stage.setScene(scene);
         * stage.show();
         * } catch (IOException e) {
         * System.err.println("Error loading BookingConfirmation.fxml: " +
         * e.getMessage());
         * e.printStackTrace();
         * }
         */
    }

}