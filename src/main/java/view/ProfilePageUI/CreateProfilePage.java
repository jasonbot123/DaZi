package view.ProfilePageUI;

import data_access.MongoDBConnection;
import data_access.MongoUserProfileViewDataAccessObject;
import interface_adapter.profileview.*;
import service.ProfileService;
import use_case.profileview.ProfileViewInteractor;

import com.mongodb.client.MongoDatabase;


import javax.swing.*;

public class CreateProfilePage {
    private final ProfileViewController controller;
    private final ProfileViewModel model;

    public CreateProfilePage() {
        MongoDatabase database = MongoDBConnection.getDatabase("UserDataBase");
        MongoUserProfileViewDataAccessObject viewDAO = new MongoUserProfileViewDataAccessObject(database);
        this.model = new ProfileViewModel();
        ProfileViewPresenter profileViewPresenter = new ProfileViewPresenter(model);
        ProfileService profileService = new ProfileService();
        ProfileViewInteractor viewInteractor = new ProfileViewInteractor(viewDAO, profileViewPresenter, profileService);
        this.controller =  new ProfileViewController(viewInteractor);
    }

    public void launchSelfProfilePage(String username) {
        //Call loadProfile with username
        controller.loadProfile(username);

        //Launch SelfProfilePage UI
        SwingUtilities.invokeLater(() -> new SelfProfilePage(model, username));
    }

    public void launchOthersProfilePage(String username) {
        //Call loadProfile with username
        controller.loadProfile(username);

        //Launch OthersProfilePage UI
        SwingUtilities.invokeLater(() -> new OthersProfilePage(model, username));
    }
}
