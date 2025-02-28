package use_case.profilesave;

public class ProfileSaveInteractor implements ProfileSaveInputBoundary {
    private final ProfileSaveDataAccessInterface dataAccess;
    private final ProfileSaveOutputBoundary presenter;

    public ProfileSaveInteractor(ProfileSaveDataAccessInterface dataAccess, ProfileSaveOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    public void saveProfile(ProfileSaveInputData inputData) {
        dataAccess.saveProfile(inputData);
        ProfileSaveOutputData outputData = new ProfileSaveOutputData(inputData.getProfileDocument());
        presenter.presentProfile(outputData);
    }
}







