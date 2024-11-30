package view.SectionPageUI;

import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;

import interface_adapter.posts.PostsViewModel;
import interface_adapter.posts.SectionViewModel;
import use_case.post.PostDataAccessInterface;
import use_case.post.PostsInteractor;
import use_case.post.SectionInteractor;
import view.HomePageUI.HomePage1;
import view.HomePageUI.PostsPanel;
import view.HomePageUI.LogoPanel;
import view.HomePageUI.SideBar;
import view.HomePageUI.SearchBar;
import view.HomePageUI.TopRightIconsPanel;

import interface_adapter.search.SearchViewModel;
import use_case.search.SearchInteractor;

import javax.swing.*;
import java.awt.*;

public class GamingUI extends JFrame{
    private static GamingUI instance;
    private PostsPanel postsPanel;
    private String currentUsername;
    private final SectionInteractor interactor;
    private final SectionViewModel viewModel;

    public GamingUI(String username) {
        this.currentUsername = username;
        this.viewModel = new SectionViewModel();
        this.interactor = new SectionInteractor(
                new MongoPostDataAccessObject(MongoDBConnection.getDatabase("PostDataBase")),
                viewModel);

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
        SearchViewModel sViewModel = new SearchViewModel();
        JPanel searchBar = new SearchBar(this,
                new SearchInteractor(new MongoPostDataAccessObject(MongoDBConnection.getDatabase("PostDataBase")),sViewModel),
                sViewModel);

        topPanel.add(searchBar, BorderLayout.CENTER);

        JPanel topRightIcons = new TopRightIconsPanel(this);
        topPanel.add(topRightIcons, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel
        String sectionFilter = "GAMING";

        PostsViewModel viewModel = new PostsViewModel();

        PostsPanel postsPanel = new PostsPanel(currentUsername, sectionFilter, viewModel);

        PostsInteractor interactor = new PostsInteractor(
                new MongoPostDataAccessObject(MongoDBConnection.getDatabase("PostDataBase")),
                viewModel,
                postsPanel
        );
        postsPanel.setInteractor(interactor);

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
