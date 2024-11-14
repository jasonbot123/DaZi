package view.HomePageUI;

import javax.swing.*;
import java.awt.*;

public class SearchBar extends JPanel {

    public SearchBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JTextField searchField = new JTextField("Hinted search text", 30);
        JButton searchButton = new JButton("🔍"); // Placeholder icon
        add(searchField);
        add(searchButton);

        // Add a panel for icons on the right if needed
        add(new TopRightIconsPanel(), BorderLayout.EAST);
    }
}
