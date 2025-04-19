package com.ekarya.controller;

import java.io.IOException;
import com.ekarya.Models.Property;
import com.ekarya.DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AddPropertyController {
    @FXML
    private TextField bathroomsField;

    @FXML
    private TextField bedroomsField;

    @FXML
    private TextField bedsField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField guestsField;

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
    void createPropertyButton(ActionEvent event) {
        Property data = collectPropertyData();
        try {
            if (data != null && UserDAO.savePropertyDataToDataBase(data)) {
              //  System.out.println("Collected property data: " + data);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Property listing created successfully!");
                alert.showAndWait();
                handleBackToDashboard(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Property collectPropertyData() {
        // Create a new PropertyData object to store all the collected information
        Property propertyData = new Property();

        // Collect text field data
        propertyData.setTitle(titleField.getText());
        propertyData.setLocation(locationField.getText());

        // Parse numeric fields with validation
        try {
            if (!guestsField.getText().isEmpty()) {
                propertyData.setGuests(Integer.parseInt(guestsField.getText()));
            }

            if (!bedroomsField.getText().isEmpty()) {
                propertyData.setBedrooms(Integer.parseInt(bedroomsField.getText()));
            }

            if (!bedsField.getText().isEmpty()) {
                propertyData.setBeds(Integer.parseInt(bedsField.getText()));
            }

            if (!bathroomsField.getText().isEmpty()) {
                propertyData.setBathrooms(Integer.parseInt(bathroomsField.getText()));
            }

            if (!priceField.getText().isEmpty()) {
                propertyData.setPrice(Double.parseDouble(priceField.getText()));
            }
        } catch (NumberFormatException e) {
            // Handle parsing errors
            showAlert("Invalid Input", "Please enter valid numbers for guests, bedrooms, beds, bathrooms, and price.");
            return null;
        }

        // Collect description
        propertyData.setDescription(descriptionArea.getText());

        // Handle images
        // Note: The actual image processing depends on how you've implemented the image
        // selection
        // The following code assumes you have methods to get the selected image files

        /*
         * For the image handling, we're collecting references to the images that were
         * selected.
         * In a real implementation, you might:
         * 1. Save the images to a specific directory
         * 2. Upload them to a server
         * 3. Store file paths or URLs in your database
         */

        // List<File> propertyImages = new ArrayList<>();

        // // Check if main image was selected (assuming the ImageView visibility is set
        // to
        // // true when an image is selected)
        // if (mainImageView.isVisible()) {
        // // Get the image file - implementation depends on how you've handled image
        // // selection
        // File mainImageFile = getImageFileFromImageView(mainImageView);
        // if (mainImageFile != null) {
        // propertyData.setMainImage(mainImageFile);
        // }
        // }

        // // Check additional images
        // if (image1View.isVisible()) {
        // File image1File = getImageFileFromImageView(image1View);
        // if (image1File != null) {
        // propertyImages.add(image1File);
        // }
        // }

        // if (image2View.isVisible()) {
        // File image2File = getImageFileFromImageView(image2View);
        // if (image2File != null) {
        // propertyImages.add(image2File);
        // }
        // }

        // if (image3View.isVisible()) {
        // File image3File = getImageFileFromImageView(image3View);
        // if (image3File != null) {
        // propertyImages.add(image3File);
        // }
        // }

        // if (image4View.isVisible()) {
        // File image4File = getImageFileFromImageView(image4View);
        // if (image4File != null) {
        // propertyImages.add(image4File);
        // }
        // }

        // propertyData.setAdditionalImages(propertyImages);

        // Validate required fields
        if (propertyData.getTitle().isEmpty() || propertyData.getLocation().isEmpty() ||
                propertyData.getDescription().isEmpty() /* || propertyData.getMainImage() == null */) {
            showAlert("Missing Information", "Please fill in all required fields and add a main image.");
            return null;
        }

        return propertyData;
    }

    // private File getImageFileFromImageView(ImageView mainImageView2) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getImageFileFromImageView'");
    // }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void handleBackToDashboard(ActionEvent event) {
        try {
            // Load the home page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropretyDashboard.fxml"));
            Parent homePageRoot = loader.load();

            // Get the current stage
            Stage stage = (Stage) submitButton.getScene().getWindow();

            // Set the home page scene
            Scene scene = new Scene(homePageRoot);
            stage.setScene(scene);
            stage.show();
            stage.setFullScreen(true);
        } catch (IOException e) {
            System.err.println("Error loading Dashboard.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
