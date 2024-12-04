package use_case.profileview;

import data_access.MongoUserProfileViewDataAccessObject;
import service.ProfileService;

public class ProfileViewInteractor implements ProfileViewInputBoundary {
    private final ProfileViewDataAccessInterface dataAccess;
    private final ProfileViewOutputBoundary presenter;
    private final ProfileService service;

    public ProfileViewInteractor(ProfileViewDataAccessInterface dataAccess,
                                 ProfileViewOutputBoundary presenter,
                                 ProfileService service) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
        this.service = service;
    }

    @Override
    public void loadProfile(ProfileViewInputData inputData) {
        ProfileViewInputData profileData = dataAccess.fetchProfile(inputData.getUsername());
        ProfileViewOutputData outputData = new ProfileViewOutputData(profileData);
        presenter.presentProfile(outputData);
    }
}

