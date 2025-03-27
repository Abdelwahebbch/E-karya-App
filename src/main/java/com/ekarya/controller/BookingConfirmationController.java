package com.ekarya.controller;


import com.ekarya.app.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class BookingConfirmationController implements Initializable {
    
    @FXML
    private Text propertyNameText;
    
    @FXML
    private Text datesText;
    
    @FXML
    private Text guestsText;
    
    @FXML
    private Text totalPriceText;
    
    // Booking data
    private String propertyName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int guests;
    private double totalPrice;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Default values
        propertyName = "Appartement cosy au cœur de Paris";
        checkInDate = LocalDate.now();
        checkOutDate = LocalDate.now().plusDays(5);
        guests = 2;
        totalPrice = 594.0;
        
        updateUI();
    }
    
    /**
     * Set booking details
     */
    public void setBookingDetails(String propertyName, LocalDate checkInDate, LocalDate checkOutDate, int guests, double totalPrice) {
        this.propertyName = propertyName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guests = guests;
        this.totalPrice = totalPrice;
        
        updateUI();
    }
    
    /**
     * Update UI with booking details
     */
    private void updateUI() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
        
        propertyNameText.setText(propertyName);
        datesText.setText("Check-in: " + checkInDate.format(formatter) + " | Check-out: " + checkOutDate.format(formatter));
        guestsText.setText("Guests: " + guests + (guests > 1 ? " adults" : " adult"));
        totalPriceText.setText("Total Price: " + String.format("%.0f €", totalPrice));
    }
    
    @FXML
    private void handleHome(ActionEvent event) {
        // Navigate to home page
        SceneManager.getInstance().switchToScene(SceneManager.AppScene.HOME);
    }
    
    @FXML
    private void handleViewBookings(ActionEvent event) {
        // Navigate to user profile page, bookings tab
        SceneManager.getInstance().switchToScene(SceneManager.AppScene.USER_PROFILE);
    }
}
