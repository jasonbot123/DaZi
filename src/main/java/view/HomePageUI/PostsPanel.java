package view.HomePageUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PostsPanel extends JPanel {

    public PostsPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Create a container for posts
        JPanel postsContainer = new JPanel();
        postsContainer.setLayout(new BoxLayout(postsContainer, BoxLayout.Y_AXIS));
        postsContainer.setBackground(Color.WHITE);

        // Sample posts
        List<String[]> samplePosts = List.of(
                new String[]{"Studying", "3 mins ago", "user123", "Looking for XXXXX!", "Hi, my name is ...", null},
                new String[]{"Gaming", "59 mins ago", "apexpred321", "Anyone interested in XXXXX?", "Yooo, I am...", null},
                new String[]{"Gaming", "7 hours ago", "imnewhere", "Waiting for a XXXXX", "We are ...", null},
                new String[]{"Hanging Out", "11 hours ago", "newuser321", "Found a new place in XXX", "I just saw ...", "/path/to/image.jpg"}
        );

        for (String[] post : samplePosts) {
            postsContainer.add(createPostPanel(post));
            postsContainer.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer between posts
        }

        // Wrap posts container in a scroll pane
        JScrollPane scrollPane = new JScrollPane(postsContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove border for cleaner look

        add(scrollPane, BorderLayout.CENTER); // Add scroll pane to the center of this panel
    }

    private JPanel createPostPanel(String[] postDetails) {
        JPanel postPanel = new JPanel(new BorderLayout());
        postPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        postPanel.setBackground(Color.WHITE);
        postPanel.setPreferredSize(new Dimension(800, 100));

        // Section and Timestamp
        JLabel sectionLabel = new JLabel(postDetails[0] + " | " + postDetails[1]);
        sectionLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        sectionLabel.setForeground(Color.GRAY);

        // User and Post Content
        JLabel userLabel = new JLabel(postDetails[2]);
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        JLabel titleLabel = new JLabel(postDetails[3]);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JLabel contentLabel = new JLabel(postDetails[4]);
        contentLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // Image (if available)
        JLabel imageLabel = new JLabel();
        if (postDetails[5] != null) {
            ImageIcon image = new ImageIcon(postDetails[5]); // Replace with actual path
            Image scaledImage = image.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        }

        // Organize components
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(sectionLabel);
        contentPanel.add(userLabel);
        contentPanel.add(titleLabel);
        contentPanel.add(contentLabel);

        postPanel.add(contentPanel, BorderLayout.CENTER);
        postPanel.add(imageLabel, BorderLayout.EAST);

        return postPanel;
    }
}