package view.ProfilePageUI;

import data_access.MongoDBConnection;
import data_access.MongoUserProfileViewDataAccessObject;
import service.ProfileService;
import use_case.profileview.*;
import use_case.profileview.ProfileViewInteractor;
import data_access.MongoUserProfileSaveDataAccessObject;
import interface_adapter.profilesave.*;
import interface_adapter.profileview.*;
import use_case.profilesave.*;

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
            MongoDBConnection connection = new MongoDBConnection();
            ProfileSaveDataAccessInterface dataAccess = new MongoUserProfileSaveDataAccessObject(connection.getDatabase("UserDataBase"));
            ProfileSavePresenter presenter = new ProfileSavePresenter(viewModel);
            ProfileService service = new ProfileService(); // Optional: For validation
            ProfileSaveInteractor interactor = new ProfileSaveInteractor(dataAccess, presenter);
            ProfileSaveController controller = new ProfileSaveController(interactor);
            new EditProfilePage(viewModel, controller); // Navigate to Edit Profile Page
            dispose(); // Close the current Profile Page
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}