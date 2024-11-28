package view.HomePageUI;

import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;
import entity.Post;
import use_case.search.SearchInteractor;
import view.SearchPageUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class SearchBar extends JPanel {
    private JTextField searchField;
    private JButton searchButton;

    public SearchBar(JFrame parentFrame, MongoPostDataAccessObject postDAO) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        searchField = new JTextField("Enter your search keyword...");
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 16));

        searchButton = new JButton("🔍");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            if (!keyword.isEmpty()) {
                // Perform the search
                List<Post> searchResults = postDAO.searchPostsByTitle(keyword);
                if (!searchResults.isEmpty()) {
                    // Navigate to SearchPageUI
                    new SearchPageUI(parentFrame, searchResults);
                    parentFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "No posts found for keyword: " + keyword,
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please enter a keyword!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(searchField, BorderLayout.CENTER);
        add(searchButton, BorderLayout.EAST);
    }
}