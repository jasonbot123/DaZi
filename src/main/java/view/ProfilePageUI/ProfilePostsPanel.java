package view.ProfilePageUI;

import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;
import entity.Post;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProfilePostsPanel extends JPanel {
    private JPanel postsContainer;
    private MongoPostDataAccessObject postDAO; // Data Access Object for fetching posts

    public ProfilePostsPanel(String username) {
        setLayout(new BorderLayout());

        postDAO = new MongoPostDataAccessObject(MongoDBConnection.getDatabase("posts"));

        postsContainer = new JPanel();
        postsContainer.setLayout(new BoxLayout(postsContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(postsContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        loadPostsFromDatabase(username);

        add(scrollPane, BorderLayout.CENTER);
    }

    // load+get posts from MongoDB and display them
    private void loadPostsFromDatabase(String username) {
        postsContainer.removeAll(); // Clear existing posts

        List<Post> posts = postDAO.getAllPostsByUsername(username);
        for (Post post : posts) {
            addPostToUI(post); // Add each post to the UI
        }

        postsContainer.revalidate();
        postsContainer.repaint();
    }

    // Add new post to the UI
    public void addPost(Post post) {
        // Save the post to the database
        postDAO.addPost(post);

        // Add the post to the UI
        addPostToUI(post);

        postsContainer.revalidate();
        postsContainer.repaint();
    }

    // helper method for addPost, create and add a post UI component
    private void addPostToUI(Post post) {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BorderLayout());
        postPanel.setBorder(BorderFactory.createTitledBorder(post.getSection().toString()));

        JLabel titleLabel = new JLabel(post.getTitle());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        JLabel contentLabel = new JLabel("<html>" + post.getContent() + "</html>");
        JLabel userLabel = new JLabel("By: " + post.getUsername());

        postPanel.add(titleLabel, BorderLayout.NORTH);
        postPanel.add(contentLabel, BorderLayout.CENTER);
        postPanel.add(userLabel, BorderLayout.SOUTH);

        postsContainer.add(postPanel);
        postsContainer.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer between posts
    }
}