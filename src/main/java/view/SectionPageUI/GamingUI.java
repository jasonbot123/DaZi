package view.SectionPageUI;

import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;
import view.HomePageUI.HomePage1;
import view.HomePageUI.PostsPanel;
import view.HomePageUI.LogoPanel;
import view.HomePageUI.SideBar;
import view.HomePageUI.SearchBar;
import view.HomePageUI.TopRightIconsPanel;

import javax.swing.*;
import java.awt.*;

public class GamingUI extends JFrame{
    private static GamingUI instance;
    private PostsPanel postsPanel;
    private String currentUsername;

    public GamingUI(String username) {
        this.currentUsername = username;

        setTitle("Gaming Section - " + username);
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
        JPanel searchBar = new SearchBar(this,
                new MongoPostDataAccessObject(MongoDBConnection.getDatabase("PostDataBase")));
        topPanel.add(searchBar, BorderLayout.CENTER);

        JPanel topRightIcons = new TopRightIconsPanel(this);
        topPanel.add(topRightIcons, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel
        postsPanel = new PostsPanel(currentUsername, "GAMING");
        //postsPanel.loadMorePosts();
        add(postsPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static GamingUI getInstance(String username) {
        if (instance == null) {
            instance = new GamingUI(username);
        }
        return instance;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GamingUI("testUser"));
    }

    public PostsPanel getPostsPanel() {
        return postsPanel;
    }
}
