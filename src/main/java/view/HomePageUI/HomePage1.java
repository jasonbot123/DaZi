package view.HomePageUI;

import com.mongodb.client.MongoDatabase;
import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;
import interface_adapter.posts.PostsViewModel;
import interface_adapter.search.SearchViewModel;
import use_case.post.PostsInteractor;
import use_case.search.SearchInteractor;

import javax.swing.*;
import java.awt.*;

public class HomePage1 extends JFrame {
    private String currentUsername;
    private final SearchInteractor searchInteractor;
    private final SearchViewModel searchViewModel;
    private final PostsViewModel postsViewModel;
    // private final PostsPanel postsPanel;

    public HomePage1(String username) {
        this.currentUsername = username;
        this.searchViewModel = new SearchViewModel();
        this.postsViewModel = new PostsViewModel();
        this.searchInteractor = new SearchInteractor(
                new MongoPostDataAccessObject(MongoDBConnection.getDatabase("PostDataBase")),
                searchViewModel);

        setTitle("Home Page - " + username);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // left panel, colouring
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(0, 51, 102));

        // logo
        JPanel logoPanel = new LogoPanel();
        logoPanel.setBackground(new Color(0, 51, 102));
        leftPanel.add(logoPanel, BorderLayout.NORTH);

        // posts panel setup
        PostsViewModel viewModel = new PostsViewModel();
        PostsPanel postsPanel = new PostsPanel(username, null, viewModel);
        viewModel.clearPosts();
        PostsInteractor interactor = new PostsInteractor(
                new MongoPostDataAccessObject(MongoDBConnection.getDatabase("PostDataBase")),
                viewModel,
                postsPanel
        );
        postsPanel.setInteractor(interactor);

        // sidebar
        JPanel sideBar = new SideBar(currentUsername, postsPanel, viewModel);
        leftPanel.add(sideBar, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);

        // searchInteractor, searchBar
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchBar = new SearchBar(this, searchInteractor, searchViewModel);
        topPanel.add(searchBar, BorderLayout.CENTER);

        // upper right, icon buttons
        JPanel topRightIcons = new TopRightIconsPanel(this);
        topPanel.add(topRightIcons, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // posts
        add(postsPanel, BorderLayout.CENTER);
        interactor.getThePosts(10);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            MongoDatabase database = MongoDBConnection.getDatabase("PostDataBase");
            MongoPostDataAccessObject dao = new MongoPostDataAccessObject(database);
            dao.createIndexes();

            new HomePage1("testUser");
        });
    }

    public Object getCurrentUsername() {
        return currentUsername.toString();
    }
}

