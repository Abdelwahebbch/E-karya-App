package com.ekarya.controller;


import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import com.ekarya.DAO.BlobDAO;
import com.ekarya.DAO.PropertyDAO;
import com.ekarya.Models.User;
import com.ekarya.Models.ImageModel;
import com.ekarya.Models.Property;
import com.ekarya.utile.DatabaseConnection;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController {

    User currentUser = new User();
    private int column = 0;
    private int row = 0;
    private final int MAX_COLUMNS = 2;
    private Property cuProperty = new Property();
    DecimalFormat df = new DecimalFormat("#.00");

    @FXML
    private TextField destinationField;

    @FXML
    private DatePicker endField;

    @FXML
    private Button filterButton;

    @FXML
    private TextField nbGuestsField;

    @FXML
    private GridPane propertiesGridPane;

    @FXML
    private DatePicker startField;

    // Event handlers
    public void initData(User user) throws Exception {
        this.currentUser = user;
        PropertyDAO.loadAllProperties();
        refreshPropertyList();
    }

    public void addPropertyToGrid(Property property) throws Exception {
        ArrayList<ImageModel> x = BlobDAO.loadImagesForProperty(property.getId());
    
        if (x.isEmpty()) {
            System.out.println("No images found for property with ID: " + property.getId());
            return; 
        }
    
        Node card = createListingCard(property, x.get(0));
    
        GridPane.setColumnIndex(card, column);
        GridPane.setRowIndex(card, row);
    
        propertiesGridPane.getChildren().add(card);
    
        column++;
        if (column > MAX_COLUMNS) {
            column = 0;
            row++;
        }
    }
    

    public void LoadPropertyData(String id, MouseEvent e) {
        for (Property p : PropertyDAO.getProperties()) {
            if (p.getId().equals(id)) {
                cuProperty = p;
                handleListingClick(e);
                break;
            }
        }

    }

    private void refreshPropertyList() throws Exception {
        propertiesGridPane.getChildren().clear();
        for (Property p : PropertyDAO.properties) {
            if(p.getStatus()==0)
                addPropertyToGrid(p);
        }
    }

    @FXML
    public void handleListingClick(MouseEvent event) {
        Node node = (Node) event.getSource(); // Works for Button, MenuItem, etc.
        Scene scene = node.getScene();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropertyDetail.fxml"));
            Parent root = loader.load();

            PropertyDetailController propertyDetailController = loader.getController();
            propertyDetailController.initData(cuProperty, currentUser);

            root.setOpacity(0); // Start invisible
            scene.setRoot(root); // Set the new root

            FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.millis(01), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();

        } catch (IOException e) {
            handleException("Error loading property description", e);
        }
    }

    @FXML
    private void handleFilterClick() {
        try {
            // Load the filter FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropertyFilter.fxml"));
            Parent root = loader.load();

            // Create a new stage for the filter dialog
            Stage filterStage = new Stage();
            filterStage.setTitle("Filtres");

            // Make it a modal dialog (blocks interaction with the main window)
            filterStage.initModality(Modality.APPLICATION_MODAL);
            filterStage.initStyle(StageStyle.UNDECORATED);

            // Set the scene and display
            Scene scene = new Scene(root);
            filterStage.setScene(scene);
            filterStage.centerOnScreen();
            filterStage.showAndWait();

            // After the dialog is closed, you can retrieve filter values if needed
            // PropertyFilterController controller = loader.getController();
            // Use controller methods to get selected filter values
        } catch (IOException e) {
            handleException("Error loading filter interface", e);
        }
    }

    @FXML
    void ToDashBoard(ActionEvent event) throws Exception {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropertyDashboard.fxml"));
            Parent root = loader.load();
            PropertyDashboardController propertyDashboardController = loader.getController();
            propertyDashboardController.initialize(currentUser);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ToBookings(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RentalInterface.fxml"));
            Parent root = loader.load();
            RentalInterfaceController rentalInterfaceController = loader.getController();
            rentalInterfaceController.initialize(currentUser);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ToProfile(ActionEvent event) throws IOException {

        MenuItem menuItem = (MenuItem) event.getSource();
        Scene scene = menuItem.getParentPopup().getOwnerWindow().getScene();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProfileManager.fxml"));
            Parent root = loader.load();
            ProfileManagementController profilcontroller = loader.getController();
            profilcontroller.initData(currentUser);
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handelLogOut(ActionEvent event) {
        try {
            DatabaseConnection.closeConnection();

            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Logout Confirmation");
            confirmDialog.setHeaderText("Are you sure you want to logout?");
            // confirmDialog.setContentText("Any unsaved changes will be lost.");

            Optional<ButtonType> result = confirmDialog.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                System.out.println("User logged out successfully");

                // Platform.exit(); // anotherrr way to exit from JavaFX applications
                System.exit(0);

            }
        } catch (Exception e) {
            System.err.println("Error during logout: " + e.getMessage());
            e.printStackTrace();

            // Show error dialog
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Logout Error");
            errorAlert.setContentText("An error occurred while trying to logout: " + e.getMessage());
            errorAlert.showAndWait();
        }
    }

    private void handleException(String message, Exception e) {
        System.err.println(message + ": " + e.getMessage());
        e.printStackTrace();

    }

    public VBox createListingCard(Property p, ImageModel i) {
        VBox card = new VBox();
        card.setUserData(p);
        card.setSpacing(8);
        card.setPadding(new Insets(8));
        card.setStyle(
                "-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 10; -fx-background-radius: 10;");
        card.setCursor(Cursor.HAND);
        card.setEffect(new DropShadow());

        // ---- Image ----
        ImageView imageView = new ImageView();

        try {
            if (i != null && i.getImgFile() != null && i.getImgFile().exists()) {
                Image image = new Image(i.getImgFile().toURI().toString());
                imageView.setImage(image);
            } else {
                // Fallback image if none provided
                URL fallbackUrl = getClass().getResource("/pictures/error.png");
                if (fallbackUrl != null) {
                    imageView.setImage(new Image(fallbackUrl.toExternalForm()));
                }
            }
        } catch (Exception e) {
            System.err.println("Could not load property image: " + e.getMessage());
        }

        imageView.setFitWidth(300);
        imageView.setFitHeight(220);
        imageView.setPreserveRatio(true);

        StackPane imagePane = new StackPane(imageView);
        imagePane.setAlignment(Pos.CENTER);
        imagePane.setStyle(
                "-fx-background-color: #f0f0f0; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0;");

        // ---- Content Box ----
        VBox contentBox = new VBox(5);
        contentBox.setPadding(new Insets(8));

        // ---- Top Row ----
        HBox topRow = new HBox();
        Label location = new Label(p.getLocation());
        location.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label star = new Label("★");
        star.setTextFill(Color.ORANGE);
        Label rating = new Label(df.format(p.getRating())+"");
        Label numRaters= new Label("("+p.getNumRaters()+" reviews)");

        HBox ratingBox = new HBox(5, star, rating, numRaters);
        topRow.getChildren().addAll(location, spacer, ratingBox);

        // ---- Subtitle and Price ----
        Label subtitle = new Label(p.getTitle());
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 13));

        HBox priceRow = new HBox(5);
        Label price = new Label("TND " + p.getPrice());
        price.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        Label perNight = new Label("per night");
        perNight.setFont(Font.font(12));
        perNight.setTextFill(Color.GRAY);

        priceRow.getChildren().addAll(price, perNight);

        contentBox.getChildren().addAll(topRow, subtitle, priceRow);
        card.getChildren().addAll(imagePane, contentBox);

        // ---- Click Event ----
        card.setOnMouseClicked(event -> LoadPropertyData(p.getId(), event));

        return card;
    }

    @FXML
    void handleSearch(ActionEvent event) throws Exception {
        this.column = 0;
        this.row = 0;
        LocalDate checkInDate = startField.getValue();
        LocalDate checkOutDate = endField.getValue();

        propertiesGridPane.getChildren().clear();
        for (Property p : PropertyDAO.loadSpecificPropertys(String.valueOf(destinationField.getText()), checkInDate,
                checkOutDate,
                nbGuestsField.getText())) {

            addPropertyToGrid(p);
            // System.out.println("------------->> Propertys ID " + p.getId());
            // System.out.println("------------->> Propertys Location " + p.getLocation());
        }

    }

}
