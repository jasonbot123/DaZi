package view.HomePageUI;

import javax.swing.*;
import java.awt.*;

public class PostsPanel extends JPanel {

    public PostsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Sample posts - you could load these dynamically
        JPanel postPanel = null;
        for (int i = 0; i < 4; i++) {
            postPanel = new JPanel();
            postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
            postPanel.setBorder(BorderFactory.createTitledBorder("Section " + (5 + i)));

            JLabel postTitle = new JLabel("Post Title " + (i + 1));
            JLabel postContent = new JLabel("This is a snippet of the post content...");
            postPanel.add(postTitle);
            postPanel.add(postContent);

            add(postPanel);
            add(Box.createRigidArea(new Dimension(0, 10))); // Spacer between posts
        }

        // Make this panel scrollable
        JScrollPane scrollPane = new JScrollPane(postPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        add(scrollPane);
    }
}