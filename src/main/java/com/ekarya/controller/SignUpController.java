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

import com.ekarya.DAO.UserDAO;
import com.ekarya.Models.User;

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

    // @FXML
    // private void handleSignIn(ActionEvent event) 
    // {
    //     String fullName = fullNameField.getText();
    //     String phoneNumber = phoneField.getText();
    //     String email = emailField.getText();
    //     String password = passwordField.getText();
    //     UserDAO userDAO = new UserDAO();
        
    //     if (email.isEmpty() || password.isEmpty() || fullName.isEmpty() || phoneNumber.isEmpty()) {
    //         errorLabel.setText("Username and password cannot be empty");
    //         return;
    //     }
        
        
    //     User user = userDAO.createUser(fullName,phoneNumber,email,password);
        
    //     // if (user != null) {
    //     //     try {
    //     //         // Load the main scene
    //     //         FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
    //     //         Parent mainRoot = loader.load();
                
    //     //         // Get the controller and pass the user data
    //     //         MainController mainController = loader.getController();
    //     //         mainController.initData(user);
                
    //     //         // Get current stage
    //     //         Stage stage = (Stage) usernameField.getScene().getWindow();
                
    //     //         // Set the new scene
    //     //         Scene mainScene = new Scene(mainRoot);
    //     //         stage.setScene(mainScene);
    //     //         stage.setTitle("Main Application");
    //     //         stage.show();
                
    //     //     } catch (IOException e) {
    //     //         messageLabel.setText("Error loading main scene: " + e.getMessage());
    //     //         e.printStackTrace();
    //     //     }
    //     // } else {
    //     //     messageLabel.setText("Invalid username or password");
    //     // }
    // }

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