package interface_adapter.profileview;

import use_case.profileview.*;

public class ProfileViewController {
    private final ProfileViewInputBoundary viewinteractor;

    public ProfileViewController(ProfileViewInputBoundary viewinteractor) {
        this.viewinteractor = viewinteractor;
    }

    public void loadProfile(String username) {
        ProfileViewInputData inputData = new ProfileViewInputData(username);
        viewinteractor.loadProfile(inputData);
    }

//    public void saveProfile(String username, String yearOfStudy, String program, String bio, String college) {
//        ProfileSaveInputData inputData = new ProfileSaveInputData(username,yearOfStudy,program,bio,college);
//        saveinteractor.saveProfile(inputData);
//    }
}