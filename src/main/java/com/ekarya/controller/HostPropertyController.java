package com.ekarya.controller;

import com.ekarya.app.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HostPropertyController extends BaseController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private TextField propertyTitleField;

    @FXML
    private ComboBox<String> propertyTypeComboBox;

    @FXML
    private TextField locationField;

    @FXML
    private TextField addressField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private Button addPhotoButton;

    @FXML
    private HBox photosContainer;

    @FXML
    private TextField priceField;

    @FXML
    private ComboBox<Integer> minStayComboBox;

    @FXML
    private DatePicker availableFromPicker;

    @FXML
    private DatePicker availableUntilPicker;

    @FXML
    private CheckBox wifiCheckBox;

    @FXML
    private CheckBox tvCheckBox;

    @FXML
    private CheckBox acCheckBox;

    @FXML
    private CheckBox kitchenCheckBox;

    @FXML
    private CheckBox parkingCheckBox;

    @FXML
    private CheckBox poolCheckBox;

    @FXML
    private CheckBox washingMachineCheckBox;

    @FXML
    private CheckBox petsCheckBox;

    @FXML
    private CheckBox smokingCheckBox;

    @FXML
    private TextArea rulesArea;

    @FXML
    private Button cancelButton;

    @FXML
    private Button submitButton;

    // List to store photo files
    private List<File> photoFiles = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize property type combo box
        ObservableList<String> propertyTypes = FXCollections.observableArrayList(
                "Apartment", "House", "Villa", "Cottage", "Guesthouse", "Hotel", "Hostel", "Other"
        );
        propertyTypeComboBox.setItems(propertyTypes);

        // Initialize minimum stay combo box
        ObservableList<Integer> minStayOptions = FXCollections.observableArrayList(
                1, 2, 3, 4, 5, 6, 7, 14, 30
        );
        minStayComboBox.setItems(minStayOptions);

        // Set default values for date pickers
        availableFromPicker.setValue(LocalDate.now());
        availableUntilPicker.setValue(LocalDate.now().plusMonths(6));

        // Call the initialize method from BaseController
        initialize();
    }

    @Override
    public void initialize() {
        // Additional initialization if needed
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
    // Navigate back to the home page
        navigateTo(SceneManager.AppScene.HOME);
    }

    @FXML
    private void handleAddPhoto(ActionEvent event) {
        // Check if we already have 5 photos
        if (photoFiles.size() >= 5) {
            showAlert("Limit Reached", "You can only add up to 5 photos.");
            return;
        }

        // Create file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Property Photo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        // Show open file dialog
        File file = fileChooser.showOpenDialog(addPhotoButton.getScene().getWindow());
        if (file != null) {
            try {
                // Add file to list
                photoFiles.add(file);

                // Create thumbnail
                Image image = new Image(file.toURI().toString(), 100, 100, true, true);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(80);
                imageView.setFitHeight(80);

                // Create container for thumbnail with remove button
                VBox photoBox = new VBox(5);
                photoBox.setPrefSize(100, 100);
                photoBox.setStyle("-fx-border-color: #e0e0e0; -fx-border-radius: 5; -fx-padding: 5;");

                // Add remove button
                Button removeButton = new Button("Remove");
                removeButton.setStyle("-fx-background-color: #FF385C; -fx-text-fill: white;");
                removeButton.setOnAction(e -> {
                    photoFiles.remove(file);
                    photosContainer.getChildren().remove(photoBox);
                    updatePhotosContainer();
                });

                photoBox.getChildren().addAll(imageView, removeButton);

                // Clear the "No photos" placeholder if it's the first photo
                if (photoFiles.size() == 1) {
                    photosContainer.getChildren().clear();
                }

                // Add to container
                photosContainer.getChildren().add(photoBox);

            } catch (Exception e) {
                showError("Error loading image: " + e.getMessage());
            }
        }
    }

    private void updatePhotosContainer() {
        // If no photos, show placeholder
        if (photoFiles.isEmpty()) {
            photosContainer.getChildren().clear();
            VBox placeholder = new VBox();
            placeholder.setAlignment(javafx.geometry.Pos.CENTER);
            placeholder.setPrefSize(100, 100);
            placeholder.setStyle("-fx-border-color: #e0e0e0; -fx-border-style: dashed; -fx-border-radius: 5;");
            placeholder.getChildren().add(new Label("No photos added yet"));
            photosContainer.getChildren().add(placeholder);
        }
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        // Show confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Listing");
        alert.setHeaderText("Are you sure you want to cancel?");
        alert.setContentText("All entered information will be lost.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Navigate back to home
                navigateTo(SceneManager.AppScene.HOME);
            }
        });
    }

    @FXML
    private void handleSubmitButton(ActionEvent event) {
        // Validate required fields
        if (!validateForm()) {
            return;
        }

        // In a real application, this would save the property listing to a database
        // For now, just show a success message and navigate back to home

        // Collect amenities
        List<String> amenities = new ArrayList<>();
        if (wifiCheckBox.isSelected()) amenities.add("WiFi");
        if (tvCheckBox.isSelected()) amenities.add("TV");
        if (acCheckBox.isSelected()) amenities.add("Air Conditioning");
        if (kitchenCheckBox.isSelected()) amenities.add("Kitchen");
        if (parkingCheckBox.isSelected()) amenities.add("Free Parking");
        if (poolCheckBox.isSelected()) amenities.add("Swimming Pool");
        if (washingMachineCheckBox.isSelected()) amenities.add("Washing Machine");
        if (petsCheckBox.isSelected()) amenities.add("Pets Allowed");
        if (smokingCheckBox.isSelected()) amenities.add("Smoking Allowed");

        // Show success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Property Listed Successfully");
        alert.setContentText("Your property has been listed successfully. You will be notified when guests book your property.");

        alert.showAndWait();

        // Navigate back to home
        navigateTo(SceneManager.AppScene.HOME);
    }

    private boolean validateForm() {
        // Validate property title
        if (propertyTitleField.getText().trim().isEmpty()) {
            showError("Please enter a property title");
            propertyTitleField.requestFocus();
            return false;
        }

        // Validate property type
        if (propertyTypeComboBox.getValue() == null) {
            showError("Please select a property type");
            propertyTypeComboBox.requestFocus();
            return false;
        }

        // Validate location
        if (locationField.getText().trim().isEmpty()) {
            showError("Please enter a location");
            locationField.requestFocus();
            return false;
        }

        // Validate address
        if (addressField.getText().trim().isEmpty()) {
            showError("Please enter an address");
            addressField.requestFocus();
            return false;
        }

        // Validate description
        if (descriptionArea.getText().trim().isEmpty()) {
            showError("Please enter a description");
            descriptionArea.requestFocus();
            return false;
        }

        // Validate price
        try {
            double price = Double.parseDouble(priceField.getText().trim());
            if (price <= 0) {
                showError("Please enter a valid price (greater than 0)");
                priceField.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Please enter a valid price");
            priceField.requestFocus();
            return false;
        }

        // Validate minimum stay
        if (minStayComboBox.getValue() == null) {
            showError("Please select a minimum stay");
            minStayComboBox.requestFocus();
            return false;
        }

        // Validate availability dates
        if (availableFromPicker.getValue() == null) {
            showError("Please select an availability start date");
            availableFromPicker.requestFocus();
            return false;
        }

        if (availableUntilPicker.getValue() == null) {
            showError("Please select an availability end date");
            availableUntilPicker.requestFocus();
            return false;
        }

        if (availableUntilPicker.getValue().isBefore(availableFromPicker.getValue())) {
            showError("Availability end date must be after start date");
            availableUntilPicker.requestFocus();
            return false;
        }

        // All validations passed
        return true;
    }
}
