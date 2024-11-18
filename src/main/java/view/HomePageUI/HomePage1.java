package view.HomePageUI;

import javax.swing.*;
import java.awt.*;

public class HomePage1 extends JFrame {
    public HomePage1() {
        // Set up the main frame
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null); // Center on the screen
        setLayout(new BorderLayout());

        // Left Panel (Logo + Sidebar)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(new Color(0, 51, 102)); // Sidebar color

        // Add LogoPanel to the top
        JPanel logoPanel = new LogoPanel();
        logoPanel.setBackground(new Color(0, 51, 102));
        leftPanel.add(logoPanel, BorderLayout.NORTH);

        // Add SideBar to the center
        JPanel sideBar = new SideBar();
        leftPanel.add(sideBar, BorderLayout.CENTER);

        // Add Left Panel to the West of the main frame
        add(leftPanel, BorderLayout.WEST);

        // Top Panel (Search Bar and Icons)
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchBar = new SearchBar(); // Adjusted for alignment and size
        topPanel.add(searchBar, BorderLayout.CENTER);
        JPanel topRightIcons = new TopRightIconsPanel(); // Adjusted alignment
        topPanel.add(topRightIcons, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Center Panel (Posts)
        JPanel centerPanel = new PostsPanel(); // Redesigned to match the draft
        add(centerPanel, BorderLayout.CENTER);

        // Make frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomePage1::new);
    }
}