package com.ekarya.controller;

import java.io.IOException;

import com.ekarya.DAO.UserDAO;
import com.ekarya.Models.User;

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

    @FXML
    private Label errorLabel;

    @FXML
    private void handleSignIn(ActionEvent event) throws Exception {
        String email = emailField.getText();
        String password = passwordField.getText();
        UserDAO userDAO = new UserDAO();

        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Email and password cannot be empty");
            return;
        }

        User user = userDAO.VerifUser(email, password);

        if (user != null) {
            try {
                // Load the main scene
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
                Parent mainRoot = loader.load();
                Stage stage = (Stage) emailField.getScene().getWindow();

                MainController mainController = loader.getController();
                mainController.initData(user);

                // Set the new scene
                Scene mainScene = new Scene(mainRoot);
                stage.setScene(mainScene);
                stage.setTitle("Signin Now !");
                stage.setFullScreen(true);
                stage.show();

            } catch (IOException e) {
                errorLabel.setText("Error loading main scene: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            showError("Invalid username or password ! ");
        }
    }

    @FXML
    void handleSignUp(ActionEvent event) {
        try {
            Parent signUpRoot = FXMLLoader.load(getClass().getResource("/fxml/signup.fxml"));
            
            Scene signUpScene = new Scene(signUpRoot);
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(signUpScene);
            stage.setResizable(false);
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
    /*
     * private void navigateToDashboard(ActionEvent event) {
     * try {
     * Parent dashboardRoot =
     * FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
     * Scene dashboardScene = new Scene(dashboardRoot);
     * Stage stage = (Stage) emailField.getScene().getWindow();
     * stage.setScene(dashboardScene);
     * stage.setTitle("E-karya - Dashboard");
     * } catch (IOException e) {
     * showError("Error navigating to dashboard: " + e.getMessage());
     * }
     * }
     */
}