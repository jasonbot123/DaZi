package view.HomePageUI;

import entity.Post;
import view.ProfilePageUI.CreateProfilePage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PostCellRenderer extends JPanel implements ListCellRenderer<Post> {
    private final JLabel titleLabel;
    private final JLabel contentLabel;
    private final JLabel userLabel;
    private final JLabel likeLabel;
    private final JLabel messageLabel;
    private final JLabel commentLabel;

    public PostCellRenderer() {
        setLayout(new BorderLayout(5, 5));

        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border innerBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        titleLabel = new JLabel();
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));

        contentLabel = new JLabel();
        contentLabel.setVerticalAlignment(JLabel.TOP);
        contentLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        userLabel = new JLabel();
        userLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        userLabel.setForeground(new Color(100, 100, 100));

        likeLabel = new JLabel();
        likeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        likeLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 25, 8, 25)
        ));
        likeLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        likeLabel.setOpaque(true);
        likeLabel.setBackground(new Color(245, 245, 245));
        likeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        messageLabel = new JLabel("Message");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 25, 8, 25)
        ));
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        messageLabel.setOpaque(true);
        messageLabel.setBackground(new Color(245, 245, 245));
        messageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        commentLabel = new JLabel("Comments");
        commentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        commentLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 25, 8, 25)
        ));
        commentLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        commentLabel.setOpaque(true);
        commentLabel.setBackground(new Color(245, 245, 245));
        commentLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(likeLabel);
        buttonsPanel.add(messageLabel);
        buttonsPanel.add(commentLabel);

        bottomPanel.setOpaque(false);
        bottomPanel.add(userLabel, BorderLayout.WEST);
        bottomPanel.add(buttonsPanel, BorderLayout.EAST);

        JPanel contentPanel = new JPanel(new BorderLayout(5, 10));
        contentPanel.setOpaque(false);
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(contentLabel, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends Post> list, Post post, int index, boolean isSelected, boolean cellHasFocus
    ) {
        titleLabel.setText(post.getTitle());
        contentLabel.setText("<html><body style='width: 300px'>" +
                post.getTruncatedContent(200) + "</html>");
        userLabel.setText("Posted by: " + post.getUsername());

        likeLabel.setText("❤ " + post.getLikes());
        likeLabel.setForeground(new Color(128, 128, 128));
        
        if (isSelected) {
            setBackground(new Color(240, 240, 240));
            likeLabel.setBackground(new Color(245, 245, 245));
            messageLabel.setBackground(new Color(245, 245, 245));
            commentLabel.setBackground(new Color(245, 245, 245));
        } else {
            setBackground(Color.WHITE);
            likeLabel.setBackground(new Color(250, 250, 250));
            messageLabel.setBackground(new Color(250, 250, 250));
            commentLabel.setBackground(Color.WHITE);
        }

        return this;
    }
}