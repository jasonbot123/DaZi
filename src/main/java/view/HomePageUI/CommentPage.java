package view.HomePageUI;

import data_access.MongoCommentDataAccessObject;
import data_access.MongoDBConnection;
import entity.Comment;
import entity.Post;
import interface_adapter.comment.CommentPresenter;
import interface_adapter.comment.CommentViewModel;
import use_case.comment.CommentInputBoundary;
import use_case.comment.CommentInputData;
import use_case.comment.CommentInteractor;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentPage extends JFrame {
    private final Post post;
    private final CommentViewModel viewModel;
    private final CommentInputBoundary commentInteractor;
    private final JPanel commentsPanel;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public CommentPage(Post post, String currentUsername) {
        this.post = post;
        
        // 初始化Clean Architecture组件
        MongoCommentDataAccessObject commentDAO = new MongoCommentDataAccessObject(
            MongoDBConnection.getDatabase("PostDataBase")
        );
        this.viewModel = new CommentViewModel();
        CommentPresenter presenter = new CommentPresenter(viewModel);
        this.commentInteractor = new CommentInteractor(commentDAO, presenter);

        setTitle("Comments for: " + post.getTitle());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 添加原始帖子面板
        JPanel postPanel = createPostPanel();
        add(postPanel, BorderLayout.NORTH);

        // 评论列表面板
        commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(commentsPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Comments"));
        add(scrollPane, BorderLayout.CENTER);

        // 添加评论输入面板
        JPanel addCommentPanel = createCommentInputPanel(currentUsername);
        add(addCommentPanel, BorderLayout.SOUTH);

        // 设置视图模型监听器
        viewModel.addPropertyChangeListener(this::onViewModelChanged);

        // 加载评论
        commentInteractor.loadComments(post.getId());
    }

    private void onViewModelChanged(PropertyChangeEvent evt) {
        if ("comments".equals(evt.getPropertyName())) {
            refreshComments();
        } else if ("error".equals(evt.getPropertyName())) {
            String error = viewModel.getError();
            if (error != null) {
                JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private JPanel createPostPanel() {
        // 原有的createPostPanel代码保持不变
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

    private JPanel createCommentInputPanel(String currentUsername) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextArea commentInput = new JTextArea(3, 40);
        commentInput.setLineWrap(true);
        commentInput.setWrapStyleWord(true);

        JButton submitButton = new JButton("Add Comment");
        submitButton.addActionListener(e -> {
            String content = commentInput.getText().trim();
            if (!content.isEmpty()) {
                CommentInputData inputData = new CommentInputData(
                    post.getId(),
                    content,
                    currentUsername
                );
                commentInteractor.addComment(inputData);
                commentInput.setText("");
            }
        });

        panel.add(new JScrollPane(commentInput), BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.EAST);

        return panel;
    }

    private void refreshComments() {
        commentsPanel.removeAll();
        for (Comment comment : viewModel.getComments()) {
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