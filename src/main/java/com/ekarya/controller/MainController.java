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
import javafx.stage.Stage;

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
    public void handleListingClick(MouseEvent event) throws IOException{ 
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/propretyDesc.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        
    }

}


