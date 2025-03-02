package com.ekarya.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.concurrent.Worker;

public class WebViewController {

    @FXML
    private WebView webView;
    @FXML
    private Label res;

    private WebEngine webEngine;

    @FXML
    public void initialize() {
        webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("/html/MessagesBoard.html").toExternalForm());

    }

    // @FXML
    // public void sendDataToWeb() {
    //     webEngine.executeScript("receiveDataFromJava('Hello from JavaFX!')");
    // }

    // @FXML
    // public void receiveData(String data) {
    //     webEngine.executeScript("receiveDataFromJava(data)");
    // }
}
