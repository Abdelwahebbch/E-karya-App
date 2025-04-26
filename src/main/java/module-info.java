module com.ekarya {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;
    exports com.ekarya.app;
    opens com.ekarya.controller to javafx.fxml;
}