package view.HomePageUI;

import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;
import data_access.MongoLikeRecordDataAccessObject;
import entity.Post;
import entity.LikeRecord;
import view.HomePageUI.CommentPage;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PostsPanel extends JPanel {
    private JPanel postsContainer;
    private MongoPostDataAccessObject postDAO; // Data Access Object for fetching posts
    private MongoLikeRecordDataAccessObject likeRecordDAO;
    private String currentUsername; // 当前登录用户的用户名

    public PostsPanel(String username) {
        this.currentUsername = username;
        setLayout(new BorderLayout());

        postDAO = new MongoPostDataAccessObject(MongoDBConnection.getDatabase("posts"));
        likeRecordDAO = new MongoLikeRecordDataAccessObject(MongoDBConnection.getDatabase("posts"));

        postsContainer = new JPanel();
        postsContainer.setLayout(new BoxLayout(postsContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(postsContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        loadPostsFromDatabase();

        add(scrollPane, BorderLayout.CENTER);
    }

    // load+get posts from MongoDB and display them
    private void loadPostsFromDatabase() {
        postsContainer.removeAll(); // Clear existing posts

        List<Post> posts = postDAO.getAllPosts();
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

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(userLabel, BorderLayout.WEST);

        // 创建一个面板来放置评论按钮和点赞按钮
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // 点赞按钮
        JToggleButton likeButton = new JToggleButton("❤ " + post.getLikes());
        // 检查用户是否已经点赞过这个帖子
        boolean hasLiked = likeRecordDAO.hasUserLiked(currentUsername, post.getTitle());
        likeButton.setSelected(hasLiked);

        likeButton.addActionListener(e -> {
            if (likeButton.isSelected()) {
                // 检查是否已经点赞过
                if (!likeRecordDAO.hasUserLiked(currentUsername, post.getTitle())) {
                    post.setLikes(post.getLikes() + 1);
                    likeRecordDAO.addLikeRecord(new LikeRecord(currentUsername, post.getTitle()));
                    postDAO.updateLikes(post.getTitle(), post.getLikes());
                }
            } else {
                // 取消点赞
                if (likeRecordDAO.hasUserLiked(currentUsername, post.getTitle())) {
                    post.setLikes(Math.max(0, post.getLikes() - 1));
                    likeRecordDAO.removeLikeRecord(currentUsername, post.getTitle());
                    postDAO.updateLikes(post.getTitle(), post.getLikes());
                }
            }
            likeButton.setText("❤ " + post.getLikes());
        });
        buttonsPanel.add(likeButton);

        // 评论按钮
        JButton commentButton = new JButton("Comments");
        commentButton.addActionListener(e -> {
            CommentPage commentPage = new CommentPage(post);
            commentPage.setVisible(true);
        });
        buttonsPanel.add(commentButton);

        bottomPanel.add(buttonsPanel, BorderLayout.EAST);
        postPanel.add(bottomPanel, BorderLayout.SOUTH);

        postsContainer.add(postPanel);
        postsContainer.add(Box.createRigidArea(new Dimension(0, 10)));
    }
}