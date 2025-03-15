package com.ekarya.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

public class PropertyFilterController {
    @FXML private Button closeButton;
    @FXML private Slider minPriceSlider;
    @FXML private Slider maxPriceSlider;
    @FXML private Text minPriceText;
    @FXML private Text maxPriceText;
    @FXML private ComboBox<Integer> bedroomsCombo;
    @FXML private ComboBox<Integer> bedsCombo;
    @FXML private ComboBox<Integer> bathroomsCombo;
    @FXML private Button typeApartmentButton;
    @FXML private Button typeHouseButton;
    @FXML private Button typeVillaButton;
    @FXML private Button typeChaletButton;
    @FXML private Button resetButton;
    @FXML private Button applyButton;
    
    @FXML
    public void initialize() {
        // Initialize combo boxes
        bedroomsCombo.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        bedsCombo.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        bathroomsCombo.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Set up price slider listeners
        minPriceSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Ensure min price doesn't exceed max price
            if (newVal.doubleValue() > maxPriceSlider.getValue()) {
                minPriceSlider.setValue(maxPriceSlider.getValue());
            }
            minPriceText.setText(String.format("%.0f €", minPriceSlider.getValue()));
        });
        
        maxPriceSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Ensure max price isn't less than min price
            if (newVal.doubleValue() < minPriceSlider.getValue()) {
                maxPriceSlider.setValue(minPriceSlider.getValue());
            }
            maxPriceText.setText(String.format("%.0f €", maxPriceSlider.getValue()));
        });
        
        // Set up button click handlers for property types and amenities
        setupToggleButton(typeApartmentButton);
        setupToggleButton(typeHouseButton);
        setupToggleButton(typeVillaButton);
        setupToggleButton(typeChaletButton);
        
        // Set up reset button
        resetButton.setOnAction(e -> resetFilters());
        
        // Set up apply button
        applyButton.setOnAction(e -> applyFilters());
        
        // Set up close button
        closeButton.setOnAction(e -> closeDialog());
    }
    
    private void setupToggleButton(Button button) {
        button.setOnAction(e -> {
            if (button.getStyle().contains("-fx-background-color: #FF385C")) {
                // Deselect
                button.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 20;");
                button.setTextFill(javafx.scene.paint.Color.BLACK);
            } else {
                // Select
                button.setStyle("-fx-background-color: #FF385C; -fx-text-fill: white; -fx-border-radius: 20;");
                button.setTextFill(javafx.scene.paint.Color.WHITE);
            }
        });
    }
    
    private void resetFilters() {
        // Reset price sliders
        minPriceSlider.setValue(0);
        maxPriceSlider.setValue(1000);
        
        // Reset combo boxes
        bedroomsCombo.setValue(null);
        bedsCombo.setValue(null);
        bathroomsCombo.setValue(null);
        
        // Reset property type buttons
        resetButtonStyle(typeApartmentButton);
        resetButtonStyle(typeHouseButton);
        resetButtonStyle(typeVillaButton);
        resetButtonStyle(typeChaletButton);
        
    }
    
    private void resetButtonStyle(Button button) {
        button.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 20;");
        button.setTextFill(javafx.scene.paint.Color.BLACK);
    }
    
    private void applyFilters() {
        // Implement filter application logic here
        // This would typically gather all selected filters and pass them to a search function
        System.out.println("Applying filters:");
        System.out.println("Price range: " + minPriceSlider.getValue() + " - " + maxPriceSlider.getValue() + " €");
        System.out.println("Bedrooms: " + (bedroomsCombo.getValue() != null ? bedroomsCombo.getValue() : "Any"));
        System.out.println("Beds: " + (bedsCombo.getValue() != null ? bedsCombo.getValue() : "Any"));
        System.out.println("Bathrooms: " + (bathroomsCombo.getValue() != null ? bathroomsCombo.getValue() : "Any"));
        
        // Close the dialog after applying filters
        closeDialog();
    }
    
    private void closeDialog() {
        // Close the dialog
        closeButton.getScene().getWindow().hide();
    }
}