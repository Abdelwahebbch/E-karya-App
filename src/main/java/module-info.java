module com.ekarya {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens com.ekarya.app to javafx.fxml;
    opens com.ekarya.controller to javafx.fxml;
    
    exports com.ekarya.app;
    exports com.ekarya.controller;
}