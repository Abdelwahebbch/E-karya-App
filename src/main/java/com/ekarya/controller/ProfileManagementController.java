package com.ekarya.controller;

import com.ekarya.Models.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProfileManagementController {

    User currentUser = new User();

    @FXML private Text phoneNumberText;
    @FXML private TextArea bio;
    @FXML private Text bioText;
    @FXML private PasswordField confirmPassword;
    @FXML private PasswordField currentPassword;
    @FXML private DatePicker dateOfBirth;
    @FXML private TextField emailField;
    @FXML private Text emailText;
    @FXML private TextField fullNameField;
    @FXML private Text dateOfBirthText;
    @FXML private PasswordField newPassword;
    @FXML private TextField phoneField;
    @FXML private Button refreshAccountButton;
    @FXML private Button saveChangesButton;
    @FXML private Button updatePasswordButton;
    @FXML private Text fullnameText;

    public void initData(User user) 
    {
        this.currentUser = user;
        if(user!=null)
        {
            fullnameText.setText(user.getFullname()); 
            emailText.setText(user.getEmail());
            phoneNumberText.setText(user.getPhoneNumber());
            bioText.setText(user.getBio());

            //TO DO: nzidou bio wel birth date w t3awed l 5edma mn signup

        }
    }
    @FXML
    void handleSaveChanges(ActionEvent event) 
    {
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneField.getText();
    }

    @FXML
    void handleCloseButton(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleRefreshAccount(ActionEvent event) {
    }

    @FXML
    void handleUpdatePassword(ActionEvent event) {
    }
}