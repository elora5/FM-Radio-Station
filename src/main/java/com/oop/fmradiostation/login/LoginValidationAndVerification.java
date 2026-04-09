package com.oop.fmradiostation.login;

public class LoginValidationAndVerification {
    public boolean validateEmailOrId(String emailOrId) {
        if (emailOrId == null) {
            return false;
        }
        if (emailOrId.contains("@") && emailOrId.contains(".")) {
            // The input is an email
            return true;
        } else {
            // The input is an ID
            return !emailOrId.trim().isEmpty();
        }
    }

    public boolean validatePassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasSpecial   = !password.matches("[A-Za-z0-9 ]*");
        boolean hasDigit     = password.matches(".*\\d.*");

        return hasUppercase && hasLowercase && hasSpecial && hasDigit;
    }
}