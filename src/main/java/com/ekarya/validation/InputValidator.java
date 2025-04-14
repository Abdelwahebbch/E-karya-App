package com.ekarya.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator 
{
    public static boolean isValidFullName(String fullName)
    {
        String fullNamePattern="^(9[0-9]|5[0-9]|2[0-9])([0-9]{6})$";
        Pattern pattern = Pattern.compile(fullNamePattern);
        Matcher matcher = pattern.matcher(fullName);
        if (matcher.matches()) 
            return true;
        else 
            return false;
    }
    public static boolean isValidEmail(String email)
    {
        String emailPattern="^[A-Za-z0-9_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) 
            return true;
        else 
            return false;
    }
    public static boolean isValidPhoneNumber(String phoneNumber)
    {
        String phoneNumberPattern="^(9[0-9]|5[0-9]|2[0-9])([0-9]{6})$";
        Pattern pattern = Pattern.compile(phoneNumberPattern);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) 
            return true;
        else 
            return false;
    }
    public static boolean isValidPassword(String password)
    {
        String passwordPattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&])[A-Za-z\\\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) 
            return true;
        else 
            return false;
    }
    
}
