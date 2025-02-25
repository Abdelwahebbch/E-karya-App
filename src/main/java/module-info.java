module com.ekarya {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    
    exports com.ekarya.app;
     opens com.ekarya.controller to javafx.fxml;
}