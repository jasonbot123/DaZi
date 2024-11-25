package view.HomePageUI;

import data_access.MongoCommentDataAccessObject;
import data_access.MongoDBConnection;
import entity.Comment;
import entity.Post;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CommentPage extends JFrame {
    private final Post post;
    private final MongoCommentDataAccessObject commentDAO;
    private final JPanel commentsPanel;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public CommentPage(Post post) {
        this.post = post;
        this.commentDAO = new MongoCommentDataAccessObject(MongoDBConnection.getDatabase("PostDataBase"));

        setTitle("Comments for: " + post.getTitle());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 显示原帖子内容
        JPanel postPanel = createPostPanel();
        add(postPanel, BorderLayout.NORTH);

        // 评论列表区域
        commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(commentsPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Comments"));
        add(scrollPane, BorderLayout.CENTER);

        // 添加评论的区域
        JPanel addCommentPanel = createCommentInputPanel();
        add(addCommentPanel, BorderLayout.SOUTH);

        refreshComments();
    }

    private JPanel createPostPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Original Post"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        JLabel titleLabel = new JLabel(post.getTitle());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JTextArea contentArea = new JTextArea(post.getContent());
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(contentArea), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCommentInputPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea commentInput = new JTextArea(3, 40);
        commentInput.setLineWrap(true);
        commentInput.setWrapStyleWord(true);

        JButton submitButton = new JButton("Add Comment");
        submitButton.addActionListener(e -> {
            String content = commentInput.getText().trim();
            if (!content.isEmpty()) {
                Comment newComment = new Comment(
                        post.getTitle(),
                        content,
                        "currentUser", // 这里应该使用实际的登录用户名
                        LocalDateTime.now()
                );
                commentDAO.addComment(newComment);
                commentInput.setText("");
                refreshComments();
            }
        });

        panel.add(new JScrollPane(commentInput), BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.EAST);

        return panel;
    }

    private void refreshComments() {
        commentsPanel.removeAll();
        List<Comment> comments = commentDAO.getCommentsForPost(post.getTitle());

        for (Comment comment : comments) {
            JPanel commentPanel = createCommentPanel(comment);
            commentsPanel.add(commentPanel);
            commentsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        commentsPanel.revalidate();
        commentsPanel.repaint();
    }

    private JPanel createCommentPanel(Comment comment) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        JLabel userLabel = new JLabel(comment.getUsername());
        userLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JTextArea contentArea = new JTextArea(comment.getContent());
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

        JLabel timeLabel = new JLabel(comment.getTimestamp().format(formatter));
        timeLabel.setFont(new Font("Arial", Font.ITALIC, 10));

        panel.add(userLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(contentArea), BorderLayout.CENTER);
        panel.add(timeLabel, BorderLayout.SOUTH);

        return panel;
    }
}