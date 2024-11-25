package view.HomePageUI;

import com.mongodb.client.MongoDatabase;
import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;
import entity.Post;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HomePage1 extends JFrame {
    private PostsPanel postsPanel;
    private String currentUsername;

    public HomePage1(String username) {
        this.currentUsername = username;
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

        JPanel sideBar = new SideBar();
        leftPanel.add(sideBar, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);

        // search bar
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchBar = new SearchBar();
        topPanel.add(searchBar, BorderLayout.CENTER);

        // upper right, icon buttons
        JPanel topRightIcons = new TopRightIconsPanel(this);
        topPanel.add(topRightIcons, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // posts
        postsPanel = new PostsPanel(currentUsername);
        add(postsPanel, BorderLayout.CENTER);
        loadPostsFromDatabase();

        setVisible(true);
    }

    // to load posts from MongoDB
    private void loadPostsFromDatabase() {
        try {
            MongoDatabase database = MongoDBConnection.getDatabase("PostDataBase");
            MongoPostDataAccessObject dao = new MongoPostDataAccessObject(database);
            List<Post> posts = dao.getAllPosts();

            for (Post post : posts) {
                postsPanel.addPost(post);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load posts from the database.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    public PostsPanel getPostsPanel() {
        return postsPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage1("testUser"));
    }
}