package com.ekarya.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import javafx.scene.input.MouseEvent;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import javafx.application.Platform;
import java.util.concurrent.CountDownLatch;


public class ProfileManagementControllerTest {

    @BeforeAll
public static void initJFX() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);
    Platform.startup(latch::countDown);
    latch.await();
}

/** 
 * @throws Exception
*/ 
@Test
void testHandleCloseButton() throws Exception {
    CountDownLatch testLatch = new CountDownLatch(1);

    Platform.runLater(() -> {
        try {
            ProfileManagementController test = new ProfileManagementController();

            TextField testing = new TextField("eli howa");
            DatePicker TexTest = new DatePicker();
            TexTest.setValue(LocalDate.of(2000, 1, 1));
            TextArea bioTest = new TextArea("el jem3i");
            PasswordField pwdtest = new PasswordField();
            pwdtest.setText("123456");

            test.setFullNameField(testing);
            test.setEmailField(testing);
            test.setPhoneField(testing);
            test.setDateOfBirth(TexTest);
            test.setBioField(bioTest);
            test.setCurrentPassword(pwdtest);
            test.setNewPassword(pwdtest);
            test.setConfirmPassword(pwdtest);

            MouseEvent mouseTest = new MouseEvent(null, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null);

            test.handleCloseButton(mouseTest);

            assertEquals("", test.getFullNameField().getText());
            assertEquals("", test.getEmailField().getText());
            assertEquals("", test.getPhoneField().getText());
            assertEquals(null, test.getDateOfBirth().getValue()); // default is null after clear
            assertEquals("", test.getBioField().getText());
            assertEquals("", test.getCurrentPassword().getText());
            assertEquals("", test.getNewPassword().getText());
            assertEquals("", test.getConfirmPassword().getText());
        } finally {
            testLatch.countDown();
        }
    });
    Thread.sleep(500);

    testLatch.await(); // wait for JavaFX thread to finish
    Platform.runLater(Platform::exit);
}}