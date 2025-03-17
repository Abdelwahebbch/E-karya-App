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
    void handleCloseButton(MouseEvent event) {
        // Get the current stage from the event source
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        // Close the stage
        stage.close();
    }
}
