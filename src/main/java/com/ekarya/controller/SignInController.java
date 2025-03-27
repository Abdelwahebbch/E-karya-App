package com.ekarya.controller;

import com.ekarya.app.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SignInController extends BaseController {
    
    @FXML
    private TextField emailField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Button loginButton;
    
    @FXML
    private Button registerButton;
    
    @FXML
    private Text errorText;
    
    @FXML
    private void handleSignIn(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        
        // Validate input
        if (email == null || email.trim().isEmpty()) {
            showError("Please enter your email");
            return;
        }
        
        if (password == null || password.trim().isEmpty()) {
            showError("Please enter your password");
            return;
        }
        
        // In a real application, you would authenticate the user here
        // For now, just navigate to the home page
        
        // Clear any previous error
        if (errorText != null) {
            errorText.setVisible(false);
        }
        
        // Navigate to home page
        navigateTo(SceneManager.AppScene.HOME);
    }
    
    @FXML
    private void handleRegister(ActionEvent event) {
        // Navigate to registration page
        navigateTo(SceneManager.AppScene.REGISTER);
    }
    
    protected void showError(String message) {
        if (errorText != null) {
            errorText.setText(message);
            errorText.setVisible(true);
        } else {
            // Fallback to the method from BaseController
            showError(message);
        }
    }
}