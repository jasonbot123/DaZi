package view.HomePageUI;

import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;
import entity.Post;
import view.SearchPageUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchBar extends JPanel {
    private JFrame parentFrame;

    public SearchBar(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JTextField searchField = new JTextField("Hinted search text", 30);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JButton searchButton = new JButton("🔍");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            System.out.println("Searching keyword: " + keyword); // Debugging

            if (!keyword.isEmpty()) {
                // Search posts by title
                MongoPostDataAccessObject postDAO = new MongoPostDataAccessObject(MongoDBConnection.getDatabase("posts"));
                List<Post> searchResults = postDAO.searchPostsByTitle(keyword);

                if (!searchResults.isEmpty()) {
                    // Transition to SearchPageUI with the results
                    JFrame newFrame = new SearchPageUI(keyword, searchResults, "currentUsername"); // Replace with actual username
                    newFrame.setVisible(true);

                    if (parentFrame != null) {
                        parentFrame.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No posts found matching the keyword.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Please enter a keyword!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(searchField, BorderLayout.CENTER);
        add(searchButton, BorderLayout.EAST);
    }
}