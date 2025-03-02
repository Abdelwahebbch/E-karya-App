module com.ekarya {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.web;
    
    exports com.ekarya.app;
    opens com.ekarya.controller to javafx.fxml;
}