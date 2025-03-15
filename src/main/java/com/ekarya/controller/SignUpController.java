package com.ekarya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class SignUpController {
    @FXML
    private TextField fullNameField;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private TextField phoneField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    void handleSignUp(ActionEvent event) {
        // Get the values from the fields
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = passwordField.getText();
        
        // Validate the input
        if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            showError("All fields are required");
            return;
        }
        
        // TODO: Add your sign-up logic here
        // For example, create a user in your database
        
        // For now, just show a success message and navigate to sign in
        try {
            navigateToSignIn(event);
        } catch (IOException e) {
            showError("Error navigating to sign in page: " + e.getMessage());
        }
    }
    
    @FXML
    void navigateToSignIn(ActionEvent event) throws IOException {
        Parent signInRoot = FXMLLoader.load(getClass().getResource("/fxml/signin.fxml"));
        Scene signInScene = new Scene(signInRoot);
        Stage stage = (Stage) emailField.getScene().getWindow();
        stage.setScene(signInScene);
        stage.setTitle("E-karya - Sign In");
    }
    
    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
}