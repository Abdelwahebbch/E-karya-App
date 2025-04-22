package com.ekarya.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ekarya.DAO.BlobDAO;
import com.ekarya.DAO.BookingDAO;
import com.ekarya.DAO.PropertyDAO;
import com.ekarya.Models.Booking;
import com.ekarya.Models.ImageModel;
import com.ekarya.Models.Property;
import com.ekarya.Models.User;

public class RentalInterfaceController {
    User currentUser = new User();
    Property currentProperty = new Property();
    DecimalFormat df = new DecimalFormat("#.00");

    @FXML
    private VBox rentedHomesContainer;

    @FXML
    private VBox notificationsContainer;

    @FXML
    private Button searchPropertiesButton;

    @FXML
    private Button reviewButton;

    @FXML
    private Text propertyTitle;

    @FXML
    private ImageView mainImageView;

    @FXML
    private ImageView image1View;

    @FXML
    private ImageView image2View;

    @FXML
    private ImageView image3View;

    @FXML
    private ImageView image4View;

    @FXML
    private Text titleText;

    @FXML
    private Text locationText;

    @FXML
    private Text priceText;

    @FXML
    private Text guestsText;

    @FXML
    private Text bedroomsText;

    @FXML
    private Text bedsText;

    @FXML
    private Text bathroomsText;

    @FXML
    private Text descriptionText;

    @FXML
    private HBox ratingStarsContainer;

    @FXML
    private Label reviewErrorLabel;

    @FXML
    private Text ratingText;

    private int currentRating = 0;
    private List<Button> ratingStars = new ArrayList<>();

    @FXML
    public void initialize(User u) {
        currentUser = u;
        BookingDAO.loadAllBookings();
        PropertyDAO.loadAllProperties();
        refreshBookingsList();
    }

    private void refreshBookingsList() {
        rentedHomesContainer.getChildren().clear();
        for (Property p : PropertyDAO.properties) {
            for (Booking b : BookingDAO.bookings) {
                if (currentUser.getId() == b.getUserId() && p.getId().equals(b.getPropertyId() + "")) {
                    addPropertyToList(p);
                    break;
                }
            }
        }
    }

    public void addPropertyToList(Property property) {
        Button propertyButton = createPropertyButton(property);
        rentedHomesContainer.getChildren().add(propertyButton);
    }

    /**
     * Handles the back button action to navigate back to the home page
     * 
     * @throws Exception
     */
    @FXML
    private void handleBackToHome(ActionEvent event) throws Exception {
        Node node = (Node) event.getSource(); // Works for Button, MenuItem, etc.
        Scene scene = node.getScene();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            Parent root = loader.load();

            MainController controller = loader.getController();
            controller.initData(currentUser);

            // Apply fade-in transition
            root.setOpacity(0); // Start invisible
            scene.setRoot(root); // Set the new root

            FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.millis(01), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles submitting a review for the property
     */
    @FXML
    private void handleSubmitReview(ActionEvent event) {

        // fi kol nazla 3la element ytsajel fi currentp fih id dar nhez l id w na3mel
        // loop fl bookings
        // w nchouf el user eli 7al tawa w 3andou l booking adhika 3mal review wala le
        // ken 3amel review t9lou dja 3malt review sinon t7seb w ta3mel update fl db

        for (Booking b : BookingDAO.bookings) {
            java.sql.Date startDate = b.getStartDate();
            LocalDate startLocalDate = startDate.toLocalDate();
            LocalDate currentDate = LocalDate.now();

            if (currentUser.getId() == b.getUserId() &&
                    currentProperty.getId().equals(b.getPropertyId() + ""))

            {
                if (b.getHasReviewed() != 0) {
                    reviewErrorLabel.setText("you have already review this property");
                    reviewErrorLabel.setVisible(true);
                    reviewErrorLabel.setTextFill(javafx.scene.paint.Color.RED);
                    return;
                }
                if (currentDate.isBefore(startLocalDate)) {
                    reviewErrorLabel.setText("You can only leave a review after your stay has started.");
                    reviewErrorLabel.setVisible(true);
                    reviewErrorLabel.setTextFill(javafx.scene.paint.Color.RED);
                    return;
                }
                double newRating = (currentRating + currentProperty.getRating() * currentProperty.getNumRaters())
                        / (currentProperty.getNumRaters() + 1);
                currentProperty.setRating(newRating);
                currentProperty.setNumRaters(currentProperty.getNumRaters() + 1);
                try {
                    if (PropertyDAO.updatePropertyRating(currentProperty.getId(), currentProperty.getRating(),
                            currentProperty.getNumRaters())) {
                        reviewErrorLabel.setText("Thank you for your feedback! We're glad you shared your experience.");
                        reviewErrorLabel.setVisible(true);
                        reviewErrorLabel.setTextFill(javafx.scene.paint.Color.BLACK);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

        reviewButton.setText("Review Submitted!");

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                javafx.application.Platform.runLater(() -> {
                    reviewButton.setText("Submit Review");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Sets up the rating stars with click handlers
     */
    private void setupRatingStars() {
        // Clear any existing stars
        ratingStarsContainer.getChildren().clear();
        ratingStars.clear();

        // Create 5 star buttons
        for (int i = 1; i <= 5; i++) {
            final int rating = i;
            Button starButton = new Button("★");
            starButton.setStyle("-fx-background-color: transparent; -fx-text-fill: " +
                    (i <= currentRating ? "gold" : "#cccccc") + "; -fx-font-size: 20px;");

            starButton.setOnAction(event -> {
                setRating(rating);
            });

            ratingStars.add(starButton);
            ratingStarsContainer.getChildren().add(starButton);
        }
    }

    /**
     * Sets the rating and updates the star display
     */
    private void setRating(int rating) {
        currentRating = rating;

        // Update star colors
        for (int i = 0; i < ratingStars.size(); i++) {
            Button star = ratingStars.get(i);
            star.setStyle("-fx-background-color: transparent; -fx-text-fill: " +
                    (i < rating ? "gold" : "#cccccc") + "; -fx-font-size: 20px;");
        }
    }

    public void loadPropertyData(String id) {
        for (Property p : PropertyDAO.getProperties()) {
            if (p.getId().equals(id)) {
                currentProperty = p;
                loadPropertyDetails();
                setupRatingStars();
                break;
            }
        }
    }

    private void loadPropertyDetails() {
        ArrayList<File> TheFivePhotos = new ArrayList<>();
        if (currentProperty != null) {
            locationText.setText(currentProperty.getLocation());
            descriptionText.setText(currentProperty.getDescription());
            titleText.setText(currentProperty.getTitle());
            bathroomsText.setText(String.valueOf(currentProperty.getBathrooms()));
            bedroomsText.setText(String.valueOf(currentProperty.getBedrooms()));
            bedsText.setText(String.valueOf(currentProperty.getBeds()));
            guestsText.setText(String.valueOf(currentProperty.getGuests()));
            priceText.setText(currentProperty.getPrice() + "");
            ratingText.setText("★ " + df.format(currentProperty.getRating()));

            try {
                ArrayList<ImageModel> propertyImages = BlobDAO.loadImagesForProperty(currentProperty.getId());
                for (ImageModel i : propertyImages) {
                    TheFivePhotos.add(i.getImgFile());
                }
            } catch (Exception e) {
                System.err.println("Failed to load images for property: " + e.getMessage());
            }

            if (!TheFivePhotos.isEmpty()) {
                mainImageView.setImage(new Image(TheFivePhotos.get(0).toURI().toString()));
                if (TheFivePhotos.size() > 1)
                    image1View.setImage(new Image(TheFivePhotos.get(1).toURI().toString()));
                if (TheFivePhotos.size() > 2)
                    image2View.setImage(new Image(TheFivePhotos.get(2).toURI().toString()));
                if (TheFivePhotos.size() > 3)
                    image3View.setImage(new Image(TheFivePhotos.get(3).toURI().toString()));
                if (TheFivePhotos.size() > 4)
                    image4View.setImage(new Image(TheFivePhotos.get(4).toURI().toString()));
                TheFivePhotos.clear();
            } else {
                mainImageView.setImage(new Image("/pictures/error.png"));
            }
        }
    }

    private Button createPropertyButton(Property property) {
        Button propertyButton = new Button();
        propertyButton.setId("PropertyBtn_" + property.getId());
        propertyButton.setMaxWidth(Double.MAX_VALUE);
        propertyButton.setOnAction(event -> loadPropertyData(property.getId()));
        propertyButton.setStyle("""
                    -fx-background-color: white;
                    -fx-border-color: #E0E0E0;
                    -fx-border-radius: 12;
                    -fx-background-radius: 12;
                    -fx-alignment: center-left;
                    -fx-padding: 10;
                    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.04), 8, 0, 0, 2);
                """);

        Text propertyNameText = new Text(property.getTitle());
        propertyNameText.setStyle(
                "-fx-font-family: 'Montserrat'; -fx-font-weight: bold; -fx-font-size: 14; -fx-fill: #000000;");

        Text priceText = new Text(property.getPrice() + " TND per night");
        priceText.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 12; -fx-fill: #555555;");

        VBox textVBox = new VBox(propertyNameText, priceText);
        HBox hbox = new HBox(10, textVBox);
        propertyButton.setGraphic(hbox);

        return propertyButton;

    }

    @FXML
    private void refreshDetails(ActionEvent event) {
        PropertyDAO.loadAllProperties();
        BookingDAO.loadAllBookings();
        loadPropertyData(currentProperty.getId());
    }

}