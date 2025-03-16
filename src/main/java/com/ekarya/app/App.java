package com.ekarya.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Parent root = FXMLLoader.load(getClass().getResource("/fxml/addHome.fxml"));
        // Parent root =
        // FXMLLoader.load(getClass().getResource("/fxml/DashBoard.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/HomePage.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("/fxml/profile.fxml"));
        // Parent root =
        // FXMLLoader.load(getClass().getResource("/fxml/propretyFilter.fxml"));
         //Parent root = FXMLLoader.load(getClass().getResource("/fxml/propretyDesc.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("E-karya");

    }

    public static void main(String[] args) {
        launch(args);
    }
}