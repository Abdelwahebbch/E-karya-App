package com.ekarya.validation;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    public static boolean isValidFullName(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            return false;
        }
        String fullNamePattern = "^[A-Z][a-z]+(?: [A-Z][a-z]+)+$";
        Pattern pattern = Pattern.compile(fullNamePattern);
        Matcher matcher = pattern.matcher(fullName);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailPattern = "^[A-Za-z0-9_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        String phoneNumberPattern = "^(9[0-9]|5[0-9]|2[0-9])([0-9]{6})$";
        Pattern pattern = Pattern.compile(phoneNumberPattern);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    public static String convertSqlDateToString(Date sqlDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(sqlDate);
    }
}
