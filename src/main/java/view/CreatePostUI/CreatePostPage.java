package view.CreatePostUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mongodb.client.MongoDatabase;
import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;
import entity.Post;
import entity.Section;
import view.HomePageUI.HomePage1;

public class CreatePostPage extends JFrame {

    public CreatePostPage() {
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
        String[] sections = {"Studying", "Gaming", "Dining", "Hanging Out", "Others"};
        JComboBox<String> sectionComboBox = new JComboBox<>(sections);
        formPanel.add(sectionLabel);
        formPanel.add(sectionComboBox);

        add(formPanel, BorderLayout.CENTER);

        // Post Button
        JButton postButton = new JButton("Post");
        postButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String content = contentArea.getText().trim();
            String sectionString = (String) sectionComboBox.getSelectedItem(); // Get selected section as String

            if (!title.isEmpty() && !content.isEmpty() && sectionString != null) {
                try {
                    Section section = Section.valueOf(sectionString.toUpperCase());

                    // create the Post
                    Post newPost = new Post(title, content, section, "currentUsername"); //TODO: chnage to actual username

                    // save to MongoDB
                    MongoDatabase database = MongoDBConnection.getDatabase("PostDataBase");
                    MongoPostDataAccessObject dao = new MongoPostDataAccessObject(database);
                    dao.addPost(newPost);

                    // redirect back to HomePage1
                    dispose();
                    SwingUtilities.invokeLater(HomePage1::new);

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid section selected!", "Error", JOptionPane.ERROR_MESSAGE);
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