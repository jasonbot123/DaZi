package view.HomePageUI;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SideBar extends JPanel {

    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelMoving;

    public SideBar() {
        setLayout(new BorderLayout());

        // Logo panel at the top
        LogoPanel logoPanel = new LogoPanel();
        add(logoPanel, BorderLayout.NORTH);

        // Container panel for the menu, titles, and sections
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setOpaque(false);  // Allow gradient background to show

        // "What's on your mind?" centered label
        JLabel whatsOnYourMindLabel = new JLabel("What's on your mind?");
        whatsOnYourMindLabel.setForeground(Color.WHITE);
        whatsOnYourMindLabel.setFont(new Font("Arial", Font.BOLD, 14));
        whatsOnYourMindLabel.setAlignmentX(CENTER_ALIGNMENT);  // Center alignment
        containerPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Top spacing
        containerPanel.add(whatsOnYourMindLabel);
        containerPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Spacing after label

        // Menu panel for section buttons
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setOpaque(false);

        // Define menu items and emojis
        String[] menuItems = {
                "Latest Post", "Your Post", "Studying", "Gaming", "Dining", "Hanging Out", "Others"
        };
        String[] emojis = {
                "📜", "💬", "📚", "🎮", "🍽️", "🤝", "🌀"
        };

        // Create buttons for "Latest Post" and "Your Post" with extra spacing
        for (int i = 0; i < 2; i++) {
            JButton menuButton = createMenuButton(emojis[i] + " " + menuItems[i]);
            menuPanel.add(menuButton);
            menuPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Vertical space after each button
        }

        // "Sections" centered label below "Your Post" and above "Studying"
        JLabel sectionsLabel = new JLabel("Sections");
        sectionsLabel.setForeground(Color.WHITE);
        sectionsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        sectionsLabel.setAlignmentX(CENTER_ALIGNMENT);  // Center alignment
        menuPanel.add(sectionsLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Spacing after "Sections" title

        // Create buttons for remaining sections
        for (int i = 2; i < menuItems.length; i++) {
            JButton menuButton = createMenuButton(emojis[i] + " " + menuItems[i]);
            menuPanel.add(menuButton);
            menuPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Vertical space after each button
        }

        containerPanel.add(menuPanel);
        add(containerPanel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color color1 = new Color(0, 102, 204);
        Color color2 = new Color(0, 51, 153);
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);

        // Set button styles
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 12)); // Font settings
        button.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left

        // Adjust padding to reduce left margin
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10)); // Less left padding, more compact

        return button;
    }
}
