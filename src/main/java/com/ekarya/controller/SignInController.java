package com.ekarya.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void handleSignIn(ActionEvent event) {

    }

    @FXML
    void handleSignUp(ActionEvent event) throws IOException {
        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/fxml/signup.fxml"));
            Scene signUpScene = new Scene(signUpRoot);
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(signUpScene);
            stage.setTitle("E-karya - Sign Up");

    }

}
