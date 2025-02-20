package com.ekarya.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("C:\\Users\\henit\\OneDrive\\Documents\\GitHub\\E-karya-App\\src\\main\\resources\\fxml\\signup.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);/* 
         * String javaVersion = System.getProperty("java.version");
         * String javafxVersion = System.getProperty("javafx.version");
         * Label label = new Label("Hello, JavaFX " + javafxVersion +
         * ", running on Java " + javaVersion + ".");
         * Scene scene = new Scene(new StackPane(label), 640, 480);
         * stage.setScene(scene);*/
          stage.show();
         
    }

    public static void main(String[] args) {
        launch();
    }
}