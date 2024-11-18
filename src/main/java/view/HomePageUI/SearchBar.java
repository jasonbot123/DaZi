package view.HomePageUI;

import javax.swing.*;
import java.awt.*;

public class SearchBar extends JPanel {

    public SearchBar() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JTextField searchField = new JTextField("Hinted search text", 30);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 16)); // TODO: change the font size and style

        JButton searchButton = new JButton("🔍");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 18));

        add(searchField, BorderLayout.CENTER);
        add(searchButton, BorderLayout.EAST);
    }
}