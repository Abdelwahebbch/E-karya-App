package com.ekarya.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.junit.jupiter.api.Test;

public class InputValidatorTest {

    @Test
    void ShouldReturnTrueWhenEmailIsValid() {
        assertTrue(InputValidator.isValidEmail("azizboujlida93@gmail.com"));
    }

    @Test
    void ShouldReturnFalseWhenEmailIsEmptyOrNull() {
        assertFalse(InputValidator.isValidEmail(""));
        assertFalse(InputValidator.isValidEmail(null));
    }

    @Test
    void ShouldReturnFalseWhenEmailLacksAtSymbol() {
        assertFalse(InputValidator.isValidEmail("azizboujlida93gmail.com"));
    }

    @Test
    void ShouldReturnFalseWhenEmailContainsWhiteSpaces() {
        assertFalse(InputValidator.isValidEmail("aziz boujli da93@  gmail.com"));
    }

    @Test
    void ShouldReturnTrueWhenFullNameIsValid() {
        assertTrue(InputValidator.isValidFullName("Foulen Ben Flen"));

    }

    @Test
    void ShouldReturnFalseWhenFullNameIsEmptyOrNull() {
        assertFalse(InputValidator.isValidFullName(""));
        assertFalse(InputValidator.isValidFullName(null));
    }

    @Test
    void ShouldReturnFalseWhenLastNameIsMessing() {
        assertFalse(InputValidator.isValidFullName("Foulen"));

    }

    @Test
    void ShouldReturnFalseWhenLastNameIsNotTitleCased() {
        assertFalse(InputValidator.isValidFullName("foulen ben flen"));

    }

    @Test
    void ShouldReturnFalseWhenPhoneNumberIsEmptyOrNull() {
        assertFalse(InputValidator.isValidPhoneNumber(""));
        assertFalse(InputValidator.isValidPhoneNumber(null));
    }

    @Test
    void ShouldReturnTrueWhenPhoneNumberIsValid() {
        assertTrue(InputValidator.isValidPhoneNumber("96000000"));
    }

    @Test
    void ShouldReturnFalseWhenPhoneNumberContainsWhiteSpaces() {
        assertFalse(InputValidator.isValidPhoneNumber("90 65 65 84 88"));
    }

    @Test
    void ShouldReturnFalseWhenPhoneNumberDiffThan8Digits() {
        assertFalse(InputValidator.isValidPhoneNumber("9036367"));
        assertFalse(InputValidator.isValidPhoneNumber("9036367777"));
    }

    @Test
    void ShouldReturnTrueWhenPasswordIsValid() {
        assertTrue(InputValidator.isValidPassword("Test1234"));
    }

    @Test
    void ShouldReturnFalseWhenPasswordIsEmptyOrNull() {
        assertFalse(InputValidator.isValidPassword(""));
        assertFalse(InputValidator.isValidPassword(null));
    }

    @Test
    void ShouldReturnFalseWhenPasswordMissingDigitUpperLowerCaseChar() {
        assertFalse(InputValidator.isValidPassword("Aaaaaaaaa"));
        assertFalse(InputValidator.isValidPassword("aaaaaaaa12"));
        assertFalse(InputValidator.isValidPassword("AAAAAA44"));
    }

    @Test
    void ShouldReturnFalseWhenPasswordTooLongOrTooShort() {
        assertFalse(InputValidator.isValidPassword("Test1234567899999"));
        assertFalse(InputValidator.isValidPassword("Test12"));
    }

    @Test
    void shouldReturnFormattedDateString_WhenGivenValidSqlDate() {
        Date date = Date.valueOf("2024-04-19");
        String result = InputValidator.convertSqlDateToString(date);
        assertEquals("19-04-2024", result);
    }

}
