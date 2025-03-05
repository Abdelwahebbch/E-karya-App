package com.ekarya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ProfileManagementController {

    @FXML
    private Text accountTypeText;

    @FXML
    private TextArea bio;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private PasswordField currentPassword;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private TextField email;

    @FXML
    private Text emailText;

    @FXML
    private TextField firstName;

    @FXML
    private ComboBox<?> gender;

    @FXML
    private Text lastLoginText;

    @FXML
    private TextField lastName;

    @FXML
    private Text memberSinceText;

    @FXML
    private PasswordField newPassword;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button refreshAccountButton;

    @FXML
    private Button saveChangesButton;

    @FXML
    private Button updatePasswordButton;

    @FXML
    private Text usernameText;

    @FXML
    void handleRefreshAccount(ActionEvent event) {

    }

    @FXML
    void handleSaveChanges(ActionEvent event) {

    }

    @FXML
    void handleUpdatePassword(ActionEvent event) {

    }

}
