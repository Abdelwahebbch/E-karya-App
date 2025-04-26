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
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.ekarya.DAO.BookingDAO;
import com.ekarya.Models.Property;
import com.ekarya.Models.User;

public class PropertyDetailController {

    double totalPrice;

    public static User currentUser = new User();
    public static Property currentProperty = new Property();
    DecimalFormat df = new DecimalFormat("#.00");
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

    @FXML private Text ratingText;
    @FXML private Text numRatersText;

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
            totalPrice = (currentProperty.getPrice() * numberOfDays) * 0.1
                    + (currentProperty.getPrice() * numberOfDays);
        } else {
            equationField.setText("");
            equationResultField.setText("");
        }
    }

    public void initData(Property p, User u) {
        currentUser = u;
        currentProperty = p;

        ratingText.setText("★ "+df.format(p.getRating()));
        numRatersText.setText("("+p.getNumRaters()+" reviews)");
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
          * @throws Exception 
          */
         @FXML
         private void handleBackToHome(ActionEvent event) throws Exception {
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
        BookingDAO.savePropertyDataToBooking(currentProperty, currentUser, checkInDatePicker.getValue(),
                checkOutDatePicker.getValue());
        Node node = (Node) event.getSource(); // Works for Button, MenuItem, etc.
        Scene scene = node.getScene();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BookingConfirmation.fxml"));
            Parent root = loader.load();

            BookingConfirmationController bookingConfirmationController = loader.getController();
            bookingConfirmationController.initData(currentProperty, currentUser, totalPrice);

            root.setOpacity(0);
            scene.setRoot(root);

            FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.millis(01), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}