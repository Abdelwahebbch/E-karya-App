package com.ekarya.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;
    
    public TextField getEmailField() {
        return emailField;
    }

    public void setEmailField(TextField emailField) {
        this.emailField = emailField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(Label errorLabel) {
        this.errorLabel = errorLabel;
    }

    @FXML
    private Label errorLabel;

    @FXML
    void handleSignIn(ActionEvent event) {
        // Get the values from the fields
        String email = emailField.getText();
        String password = passwordField.getText();
        
        // Validate the input
        if (email.isEmpty() || password.isEmpty()) {
            showError("Email and password are required");
            return;
        }
        
        // TODO: Add your sign-in logic here
        // For example, authenticate the user against your database
        
        // For now, just show a success message
        // You can navigate to a dashboard or home page after successful login
        // Example:
        // navigateToDashboard(event);
    }

    @FXML
    void handleSignUp(ActionEvent event) {
        try {
            Parent signUpRoot = FXMLLoader.load(getClass().getResource("/fxml/signup.fxml"));
            Scene signUpScene = new Scene(signUpRoot);
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(signUpScene);
            stage.setTitle("E-karya - Sign Up");
        } catch (IOException e) {
            showError("Error navigating to sign up page: " + e.getMessage());
        }
    }
    
    private void showError(String message) {
        if (errorLabel != null) {
            errorLabel.setText(message);
            errorLabel.setVisible(true);
        } else {
            System.err.println("Error: " + message);
        }
    }
    
    // Example method for navigation after successful login
   /* private void navigateToDashboard(ActionEvent event) {
        try {
            Parent dashboardRoot = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
            Scene dashboardScene = new Scene(dashboardRoot);
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(dashboardScene);
            stage.setTitle("E-karya - Dashboard");
        } catch (IOException e) {
            showError("Error navigating to dashboard: " + e.getMessage());
        }
    }*/
}