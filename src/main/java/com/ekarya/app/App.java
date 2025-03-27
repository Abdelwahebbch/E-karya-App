package com.ekarya.app;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Initialize the SceneManager with the primary stage
            SceneManager.getInstance().initialize(stage);
            
            // Switch to the login scene (or whichever scene you want to start with)
            // You can change this to any scene you want to start with
            SceneManager.getInstance().switchToScene(SceneManager.AppScene.HOME, false);
            
            // Show the stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorAlert("Application Error", "Failed to start application", e.getMessage());
        }
    }

    private void showErrorAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}