package com.ekarya.controller;

import com.ekarya.app.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterController {
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private PasswordField confirmPasswordField;
    
    @FXML
    private Button registerButton;
    
    @FXML
    private Hyperlink loginLink;
    
    @FXML
    private Text errorText;
    
    @FXML
    private void handleRegister(ActionEvent event) {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        
        // Validate input
        if (name == null || name.trim().isEmpty()) {
            showError("Please enter your name");
            return;
        }
        
        if (email == null || email.trim().isEmpty()) {
            showError("Please enter your email");
            return;
        }
        
        if (password == null || password.trim().isEmpty()) {
            showError("Please enter a password");
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match");
            return;
        }
        
        // In a real application, you would register the user here
        // For now, just navigate to the home page
        
        // Clear any previous error
        hideError();
        
        // Navigate to home page
        SceneManager.getInstance().switchToScene(SceneManager.AppScene.HOME);
    }
    
    @FXML
    private void handleLogin(ActionEvent event) {
        // Navigate to login page
        SceneManager.getInstance().switchToScene(SceneManager.AppScene.LOGIN);
    }
    
    private void showError(String message) {
        errorText.setText(message);
        errorText.setVisible(true);
    }
    
    private void hideError() {
        errorText.setVisible(false);
    }
}