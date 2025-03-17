package com.ekarya.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddPropertyController {

    @FXML
    private ComboBox<?> bathroomsCombo;

    @FXML
    private ComboBox<?> bedroomsCombo;

    @FXML
    private ComboBox<?> bedsCombo;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private ComboBox<?> guestsCombo;

    @FXML
    private Button image1Button;

    @FXML
    private ImageView image1View;

    @FXML
    private Button image2Button;

    @FXML
    private ImageView image2View;

    @FXML
    private Button image3Button;

    @FXML
    private ImageView image3View;

    @FXML
    private Button image4Button;

    @FXML
    private ImageView image4View;

    @FXML
    private TextField locationField;

    @FXML
    private Button mainImageButton;

    @FXML
    private ImageView mainImageView;

    @FXML
    private TextField priceField;

    @FXML
    private Button submitButton;

    @FXML
    private TextField titleField;

    @FXML
    void handleCloseButton(MouseEvent event) {
        // Get the current stage from the event source
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        // Close the stage
        stage.close();
    }

}
