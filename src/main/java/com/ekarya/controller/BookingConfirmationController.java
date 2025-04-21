package com.ekarya.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.ekarya.Models.Property;
import com.ekarya.Models.User;

public class BookingConfirmationController {
    private User currentUser = new User();
    private Property cuProperty = new Property();
    @FXML
    private Button homeButton;

    @FXML
    private Button homeButton2;

    @FXML
    private Button viewBookingsButton;

    @FXML
    private Text propertyNameText;

    @FXML 
    private Text checkInText;

    @FXML 
    private Text checkOutText;

    @FXML
    private Text locationText;

    @FXML
    private Text totalPriceText;

    // private String propertyName;
    // private LocalDate checkInDate;
    // private LocalDate checkOutDate;
    // private int adultGuests;
    // private int childGuests;
    // private double totalPrice;
    // private String currency = "TND";

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
        // Default initialization if needed
    }

    public void initData(Property p, User u , double price)
    {
        currentUser=u;
        cuProperty=p;


        propertyNameText.setText(p.getTitle());
        locationText.setText(p.getLocation());
        totalPriceText.setText(price+" TND");

    }

    /**
     * Sets the booking details to be displayed in the confirmation screen.
     */
    // public void setBookingDetails(String propertyName, LocalDate checkInDate, LocalDate checkOutDate,
    //         int adultGuests, int childGuests, double totalPrice, String currency) {
    //     this.propertyName = propertyName;
    //     this.checkInDate = checkInDate;
    //     this.checkOutDate = checkOutDate;
    //     this.adultGuests = adultGuests;
    //     this.childGuests = childGuests;
    //     this.totalPrice = totalPrice;
    //     this.currency = currency != null ? currency : "TND";

    //     updateUI();
    // }

    /**
     * Updates the UI elements with the booking details.
     */
    // private void updateUI() {
    //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");

    //     propertyNameText.setText(propertyName);

    //     String checkInStr = checkInDate != null ? checkInDate.format(formatter) : "N/A";
    //     String checkOutStr = checkOutDate != null ? checkOutDate.format(formatter) : "N/A";
        

    //     String guestText = adultGuests + " adult" + (adultGuests != 1 ? "s" : "");
    //     if (childGuests > 0) {
    //         guestText += ", " + childGuests + " child" + (childGuests != 1 ? "ren" : "");
    //     }
    //     guestsText.setText("Guests: " + guestText);

    //     totalPriceText.setText("Total Price: " + String.format("%.0f", totalPrice) + " " + currency);
    // }

    /**
     * Handles the action when the home button is clicked.
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
     * Handles the action when the view bookings button is clicked.
     */
    @FXML
    private void handleViewBookings(ActionEvent event) {
        Node node = (Node) event.getSource(); // Works for Button, MenuItem, etc.
        Scene scene = node.getScene();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RentalInterface.fxml"));
            Parent root = loader.load();

            // Apply fade-in transition
            root.setOpacity(0); // Start invisible
            scene.setRoot(root); // Set the new root

            FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.millis(01), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately, perhaps show an error dialog
        }
    }

    /**
     * Example method to demonstrate how to call this controller with sample data
     */
    // public static void showWithSampleData(Stage stage) throws IOException {
    //     FXMLLoader loader = new FXMLLoader(
    //             BookingConfirmationController.class.getResource("/fxml/BookingConfirmation.fxml"));
    //     Parent root = loader.load();

    //     BookingConfirmationController controller = loader.getController();
    //     controller.setBookingDetails(
    //             "Appartement cosy au cœur de Paris",
    //             LocalDate.of(2025, 3, 1),
    //             LocalDate.of(2025, 3, 6),
    //             2,
    //             0,
    //             594.0,
    //             "TND");

    //     Scene scene = new Scene(root);
    //     stage.setScene(scene);
    //     stage.show();
    // }
}