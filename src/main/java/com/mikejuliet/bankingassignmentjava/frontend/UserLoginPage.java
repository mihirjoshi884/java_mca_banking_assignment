package com.mikejuliet.bankingassignmentjava.frontend;

import javax.swing.*;
import java.awt.*;

public class UserLoginPage extends JPanel {
    public UserLoginPage(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Add more padding

        // Create the label for user login
        JLabel loginLabel = new JLabel("User Login");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Increase font size for larger screens
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(loginLabel, gbc);

        // Username field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(userLabel, gbc);

        JTextField userField = new JTextField(15);
        userField.setFont(new Font("Arial", Font.PLAIN, 24)); // Increase text field font size
        gbc.gridx = 1;
        add(userField, gbc);

        // Password field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(15);
        passField.setFont(new Font("Arial", Font.PLAIN, 24)); // Increase password field font size
        gbc.gridx = 1;
        add(passField, gbc);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Increase button font size
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        // Add action listener for submit button
        submitButton.addActionListener(e -> {
            // Add login functionality here
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if (validateUserCredentials(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Submitted");
                cardLayout.show(mainPanel, "Home"); // Go back to home after submitting
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
            }
        });
    }

    private boolean validateUserCredentials(String username, String password) {
        // Replace with actual validation logic
        return "user".equals(username) && "password".equals(password);
    }
}
