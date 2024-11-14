package view.HomePageUI;

import javax.swing.*;
import java.awt.*;

public class LogoPanel extends JPanel {

    public LogoPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 150)); // Define a max size for the logo panel

        // Load and scale the logo image
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image logoImage = logoIcon.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH);  // Resize logo
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage), SwingConstants.CENTER);

        add(logoLabel, BorderLayout.CENTER);
    }
}