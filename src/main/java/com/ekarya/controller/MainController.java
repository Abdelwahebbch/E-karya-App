package com.ekarya.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController {

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

    @FXML
    void handleCategorySelect(ActionEvent event) {

    }

    @FXML
    void handleFavoriteClick(ActionEvent event) {

    }

    @FXML
    void handleSearch(ActionEvent event) {

    }

    @FXML
    public void handleListingClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/propretyDesc.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void handleFilterClick() {
        try {
            // Load the filter FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/propertyFilter.fxml"));
            Parent root = loader.load();
            
            // Create a new stage for the filter dialog
            Stage filterStage = new Stage();
            filterStage.setTitle("Filtres");
            
            // Make it a modal dialog (blocks interaction with the main window)
            filterStage.initModality(Modality.APPLICATION_MODAL);
            
            // Optional: Remove window decorations for a cleaner look
            // Comment this line if you want the standard window with minimize/maximize/close buttons
            filterStage.initStyle(StageStyle.UNDECORATED);
            
            // Set the scene
            Scene scene = new Scene(root);
            filterStage.setScene(scene);
            
            // Center the dialog on the screen
            filterStage.centerOnScreen();
            
            // Show the dialog and wait for it to be closed
            filterStage.showAndWait();
            
            // After the dialog is closed, you can retrieve filter values if needed
            // PropertyFilterController controller = loader.getController();
            // Use controller methods to get selected filter values
            
        } catch (IOException e) {
            System.err.println("Error loading filter interface: " + e.getMessage());
            e.printStackTrace();
        }
    }
}