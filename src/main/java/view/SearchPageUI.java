package view;

import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;
import entity.Post;
import interface_adapter.search.SearchViewModel;
import use_case.search.SearchInteractor;

import view.HomePageUI.PostsPanel;
import view.HomePageUI.LogoPanel;
import view.HomePageUI.SideBar;
import view.HomePageUI.SearchBar;
import view.HomePageUI.TopRightIconsPanel;
import view.HomePageUI.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class SearchPageUI extends JFrame {

    public SearchPageUI(JFrame previousFrame,  SearchViewModel viewModel) {
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

        JPanel sideBar = new SideBar(previousFrame.getTitle());
        leftPanel.add(sideBar, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);

        // Top Panel (Search Bar remains the same)
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchBar = new SearchBar(this,
                new SearchInteractor(new MongoPostDataAccessObject
                        (MongoDBConnection.getDatabase("PostDataBase")), viewModel), viewModel);
        topPanel.add(searchBar, BorderLayout.CENTER);

        JPanel topRightIcons = new TopRightIconsPanel(this);
        topPanel.add(topRightIcons, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // post panel (display search results)
        DefaultListModel<Post> postListModel = new DefaultListModel<>();
        for (Post post : viewModel.getSearchResults()) {
            postListModel.addElement(post);
        }

        JList<Post> postList = new JList<>(postListModel);
        postList.setCellRenderer(new PostCellRenderer());
        JScrollPane scrollPane = new JScrollPane(postList);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

}