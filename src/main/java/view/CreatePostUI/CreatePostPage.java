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

public class CreatePostPage extends JFrame {

    private HomePage1 homePage1;

    public CreatePostPage(HomePage1 homePage1) {
        this.homePage1 = homePage1;

        setTitle("Create a Post");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); //TODO: change the size
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
        String[] sections = {"Studying", "Gaming", "Dining", "Hanging Out", "Others"};
        JComboBox<String> sectionComboBox = new JComboBox<>(sections);
        formPanel.add(sectionLabel);
        formPanel.add(sectionComboBox);

        add(formPanel, BorderLayout.CENTER);

        // TODO: action listener for all buttons: delete, save as draft

        // post button + action listener
        JButton postButton = new JButton("Post");
        postButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String content = contentArea.getText().trim();
            String sectionString = (String) sectionComboBox.getSelectedItem();

            if (!title.isEmpty() && !content.isEmpty() && sectionString != null) {
                try {
                    Section section = Section.valueOf(sectionString.toUpperCase());

                    // create the Post
                    Post newPost = new Post(title, content, section, "currentUsername", LocalDateTime.now());

                    // save to MongoDB
                    MongoDatabase database = MongoDBConnection.getDatabase("PostDataBase");
                    MongoPostDataAccessObject dao = new MongoPostDataAccessObject(database);
                    dao.addPost(newPost);

                    // add the new post to HomePage1 and redirect to homepage
                    homePage1.getPostsPanel().addPost(newPost);
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