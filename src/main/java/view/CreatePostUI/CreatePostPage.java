package view.CreatePostUI;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

import com.mongodb.client.MongoDatabase;
import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;
import entity.Post;
import entity.Section;
import view.HomePageUI.HomePage1;
import view.SectionPageUI.*;

public class CreatePostPage extends JFrame {

    private JFrame parentFrame; // Generalized parent frame

    public CreatePostPage(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setTitle("Create a Post");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));

        // Title
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        formPanel.add(titleLabel);
        formPanel.add(titleField);

        // Content
        JLabel contentLabel = new JLabel("Content:");
        JTextArea contentArea = new JTextArea(5, 20);
        formPanel.add(contentLabel);
        formPanel.add(new JScrollPane(contentArea));

        // Section
        JLabel sectionLabel = new JLabel("Section:");
        String[] sections = {"Studying", "Gaming", "Dining", "Hanging_Out", "Others"};
        JComboBox<String> sectionComboBox = new JComboBox<>(sections);
        formPanel.add(sectionLabel);
        formPanel.add(sectionComboBox);

        add(formPanel, BorderLayout.CENTER);

        // Post button + action listener
        JButton postButton = new JButton("Post");
        postButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String content = contentArea.getText().trim();
            String sectionString = (String) sectionComboBox.getSelectedItem();

            if (!title.isEmpty() && !content.isEmpty() && sectionString != null) {
                try {
                    Section section = Section.valueOf(sectionString.toUpperCase());

                    Post newPost = new Post(title, content, section, "currentUsername", LocalDateTime.now()); //TODO: set to the correct username

                    // Save to MongoDB
                    MongoDatabase database = MongoDBConnection.getDatabase("PostDataBase");
                    MongoPostDataAccessObject dao = new MongoPostDataAccessObject(database);
                    dao.addPost(newPost);

                    if (parentFrame instanceof HomePage1 homePage) {
                        homePage.getPostsPanel().addPost(newPost);
                    }
                    switch (section) {
                        case STUDYING -> StudyingUI.getInstance("currentUsername").getPostsPanel().addPost(newPost);
                        case GAMING -> GamingUI.getInstance("currentUsername").getPostsPanel().addPost(newPost);
                        case DINING -> DiningUI.getInstance("currentUsername").getPostsPanel().addPost(newPost);
                        case HANGING_OUT -> HangingOutUI.getInstance("currentUsername").getPostsPanel().addPost(newPost);
                        case OTHERS -> OthersUI.getInstance("currentUsername").getPostsPanel().addPost(newPost);
                    }

                    dispose();

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid section.", "Error", JOptionPane.ERROR_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Failed to save the post.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(postButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}