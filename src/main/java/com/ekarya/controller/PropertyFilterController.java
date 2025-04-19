package com.ekarya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PropertyFilterController {

    @FXML
    private Slider minPriceSlider;
    @FXML
    private Slider maxPriceSlider;
    @FXML
    private TextField bedroomsField;
    @FXML
    private TextField bathroomsField;
    @FXML
    private Button applyButton;
    @FXML
    private Button cancelButton;
    
    private MainController mainController;
    
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    @FXML
    public void initialize() {
        // Set default values
        minPriceSlider.setValue(0);
        maxPriceSlider.setValue(1000);
        bedroomsField.setText("1");
        bathroomsField.setText("1");
        
        // Add listeners to ensure min price is always less than max price
        minPriceSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() > maxPriceSlider.getValue()) {
                minPriceSlider.setValue(maxPriceSlider.getValue());
            }
        });
        
        maxPriceSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() < minPriceSlider.getValue()) {
                maxPriceSlider.setValue(minPriceSlider.getValue());
            }
        });
    }
    
    @FXML
    private void handleApplyButton(ActionEvent event) {
        try {
            double minPrice = minPriceSlider.getValue();
            double maxPrice = maxPriceSlider.getValue();
            
            int minBedrooms = Integer.parseInt(bedroomsField.getText());
            int minBathrooms = Integer.parseInt(bathroomsField.getText());
            
            // Apply filters through the main controller
            if (mainController != null) {
                mainController.applyFilters(minPrice, maxPrice, minBedrooms, minBathrooms);
            }
            
            // Close the dialog
            closeDialog();
        } catch (NumberFormatException e) {
            // Show error for invalid number format
            System.err.println("Invalid number format: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleCancelButton(ActionEvent event) {
        closeDialog();
    }
    
    private void closeDialog() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}