package com.mikejuliet.bankingassignmentjava.frontend;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {
    public HomePage(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());

        // Create the welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Banking Application");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Increase font size for larger screens
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20); // Add more padding
        add(welcomeLabel, gbc);

        // Create the login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Increase button font size
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(loginButton, gbc);

        // Create the admin login button
        JButton adminLoginButton = new JButton("Login as Admin");
        adminLoginButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Increase button font size
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(adminLoginButton, gbc);

        // Add action listeners for buttons
        loginButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        adminLoginButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminLogin"));
    }
}
