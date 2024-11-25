package view.ProfilePageUI;

import interface_adapter.profile.ProfileViewModel;

import javax.swing.*;
import java.awt.*;

public class ProfilePage extends JFrame {
    private final ProfileViewModel viewModel;

    public ProfilePage(ProfileViewModel viewModel) {
        this.viewModel = viewModel;

        setTitle("Profile Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Top Panel: Edit Profile
        EditProfilePanel editProfilePanel = new EditProfilePanel(viewModel);
        add(editProfilePanel, BorderLayout.NORTH);

//        // Center Panel: User Posts
//        UserPostsPanel userPostsPanel = new UserPostsPanel(viewModel);
//        add(userPostsPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}