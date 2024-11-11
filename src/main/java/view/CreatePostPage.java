package view;

import javax.swing.*;
import java.awt.*;
import use_case.post.CreateAPost;
import use_case.post.ManagePost;
import entity.Section;


public class CreatePostPage extends JFrame {

    // TODO: make them all final
    private CreateAPost createAPost;
    private JTextField titleField;
    private JTextArea contentArea;
    private HomePage homePage;
    private JComboBox<Section> sectionComboBox;

    public CreatePostPage(HomePage homePage, ManagePost managePost) {
        this.homePage = homePage;
        this.createAPost = new CreateAPost(managePost);
        fullWindowUI();
    }

    private void fullWindowUI(){
        setTitle("Create a New Post!");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.8);  // Set width to 80% of screen width
        int height = (int) (screenSize.height * 0.8); // Set height to 80% of screen height
        setSize(width, height);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        add(createAPostPanel(), BorderLayout.CENTER);
        add(createPostButtonPanel(), BorderLayout.SOUTH);

    }

    private JPanel createAPostPanel(){
        JPanel postPanel = new JPanel(new BorderLayout(10, 10));

        // title
        this.titleField = new JTextField("Enter your title", 5);
        postPanel.add(new JLabel("Title:"), BorderLayout.NORTH);
        postPanel.add(this.titleField, BorderLayout.CENTER);

        // post content
        this.contentArea = new JTextArea("Write your content here...", 10, 30);
        this.contentArea.setLineWrap(true);
        this.contentArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(this.contentArea);
        postPanel.add(new JLabel("Content:"), BorderLayout.WEST);
        postPanel.add(scrollPane, BorderLayout.SOUTH);

        // section
        sectionComboBox = new JComboBox<>(Section.values());
        postPanel.add(new JLabel("Section:"), BorderLayout.EAST);
        postPanel.add(sectionComboBox, BorderLayout.AFTER_LINE_ENDS);

        return postPanel;
    }

    private JPanel createPostButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Create buttons
        JButton cancelButton = new JButton("Cancel");
        JButton draftButton = new JButton("Save Draft");
        JButton postButton = new JButton("Post");


        // TODO: add action listener for save draft
        cancelButton.addActionListener(e -> dispose()); // Close window on cancel

        postButton.addActionListener(e -> {
            String title = titleField.getText();
            String content = contentArea.getText();
            Section section = (Section) sectionComboBox.getSelectedItem();
            String username = "Guest"; // TODO: placeholder, change after username is implemented

            String result = this.createAPost.execute(title, content, section, username);

            if (result == null) {
                homePage.refreshPosts();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, result, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add buttons to panel
        buttonPanel.add(cancelButton);
        buttonPanel.add(draftButton);
        buttonPanel.add(postButton);

        return buttonPanel;
    }

}
