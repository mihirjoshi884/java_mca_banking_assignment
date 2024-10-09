package com.mikejuliet.bankingassignmentjava;

import com.mikejuliet.bankingassignmentjava.frontend.AdminLoginPage;
import com.mikejuliet.bankingassignmentjava.frontend.HomePage;
import com.mikejuliet.bankingassignmentjava.frontend.UserLoginPage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class BankingassignmentjavaApplication {

    private static CardLayout cardLayout;
    private static JPanel mainPanel;

    public static void main(String[] args) {

        new Thread(() -> {
            SpringApplication.run(BankingassignmentjavaApplication.class, args);
        }).start();

        // Start the Swing GUI
        javax.swing.SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }





    @Bean
    public CommandLineRunner run() {
        return args -> {
            SwingUtilities.invokeLater(() -> {
                createAndShowGUI();
            });
        };
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Banking Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize); // Set frame size to full screen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window

        // Set the CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create the home panel
        HomePage homePage = new HomePage(cardLayout, mainPanel);
        mainPanel.add(homePage, "Home");

        // Create the user login panel
        UserLoginPage userLoginPage = new UserLoginPage(cardLayout, mainPanel);
        mainPanel.add(userLoginPage, "Login");

        // Create the admin login panel
        AdminLoginPage adminLoginPage = new AdminLoginPage(cardLayout, mainPanel);
        mainPanel.add(adminLoginPage, "AdminLogin");

        // Add the main panel to the frame
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
