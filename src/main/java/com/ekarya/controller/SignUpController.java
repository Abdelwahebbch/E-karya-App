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
import com.ekarya.validation.InputValidator;

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
    private PasswordField secpass;

    @FXML
    private Label errorLabel;

    @FXML
    void handleSignUp(ActionEvent event) throws IOException {
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneField.getText();
        UserDAO userDAO = new UserDAO();
        String password = passwordField.getText();
        String secPassword = secpass.getText();

        // Check if ANY field is empty (using OR instead of AND)
        if (fullName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || password.isEmpty()) {
            showError("All fields are required");
            return;
        } else if (!InputValidator.isValidFullName(fullName)) {
            showError("Invalid Format for the Full Name (e.g., Foulen Foulen).");
            return;
        } else if (!InputValidator.isValidEmail(email)) {
            showError("Invalid email format.");
            return;
        } else if (!InputValidator.samePassword(password, secPassword)) {
            showError("passwords are not the same .");
            return;
        } else if (!InputValidator.isValidPhoneNumber(phoneNumber)) {
            showError("Phone number must be 8 digits and start with 9X, 5X, or 2X.");
            return;
        } else if (!InputValidator.isValidPassword(password)) {
            showError(
                    "Password must be at least 8 characters long, include upper and lower case letters, a number, and a special character.");
            return;
        } else {
            User user = userDAO.createUser(fullName, phoneNumber, email, password);
            if (user != null) {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
                    Parent mainRoot = loader.load();

                    MainController mainController = loader.getController();
                    mainController.initData(user);

                    Stage stage = (Stage) fullNameField.getScene().getWindow();

                    Scene mainScene = new Scene(mainRoot);
                    stage.setScene(mainScene);
                    stage.setTitle("Main Application");
                    stage.setFullScreen(true);
                    stage.show();

                    // Successfully navigated to main scene, so return to avoid navigating to
                    // sign-in
                    return;
                } catch (IOException e) {
                    showError("Error loading main scene: " + e.getMessage());
                    // Continue to sign-in as fallback
                }
            } else {
                showError("Failed to create user. Please try again.");
                return;
            }
        }

        // Only navigate to sign-in if we didn't successfully navigate to the main scene
        // This serves as a fallback navigation
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