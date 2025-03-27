package com.ekarya.app;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Manages scene transitions and navigation throughout the application.
 */
public class SceneManager {
    
    // Singleton instance
    private static SceneManager instance;
    
    // The main application stage
    private Stage primaryStage;
    
    // Available scenes in the application
    public enum AppScene {
        LOGIN("signin.fxml", "E-karya - Sign In"),
        REGISTER("signup.fxml", "E-karya - Sign Up"),
        HOME("Homepage.fxml", "E-karya - Home"),
        ROOM_DETAIL("RoomDetail.fxml", "E-karya - Room Details"),
        HOST_PROPERTY("HostProperty.fxml", "E-karya - Host Your Property"),
        BOOKING_CONFIRMATION("BookingConfirmation.fxml", "E-karya - Booking Confirmation"),
        USER_PROFILE("UserProfile.fxml", "E-karya - Your Profile");
        
        private final String fxmlFileName;
        private final String title;
        
        AppScene(String fxmlFileName, String title) {
            this.fxmlFileName = fxmlFileName;
            this.title = title;
        }
        
        public String getFxmlFileName() {
            return fxmlFileName;
        }
        
        public String getTitle() {
            return title;
        }
    }
    
    // Private constructor for singleton
    private SceneManager() {
    }
    
    /**
     * Get the singleton instance of SceneManager
     */
    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }
    
    /**
     * Initialize the SceneManager with the primary stage
     */
    public void initialize(Stage stage) {
        this.primaryStage = stage;
    }
    
    /**
     * Switch to a scene with a fade transition
     */
    public void switchToScene(AppScene scene) {
        switchToScene(scene, true);
    }
    
    /**
     * Switch to a scene with optional transition
     */
    public void switchToScene(AppScene scene, boolean withTransition) {
        try {
            // Load the scene
            Parent root = loadFXML(scene.getFxmlFileName());
            
            // Set the scene title
            primaryStage.setTitle(scene.getTitle());
            
            // If we're using a transition
            if (withTransition && primaryStage.getScene() != null) {
                // Create fade-out transition for current scene
                FadeTransition fadeOut = new FadeTransition(Duration.millis(300), primaryStage.getScene().getRoot());
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                
                // When fade-out completes, change scene and fade in
                fadeOut.setOnFinished(e -> {
                    // Change to new scene
                    changeScene(root);
                    
                    // Create fade-in transition for new scene
                    FadeTransition fadeIn = new FadeTransition(Duration.millis(300), root);
                    fadeIn.setFromValue(0);
                    fadeIn.setToValue(1);
                    fadeIn.play();
                });
                
                // Start the fade-out transition
                fadeOut.play();
            } else {
                // No transition, just change the scene
                changeScene(root);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading scene: " + scene.getFxmlFileName());
        }
    }
    
    /**
     * Change to a new scene with the given root
     */
    private void changeScene(Parent root) {
        // If there's no scene yet, create one
        if (primaryStage.getScene() == null) {
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } else {
            // Otherwise, just change the root
            primaryStage.getScene().setRoot(root);
        }
    }
    
    /**
     * Load an FXML file and return its root element
     */
    private Parent loadFXML(String fxmlFileName) throws IOException {
        // Try different possible locations for the FXML file
        
        // First try: Using resource loading
        URL fxmlUrl = getClass().getResource("/fxml/" + fxmlFileName);
        if (fxmlUrl != null) {
            System.out.println("Loading FXML from resource: /fxml/" + fxmlFileName);
            return FXMLLoader.load(fxmlUrl);
        }
        
        // Second try: Using resource loading with a different path
        fxmlUrl = getClass().getResource("/" + fxmlFileName);
        if (fxmlUrl != null) {
            System.out.println("Loading FXML from resource: /" + fxmlFileName);
            return FXMLLoader.load(fxmlUrl);
        }
        
        // Third try: Using direct file path
        File fxmlFile = new File("E-karya-App/src/main/resources/fxml/" + fxmlFileName);
        if (fxmlFile.exists()) {
            System.out.println("Loading FXML from file: " + fxmlFile.getAbsolutePath());
            return FXMLLoader.load(fxmlFile.toURI().toURL());
        }
        
        // Fourth try: Using direct file path with a different location
        fxmlFile = new File("src/main/resources/fxml/" + fxmlFileName);
        if (fxmlFile.exists()) {
            System.out.println("Loading FXML from file: " + fxmlFile.getAbsolutePath());
            return FXMLLoader.load(fxmlFile.toURI().toURL());
        }
        
        // If we get here, we couldn't find the file
        throw new IOException("Could not find FXML file: " + fxmlFileName);
    }
    
    /**
     * Get the controller for a scene
     */
    public <T> T getController(AppScene scene) throws IOException {
        // Try different possible locations for the FXML file
        
        // First try: Using resource loading
        URL fxmlUrl = getClass().getResource("/fxml/" + scene.getFxmlFileName());
        if (fxmlUrl != null) {
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            loader.load();
            return loader.getController();
        }
        
        // Second try: Using resource loading with a different path
        fxmlUrl = getClass().getResource("/" + scene.getFxmlFileName());
        if (fxmlUrl != null) {
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            loader.load();
            return loader.getController();
        }
        
        // Third try: Using direct file path
        File fxmlFile = new File("E-karya-App/src/main/resources/fxml/" + scene.getFxmlFileName());
        if (fxmlFile.exists()) {
            FXMLLoader loader = new FXMLLoader(fxmlFile.toURI().toURL());
            loader.load();
            return loader.getController();
        }
        
        // Fourth try: Using direct file path with a different location
        fxmlFile = new File("src/main/resources/fxml/" + scene.getFxmlFileName());
        if (fxmlFile.exists()) {
            FXMLLoader loader = new FXMLLoader(fxmlFile.toURI().toURL());
            loader.load();
            return loader.getController();
        }
        
        // If we get here, we couldn't find the file
        throw new IOException("Could not find FXML file: " + scene.getFxmlFileName());
    }
}