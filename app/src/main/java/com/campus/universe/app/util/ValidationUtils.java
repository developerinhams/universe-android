package com.campus.universe.app.util;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Input validation utility functions
 */
public class ValidationUtils {

    /**
     * Validate email address
     */
    public static boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        Pattern pattern = Pattern.compile(Constants.EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }

    /**
     * Validate password strength
     * Must contain: uppercase, lowercase, digit, special char, min 8 chars
     */
    public static boolean isValidPassword(String password) {
        if (TextUtils.isEmpty(password) || password.length() < 8) {
            return false;
        }
        Pattern pattern = Pattern.compile(Constants.PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }

    /**
     * Check if passwords match
     */
    public static boolean doPasswordsMatch(String password1, String password2) {
        return !TextUtils.isEmpty(password1) && password1.equals(password2);
    }

    /**
     * Validate full name
     */
    public static boolean isValidFullName(String fullName) {
        return !TextUtils.isEmpty(fullName) && fullName.trim().length() >= 2;
    }

    /**
     * Validate phone number (basic)
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            return false;
        }
        String phonePattern = "^[\\d+\\-\\s()]+$";
        return phoneNumber.matches(phonePattern) && phoneNumber.length() >= 10;
    }

    /**
     * Validate URL
     */
    public static boolean isValidUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        try {
            new java.net.URL(url);
            return true;
        } catch (java.net.MalformedURLException e) {
            return false;
        }
    }

    /**
     * Check if string is empty or null
     */
    public static boolean isEmpty(String text) {
        return TextUtils.isEmpty(text) || text.trim().length() == 0;
    }

    /**
     * Check if string length is within range
     */
    public static boolean isValidLength(String text, int min, int max) {
        if (isEmpty(text)) {
            return false;
        }
        int length = text.length();
        return length >= min && length <= max;
    }

    /**
     * Validate username (alphanumeric and underscore only)
     */
    public static boolean isValidUsername(String username) {
        if (isEmpty(username) || username.length() < 3) {
            return false;
        }
        return username.matches("^[a-zA-Z0-9_]+$");
    }

    /**
     * Validate bio length
     */
    public static boolean isValidBio(String bio) {
        return bio == null || bio.length() <= 500;
    }

    /**
     * Sanitize input to prevent injection
     */
    public static String sanitizeInput(String input) {
        if (TextUtils.isEmpty(input)) {
            return "";
        }
        return input.trim()
                .replaceAll("[<>\"']", "")
                .replaceAll("\\s+", " ");
    }
}
