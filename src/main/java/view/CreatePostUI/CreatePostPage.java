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
import use_case.post.PostsInteractor;
import interface_adapter.posts.PostsViewModel;

public class CreatePostPage extends JFrame {

    private final PostsInteractor postsInteractor;
    private final PostsViewModel postsViewModel;
    private final String sectionFilter;

    public CreatePostPage(JFrame parentFrame, PostsInteractor postsInteractor,
                          PostsViewModel postsViewModel, String sectionFilter, String username) {
        this.postsInteractor = postsInteractor;
        this.postsViewModel = postsViewModel;
        this.sectionFilter = sectionFilter;

        setTitle("Create a Post");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));

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

        // preselect the section based on the sectionFilter
        if (sectionFilter != null) {
            sectionComboBox.setSelectedItem(formatSectionName(sectionFilter));
        }
        formPanel.add(sectionLabel);
        formPanel.add(sectionComboBox);

        add(formPanel, BorderLayout.CENTER);

        // Post Button + Action Listener
        JButton postButton = new JButton("Post");
        postButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String content = contentArea.getText().trim();
            String selectedSection = (String) sectionComboBox.getSelectedItem();

            if (!title.isEmpty() && !content.isEmpty() && selectedSection != null) {
                try {
                    // use the user-selected section or the default sectionFilter (where user opens the creat post page)
                    Section section = Section.valueOf(selectedSection.toUpperCase().replace(" ", "_"));
                    Post newPost = new Post(title,
                            content,
                            section,
                            username,
                            LocalDateTime.now()); // TODO: Replace with real username

                    postsInteractor.createPost(newPost);

                    // check success or error messages in the ViewModel
                    if (postsViewModel.getErrorMessage() != null) {
                        JOptionPane.showMessageDialog(this,
                                postsViewModel.getErrorMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Post created successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        if (parentFrame != null) {
                            parentFrame.setVisible(true);
                        }
                    }

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Invalid section: " + selectedSection,
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all fields!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(postButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    private String formatSectionName(String sectionFilter) {
        switch (sectionFilter.toUpperCase()) {
            case "STUDYING":
                return "Studying";
            case "GAMING":
                return "Gaming";
            case "DINING":
                return "Dining";
            case "HANGING_OUT":
                return "Hanging Out";
            case "OTHERS":
                return "Others";
            default:
                return "Others";
        }
    }
}