package view.ProfilePageUI;

import data_access.MongoDBConnection;
import data_access.MongoUserProfileViewDataAccessObject;
import interface_adapter.profileview.ProfileViewController;
import interface_adapter.profileview.ProfileViewPresenter;
import interface_adapter.profileview.ProfileViewModel;
import service.ProfileService;
import use_case.profileview.ProfileViewInteractor;
import use_case.profileview.ProfileViewDataAccessInterface;
import javax.swing.*;

public class MockProfileViewApp {
    public static void main(String[] args) {
        // Step 1: Connect to MongoDB

        MongoDBConnection connection = new MongoDBConnection();

        // Step 2: Instantiate Dependencies
        ProfileViewDataAccessInterface dataAccess = new MongoUserProfileViewDataAccessObject(connection.getDatabase("UserDataBase"));
        ProfileViewModel viewModel = new ProfileViewModel();
        ProfileViewPresenter presenter = new ProfileViewPresenter(viewModel);
        ProfileService service = new ProfileService(); // Optional: For validation
        ProfileViewInteractor interactor = new ProfileViewInteractor(dataAccess, presenter, service);
        ProfileViewController controller = new ProfileViewController(interactor);

        // Step 3: Simulate User Clicking Profile Icon
        String currentUserId = "Jason"; // This ID must match the `userId` in MongoDB
        controller.loadProfile(currentUserId);

        // Step 4: Launch the Profile Page with Retrieved Data
        SwingUtilities.invokeLater(() -> new view.ProfilePageUI.ProfilePage(viewModel));
    }
}