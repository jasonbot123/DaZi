package view.ProfilePageUI;

import interface_adapter.profileview.ProfileViewModel;

import javax.swing.*;
import java.awt.*;

public class ProfilePage extends JFrame {
    public ProfilePage(ProfileViewModel viewModel) {
        setTitle("Profile Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Profile Information Panel
        JPanel infoPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Profile Information"));

        // Display non-editable fields
        infoPanel.add(new JLabel("Username:"));
        infoPanel.add(new JLabel(viewModel.getUsername()));

        infoPanel.add(new JLabel("Email:"));
        infoPanel.add(new JLabel(viewModel.getEmail()));

        infoPanel.add(new JLabel("Year of Study:"));
        infoPanel.add(new JLabel(viewModel.getYearOfStudy()));

        infoPanel.add(new JLabel("Program:"));
        infoPanel.add(new JLabel(viewModel.getProgram()));

        infoPanel.add(new JLabel("College:"));
        infoPanel.add(new JLabel(viewModel.getCollege()));

        infoPanel.add(new JLabel("Bio:"));
        JTextArea bioArea = new JTextArea(viewModel.getBio());
        bioArea.setEditable(false);
        bioArea.setWrapStyleWord(true);
        bioArea.setLineWrap(true);
        bioArea.setOpaque(false);
        bioArea.setFocusable(false);
        bioArea.setBorder(null);
        infoPanel.add(new JScrollPane(bioArea));



        add(infoPanel, BorderLayout.NORTH);

        // User Posts Panel
        UserPostsPanel userPostsPanel = new UserPostsPanel(viewModel);
        add(userPostsPanel, BorderLayout.CENTER);

        // Edit Profile Button
        //if currentuser == viewModel.getUsername()
        JButton editButton = new JButton("Edit Profile");
        editButton.addActionListener(e -> {
//            new EditProfilePage(viewModel); // Navigate to Edit Profile Page
            dispose(); // Close the current Profile Page
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}