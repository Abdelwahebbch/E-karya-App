package com.ekarya.controller;

import com.ekarya.app.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    
    @FXML
    private Text userNameText;
    
    @FXML
    private Text userEmailText;
    
    @FXML
    private Text memberSinceText;
    
    @FXML
    private TextField emailSettingField;
    
    @FXML
    private TextField phoneSettingField;
    
    @FXML
    private TextField languageSettingField;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load user data
        // In a real application, this would come from a database or service
        userNameText.setText("John Doe");
        userEmailText.setText("john.doe@example.com");
        memberSinceText.setText("Member since: Jan 2023");
        
        emailSettingField.setText("john.doe@example.com");
        phoneSettingField.setText("+33 6 12 34 56 78");
        languageSettingField.setText("English");
    }
    
    @FXML
    private void handleHome(ActionEvent event) {
        // Navigate to home page
        SceneManager.getInstance().switchToScene(SceneManager.AppScene.HOME);
    }
    
    @FXML
    private void handleEditProfile(ActionEvent event) {
        // Show edit profile dialog
        showAlert("Edit Profile", "This feature is coming soon!");
    }
    
    @FXML
    private void handleViewBookingDetails(ActionEvent event) {
        // Show booking details
        showAlert("Booking Details", "This feature is coming soon!");
    }
    
    @FXML
    private void handleAddNewListing(ActionEvent event) {
        // Navigate to host property page
        SceneManager.getInstance().switchToScene(SceneManager.AppScene.HOST_PROPERTY);
    }
    
    @FXML
    private void handleEditListing(ActionEvent event) {
        // Show edit listing dialog
        showAlert("Edit Listing", "This feature is coming soon!");
    }
    
    @FXML
    private void handleViewBookings(ActionEvent event) {
        // Show bookings for this listing
        showAlert("View Bookings", "This feature is coming soon!");
    }
    
    @FXML
    private void handleSaveSettings(ActionEvent event) {
        // Save settings
        showAlert("Settings Saved", "Your settings have been saved successfully!");
    }
    
    @FXML
    private void handleChangePassword(ActionEvent event) {
        // Show change password dialog
        showAlert("Change Password", "This feature is coming soon!");
    }
    
    @FXML
    private void handleLogout(ActionEvent event) {
        // Navigate to login page
        SceneManager.getInstance().switchToScene(SceneManager.AppScene.LOGIN);
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
