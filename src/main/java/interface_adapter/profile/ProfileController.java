package interface_adapter.profile;

import use_case.profile.*;

public class ProfileController {
    private final ProfileInputBoundary interactor;

    public ProfileController(ProfileInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void loadProfile(String userId) {
        ProfileInputData inputData = new ProfileInputData(userId);
        interactor.loadProfile(inputData);
    }

    public void saveProfile(ProfileInputData inputData) {
        interactor.saveProfile(inputData);
    }
}