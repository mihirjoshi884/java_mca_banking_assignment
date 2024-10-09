package com.mikejuliet.bankingassignmentjava.frontend;

import javax.swing.*;
import java.awt.*;

public class AdminLoginPage extends JPanel {
    public AdminLoginPage(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Add more padding

        // Create the label for admin login
        JLabel adminLoginLabel = new JLabel("Admin Login");
        adminLoginLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(adminLoginLabel, gbc);

        // Username field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 24)); // Increase text field font size
        gbc.gridx = 1;
        add(usernameField, gbc);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 24)); // Increase password field font size
        gbc.gridx = 1;
        add(passwordField, gbc);

        // Submit button
        JButton submitButton = new JButton("Login");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Increase button font size
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            // Implement login logic here
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (validateAdminCredentials(username, password)) {
                JOptionPane.showMessageDialog(this, "Admin Logged In Successfully");
                // Add logic to transition to the admin dashboard
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
            }
        });

        // Back button to return to home
        JButton backButton = new JButton("Back to Home");
        backButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Increase button font size
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(backButton, gbc);

        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Home"); // Switch back to the home panel
        });
    }

    private boolean validateAdminCredentials(String username, String password) {
        // Replace with actual validation logic
        return "admin".equals(username) && "password".equals(password);
    }
}
