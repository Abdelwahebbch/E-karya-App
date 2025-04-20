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
<<<<<<< HEAD
=======
            minPriceText.setText(String.format("%.0f TND", minPriceSlider.getValue()));
>>>>>>> 16484111ec47efb4c5707daedbbb1bbfeddf638c
        });
        
        maxPriceSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() < minPriceSlider.getValue()) {
                maxPriceSlider.setValue(minPriceSlider.getValue());
            }
<<<<<<< HEAD
=======
            maxPriceText.setText(String.format("%.0f TND", maxPriceSlider.getValue()));
>>>>>>> 16484111ec47efb4c5707daedbbb1bbfeddf638c
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
    
<<<<<<< HEAD
    @FXML
    private void handleCancelButton(ActionEvent event) {
=======
    private void resetFilters() {
        // Reset price sliders
        minPriceSlider.setValue(0);
        maxPriceSlider.setValue(1000);
        
        // Reset combo boxes
        bedroomsCombo.setValue(null);
        bedsCombo.setValue(null);
        bathroomsCombo.setValue(null);
    }
    
    private void applyFilters() {
        // Implement filter application logic here
        // This would typically gather all selected filters and pass them to a search function
        System.out.println("Applying filters:");
        System.out.println("Price range: " + minPriceSlider.getValue() + " - " + maxPriceSlider.getValue() + " TND");
        System.out.println("Bedrooms: " + (bedroomsCombo.getValue() != null ? bedroomsCombo.getValue() : "Any"));
        System.out.println("Beds: " + (bedsCombo.getValue() != null ? bedsCombo.getValue() : "Any"));
        System.out.println("Bathrooms: " + (bathroomsCombo.getValue() != null ? bathroomsCombo.getValue() : "Any"));
        
        // Close the dialog after applying filters
>>>>>>> 16484111ec47efb4c5707daedbbb1bbfeddf638c
        closeDialog();
    }
    
    private void closeDialog() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}