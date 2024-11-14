package view.HomePageUI;

import javax.swing.*;
import java.awt.*;

public class TopRightIconsPanel extends JPanel {

    public TopRightIconsPanel() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        String[] icons = {"📩", "➕", "👤", "🔔", "🚪"}; // Placeholder icons
        for (String icon : icons) {
            JButton iconButton = new JButton(icon);
            add(iconButton);
        }
    }
}