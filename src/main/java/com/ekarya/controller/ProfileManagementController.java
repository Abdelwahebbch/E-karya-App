package com.ekarya.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import com.ekarya.DAO.UserDAO;
import com.ekarya.Models.User;
import com.ekarya.validation.InputValidator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProfileManagementController {

    User currentUser = new User();

    @FXML
    private Text phoneNumberText;
    @FXML
    private TextArea bioField;
    @FXML
    private Text bioText;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private PasswordField currentPassword;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private TextField emailField;
    @FXML
    private Text emailText;
    @FXML
    private TextField fullNameField;
    @FXML
    private Text dateOfBirthText;
    @FXML
    private PasswordField newPassword;
    @FXML
    private TextField phoneField;
    @FXML
    private Button refreshAccountButton;
    @FXML
    private Button saveChangesButton;
    @FXML
    private Button updatePasswordButton;
    @FXML
    private Text fullnameText;
    @FXML
    private Label errorLabel;
    @FXML private Label passwordErrorLabel;

    @FXML
    private void handleBackToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            Parent mainRoot = loader.load();
            
            MainController mainController = loader.getController();
            mainController.initData(currentUser);
            
            Stage stage = (Stage) saveChangesButton.getScene().getWindow();
            
            Scene scene = new Scene(mainRoot);
            stage.setScene(scene);
            stage.show();
            stage.setFullScreen(true);
        } catch (IOException e) {
            System.err.println("Error loading HomePage.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void initData(User user) {
        this.currentUser = user;
        if (user != null) {
            fullnameText.setText(user.getFullname());
            emailText.setText(user.getEmail());
            phoneNumberText.setText(user.getPhoneNumber());
            bioText.setText(user.getBio());
            dateOfBirthText.setText(InputValidator.convertSqlDateToString(user.getBirthday()));

            // TO DO: nzidou bio wel birth date 

        }
    }

    public void handleSaveChanges(ActionEvent event) throws IOException {
        UserDAO userDAO = new UserDAO();
        Date birthDate;
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneField.getText();
        LocalDate date = dateOfBirth.getValue();
        String bio = bioField.getText();

        if (fullName.isEmpty()) {
            fullName = currentUser.getFullname();
        }

        if (email.isEmpty()) {
            email = currentUser.getEmail();
        }

        if (phoneNumber.isEmpty()) {
            phoneNumber = currentUser.getPhoneNumber();
        }

        if (date != null) {
            birthDate = Date.valueOf(date);
        } else {
            birthDate = currentUser.getBirthday();
        }
        if (bio.isEmpty()) {
            bio = currentUser.getBio();
        }
        if (!InputValidator.isValidFullName(fullName)) {
            errorLabel.setText("Invalid Format for the Full Name (e.g., Omor Bouchniba).");
        } else if (!InputValidator.isValidEmail(email)) {
            errorLabel.setText("Invalid email format.");
        } else if (!InputValidator.isValidPhoneNumber(phoneNumber)) {
            errorLabel.setText("Phone number must be 8 digits and start with 9X, 5X, or 2X.");
        } else {
            User user = userDAO.editUser(currentUser.getId(), fullName, email, phoneNumber, birthDate, bio);
            if (user != null) {
                currentUser = user;
                fullNameField.clear();
                emailField.clear();
                phoneField.clear();
                dateOfBirth.setValue(null);
                bioField.clear();
            } else {
                errorLabel.setText("Failed to edit profile. Please try again.");
                return;
            }
        }
    }

    @FXML
    void handleCloseButton(MouseEvent event) {
                fullNameField.clear();
                emailField.clear();
                phoneField.clear();
                dateOfBirth.setValue(null);
                bioField.clear();
                currentPassword.clear();
                newPassword.clear();
                confirmPassword.clear();
    }

    @FXML
    void handleRefreshAccount(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProfileManager.fxml"));
            Parent root = loader.load();

            ProfileManagementController controller = loader.getController();

            controller.initData(currentUser);

            Scene scene = new Scene(root);

            currentStage.setScene(scene);
            currentStage.setFullScreen(true);

            errorLabel.setText("Profile updated successfully!");
            errorLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        } catch (IOException e) {
            errorLabel.setText("Error refreshing page: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleUpdatePassword(ActionEvent event) {

        String currentPass = currentPassword.getText();
        String newPass = newPassword.getText();
        String confirmPass = confirmPassword.getText();

        if (currentPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            passwordErrorLabel.setText("All password fields must be filled.");
            passwordErrorLabel.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }
        if (!InputValidator.isValidPassword(newPass))
        {
            passwordErrorLabel.setText("Password must be at least 8 characters long, include upper and lower case letters, a number, and a special character.");
            passwordErrorLabel.setTextFill(javafx.scene.paint.Color.RED);
            return;
            
        }
        if (!newPass.equals(confirmPass)) 
        {
            passwordErrorLabel.setText("New passwords do not match.");
            passwordErrorLabel.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

    
        UserDAO userDAO = new UserDAO();
        boolean isChanged = userDAO.changePassword(currentUser.getId(), currentPass, newPass);

        if (isChanged) {
            errorLabel.setText("Password updated successfully!");
            errorLabel.setTextFill(javafx.scene.paint.Color.GREEN);
            currentUser.setPassword(confirmPass);
    
            currentPassword.clear();
            newPassword.clear();
            confirmPassword.clear();
        } else {
            errorLabel.setText("Current password is incorrect or update failed.");
            errorLabel.setTextFill(javafx.scene.paint.Color.RED);
        }
    }

}