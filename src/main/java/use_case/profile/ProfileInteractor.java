package use_case.profile;

import org.bson.Document;
import service.ProfileService;

public class ProfileInteractor implements ProfileInputBoundary {
    private final ProfileUserDataAccessInterface dataAccess;
    private final ProfileOutputBoundary presenter;
    private final ProfileService service;

    public ProfileInteractor(ProfileUserDataAccessInterface dataAccess,
                             ProfileOutputBoundary presenter,
                             ProfileService service) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
        this.service = service;
    }

    @Override
    public void loadProfile(ProfileInputData inputData) {
        ProfileInputData profileData = dataAccess.fetchProfile(inputData.getUsername());
        ProfileOutputData outputData = new ProfileOutputData(profileData);
        presenter.presentProfile(outputData);
    }

    @Override
    public void saveProfile(ProfileInputData inputData) {
        dataAccess.saveProfile(inputData);
    }
}