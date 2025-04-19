package com.ekarya.controller;

import java.io.IOException;
import java.util.Optional;

import com.ekarya.DAO.PropertyDAO;
import com.ekarya.Models.Property;
import com.ekarya.Models.User;
import com.ekarya.utile.DatabaseConnection;

import javafx.application.Application;
//import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController  {

    User currentUser = new User();

    // FXML injected fields
    @FXML
    private DatePicker arrivalDate;
    @FXML
    private DatePicker departureDate;
    @FXML
    private TextField destinationField;
    @FXML
    private VBox listing1;
    @FXML
    private VBox listing2;
    @FXML
    private VBox listing3;
    @FXML
    private VBox listing4;
    @FXML
    private VBox listing5;
    @FXML
    private VBox listing6;
    @FXML
    private ComboBox<?> travelersCombo;

    // Event handlers
    public void initData(User user) {
        this.currentUser = user;
    }

   

    @FXML
    public void handleListingClick(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/PropertyDetail.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setFullScreen(true);
        } catch (IOException e) {
            handleException("Error loading property description", e);
        }
    }

    @FXML
    private void handleFilterClick() {
        try {
            // Load the filter FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropertyFilter.fxml"));
            Parent root = loader.load();

            // Create a new stage for the filter dialog
            Stage filterStage = new Stage();
            filterStage.setTitle("Filtres");

            // Make it a modal dialog (blocks interaction with the main window)
            filterStage.initModality(Modality.APPLICATION_MODAL);
            filterStage.initStyle(StageStyle.UNDECORATED);

            // Set the scene and display
            Scene scene = new Scene(root);
            filterStage.setScene(scene);
            filterStage.centerOnScreen();
            filterStage.showAndWait();

            // After the dialog is closed, you can retrieve filter values if needed
            // PropertyFilterController controller = loader.getController();
            // Use controller methods to get selected filter values
        } catch (IOException e) {
            handleException("Error loading filter interface", e);
        }
    }

    @FXML
    void ToDashBoard(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropertyDashboard.fxml"));
            Parent root = loader.load();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ToBookings(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RentalInterface.fxml"));
            Parent root = loader.load();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ToProfile(ActionEvent event) throws IOException {

        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProfileManager.fxml"));
            Parent root = loader.load();
            ProfileManagementController profilcontroller = loader.getController();
            profilcontroller.initData(currentUser);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handelLogOut(ActionEvent event) {
        for (Property property : PropertyDAO.properties) {
            PropertyDAO.saveProperty(property);
        }
        System.out.println("All properties saved before exit.");
        try {
            DatabaseConnection.closeConnection();

            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Logout Confirmation");
            confirmDialog.setHeaderText("Are you sure you want to logout?");
            //confirmDialog.setContentText("Any unsaved changes will be lost.");

            Optional<ButtonType> result = confirmDialog.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                System.out.println("User logged out successfully");

                // Platform.exit(); // anotherrr way to exit from JavaFX applications
                System.exit(0);

            }
        } catch (Exception e) {
            System.err.println("Error during logout: " + e.getMessage());
            e.printStackTrace();

            // Show error dialog
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Logout Error");
            errorAlert.setContentText("An error occurred while trying to logout: " + e.getMessage());
            errorAlert.showAndWait();
        }
    }

    private void handleException(String message, Exception e) {
        System.err.println(message + ": " + e.getMessage());
        e.printStackTrace();

    }

}