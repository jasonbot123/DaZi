package view;

import entity.Post;
import view.HomePageUI.PostsPanel;

import view.HomePageUI.PostsPanel;
import view.HomePageUI.LogoPanel;
import view.HomePageUI.SideBar;
import view.HomePageUI.SearchBar;
import view.HomePageUI.TopRightIconsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchPageUI extends JFrame {
    private PostsPanel postsPanel;

    public SearchPageUI(String keyword, List<Post> searchResults, String currentUsername) {
        setTitle("Search Results - " + currentUsername);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Left Panel (Logo + Sidebar)
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(0, 51, 102));

        JPanel logoPanel = new LogoPanel();
        logoPanel.setBackground(new Color(0, 51, 102));
        leftPanel.add(logoPanel, BorderLayout.NORTH);

        JPanel sideBar = new SideBar(currentUsername);
        leftPanel.add(sideBar, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);

        // Top Panel (Search Bar and Icons)
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchBar = new SearchBar(this);
        topPanel.add(searchBar, BorderLayout.CENTER);

        JPanel topRightIcons = new TopRightIconsPanel(this);
        topPanel.add(topRightIcons, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel (Posts Panel)
        PostsPanel postsPanel = new PostsPanel(currentUsername, null); // No section filter
        postsPanel.updatePosts(searchResults); // Populate the panel with search results
        add(postsPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public PostsPanel getPostsPanel() {
        return postsPanel;
    }
}