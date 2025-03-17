package com.ekarya.controller;


import javafx.fxml.FXML;
import javafx.scene.Node;
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
