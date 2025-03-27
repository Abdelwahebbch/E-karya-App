package com.ekarya.controller;

import com.ekarya.app.SceneManager;
import javafx.scene.control.Alert;

/**
 * Base controller class that provides common functionality for all controllers
 */
public abstract class BaseController {
    
    /**
     * Navigate to a scene
     */
    protected void navigateTo(SceneManager.AppScene scene) {
        SceneManager.getInstance().switchToScene(scene);
    }
    
    /**
     * Navigate to a scene without transition
     */
    protected void navigateToWithoutTransition(SceneManager.AppScene scene) {
        SceneManager.getInstance().switchToScene(scene, false);
    }
    
    /**
     * Show an alert dialog
     */
    protected void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Show an error dialog
     */
    protected void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Initialize method that can be overridden by subclasses
     */
    public void initialize() {
        // Default implementation does nothing
    }
}