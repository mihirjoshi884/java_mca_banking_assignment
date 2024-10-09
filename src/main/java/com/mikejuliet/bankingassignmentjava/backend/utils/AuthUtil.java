package com.mikejuliet.bankingassignmentjava.backend.utils;


import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class AuthUtil {

    // Method to generate a secure random salt
    public String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Method to encode a password using SHA-256 with a salt
    public String encodePassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encoding password", e);
        }
    }

    // Method to verify if the provided password matches the encoded password
    public boolean matches(String rawPassword, String encodedPassword, String salt) {
        String encodedRawPassword = encodePassword(rawPassword, salt);
        return encodedRawPassword.equals(encodedPassword);
    }
}