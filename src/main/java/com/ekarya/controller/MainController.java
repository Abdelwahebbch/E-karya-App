package com.ekarya.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
    void handleListingClick(ActionEvent event) {

    }

    @FXML
    void handleSearch(ActionEvent event) {

    }

}
