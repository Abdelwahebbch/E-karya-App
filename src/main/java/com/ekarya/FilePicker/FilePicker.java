    package com.ekarya.FilePicker;

    import javafx.stage.FileChooser;
    import javafx.stage.Stage;
    import java.io.File;
    
    public class FilePicker {
        public File chooseFile(Stage stage) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select a File");

            // Optional: set extension filters (e.g., only show images)
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));

            // Show open file dialog
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                return selectedFile;
            } else {
                System.out.println("File selection canceled.");
                return null;
            }
        }
    }
