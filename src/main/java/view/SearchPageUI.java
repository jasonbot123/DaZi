package view;

import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;

import interface_adapter.posts.PostsViewModel;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import use_case.search.SearchInteractor;

import view.HomePageUI.PostsPanel;
import view.HomePageUI.LogoPanel;
import view.HomePageUI.SideBar;
import view.HomePageUI.SearchBar;
import view.HomePageUI.TopRightIconsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class SearchPageUI extends JFrame {

    public SearchPageUI(JFrame previousFrame, SearchViewModel viewModel) {
        setTitle("Search Results");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Left Sidebar (same as HomePage)
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(0, 51, 102));

        JPanel logoPanel = new LogoPanel();
        logoPanel.setBackground(new Color(0, 51, 102));
        leftPanel.add(logoPanel, BorderLayout.NORTH);

        String currentUsername = previousFrame.getTitle().replace("Home Page - ", "").trim();
        PostsViewModel postsViewModel = new PostsViewModel();
        PostsPanel postsPanel = new PostsPanel(currentUsername, null, postsViewModel);
        JPanel sideBar = new SideBar(currentUsername, postsPanel, postsViewModel);
        leftPanel.add(sideBar, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchBar = new SearchBar(this,
                new SearchInteractor(new MongoPostDataAccessObject
                        (MongoDBConnection.getDatabase("PostDataBase")), new SearchPresenter(viewModel)),
                viewModel);
        topPanel.add(searchBar, BorderLayout.CENTER);

        JPanel topRightIcons = new TopRightIconsPanel(this);
        topPanel.add(topRightIcons, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // SearchPostsPanel (display search results)
        SearchViewModel searchViewModel = viewModel;
        SearchPostsPanel sPostsPanel = new SearchPostsPanel(searchViewModel);
        sPostsPanel.updateSearchResults(viewModel.getSearchResults());
        add(sPostsPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}

