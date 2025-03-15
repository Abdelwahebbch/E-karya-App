package com.ekarya.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RoomDetailController {

    @FXML
    private ComboBox<?> nbVoyage;

        @FXML
    public void backToHomePage(MouseEvent event) throws IOException{ 
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/HomePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        
    }

}
