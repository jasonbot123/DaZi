package use_case.profilesave;

import org.bson.Document;
import use_case.profileview.ProfileViewOutputData;

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
//        try {
//            dataAccess.saveProfile(inputData);
//            ProfileSaveOutputData outputData = new ProfileSaveOutputData(inputData.getProfileDocument());
//            presenter.presentProfile(outputData);
//        } catch (Exception e) {
//            ProfileSaveOutputData outputData = new ProfileSaveOutputData();
//            outputData.setSuccess(false);
//            presenter.saveResult(outputData);
//        }
    }
}