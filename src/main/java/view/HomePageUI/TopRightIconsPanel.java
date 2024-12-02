package view.HomePageUI;


import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;
import interface_adapter.login.LoginState;
import interface_adapter.posts.PostsViewModel;
import use_case.post.PostsInteractor;
import view.CreatePostUI.CreatePostPage;
import view.SectionPageUI.*;
import view.ChatPageUI.ChatPage;
import view.ChatPageUI.ChatWindow;

import view.LoginView;
import interface_adapter.login.LoginViewModel;



import javax.swing.*;
import java.awt.*;

public class TopRightIconsPanel extends JPanel {

    LoginView loginView;

    public TopRightIconsPanel(JFrame parentFrame, String currentuser) {
        setLayout(new FlowLayout(FlowLayout.RIGHT));


        String[] icons = {"📩", "➕", "👤", "🔔", "🚪"};
        for (String icon : icons) {
            JButton iconButton = new JButton(icon);
            add(iconButton);

            // ActionListener for "+" button
            if ( "➕".equals(icon)) {
                iconButton.addActionListener(e -> {
                    String sectionFilter = resolveSectionFilter(parentFrame);
                    if (sectionFilter != null) {
                        PostsViewModel viewModel = new PostsViewModel();
                        PostsPanel postsPanel = new PostsPanel(currentuser, sectionFilter, viewModel);
                        PostsInteractor interactor = new PostsInteractor(
                                new MongoPostDataAccessObject(MongoDBConnection.getDatabase("PostDataBase")),
                                viewModel,
                                postsPanel);


                        // pass them along with sectionFilter to CreatePostPage
                        new CreatePostPage(parentFrame, interactor, viewModel, sectionFilter, currentuser);
                    } else {
                        JOptionPane.showMessageDialog(
                                parentFrame,
                                "Unable to determine section filter.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                });
            }
            if ("📩".equals(icon)) {
                iconButton.addActionListener(e -> {
                    String sectionFilter = resolveSectionFilter(parentFrame);
                    if (sectionFilter != null) {
                        ChatPage chatpage = new ChatPage(parentFrame, currentuser);
                    } else {
                        JOptionPane.showMessageDialog(
                                parentFrame,
                                "Unable to determine section filter.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                });
            }

            //Logout Action listener
            if ("🚪".equals(icon)){
                iconButton.addActionListener(e -> {
                    // Dispose of the current frame
                    parentFrame.setVisible(false);
                    parentFrame.dispose();

                    // Create a new JFrame to hold the LoginView
                    SwingUtilities.invokeLater(() -> {
                        JFrame loginFrame = new JFrame("Login");
                        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        // Create a new LoginView and set it as the content pane
                        LoginViewModel loginViewModel = new LoginViewModel(); // Create a new LoginViewModel
                        LoginView loginView = new LoginView(loginViewModel);
                        loginFrame.setContentPane(loginView);
                        loginViewModel.setState(new LoginState()); // Reset state if needed

                        // Configure and display the login frame
                        loginFrame.pack();
                        loginFrame.setLocationRelativeTo(null); // Center on screen
                        loginFrame.setVisible(true);
                    });
                });
            }


            }

        }

    /**
     * Resolves the section filter from the parent frame.
     * After creating a post, return to where the user hit the plus button
     */
    private String resolveSectionFilter(JFrame parentFrame) {
        if (parentFrame instanceof HomePage1) {
            return "Latest Post";
        } else if (parentFrame instanceof StudyingUI) {
            return "Studying";
        } else if (parentFrame instanceof GamingUI) {
            return "Gaming";
        } else if (parentFrame instanceof DiningUI) {
            return "Dining";
        } else if (parentFrame instanceof HangingOutUI) {
            return "Hanging_Out";
        } else if (parentFrame instanceof OthersUI) {
            return "Others";
        }
        return null;
    }
}