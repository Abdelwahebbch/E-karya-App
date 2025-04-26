package com.ekarya.controller;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SignInControllerTest {

    private SignInController controller;

    @BeforeAll
    public static void initJfxRuntime() {
        // Initialize JavaFX Toolkit only once
        Platform.startup(() -> {});
    }

    @BeforeEach
    public void setUp() {
        controller = new SignInController();

        // Inject dummy JavaFX UI components
        controller.setEmailField(new TextField());
        controller.setPasswordField(new PasswordField());
        Label errorLabel = new Label();
        errorLabel.setVisible(false);
        controller.setErrorLabel(errorLabel);
    }

    @Test
    public void handleSignIn_showsErrorWhenFieldsAreEmpty() throws Exception {
        // Leave email and password empty
        controller.getEmailField().setText("");
        controller.getPasswordField().setText("");

        // Call the method under test
        controller.handleSignIn(null);

        // Check that error label is visible with correct message
        assertTrue(controller.getErrorLabel().isVisible());
        assertEquals("Email and password are required", controller.getErrorLabel().getText());
    }

    @Test
    public void handleSignIn_noErrorWhenFieldsAreFilled() throws Exception {
        // Provide valid input
        controller.getEmailField().setText("test@example.com");
        controller.getPasswordField().setText("password123");

        // Call the method
        controller.handleSignIn(null);

        // Check that error label is still invisible (no error shown)
        assertFalse(controller.getErrorLabel().isVisible());
    }
}
