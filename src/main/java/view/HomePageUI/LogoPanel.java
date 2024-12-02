package view.HomePageUI;

import javax.swing.*;
import java.awt.*;

public class LogoPanel extends JPanel {
    public LogoPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the logo
        ImageIcon originalLogo = new ImageIcon(getClass().getResource("/images/logo.png")); // TODO: replace with the right logo
        Image scaledLogo = originalLogo.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH); // Adjust dimensions
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        add(logoLabel);
        setPreferredSize(new Dimension(200, 120));
    }
}