package interface_adapter.profilesave;

import use_case.profilesave.*;

public class ProfileSaveController {
    private final ProfileSaveInteractor saveInteractor;

    public ProfileSaveController(ProfileSaveInteractor saveInteractor) {
        this.saveInteractor = saveInteractor;
    }

    public void saveProfile(String username, String yearOfStudy, String program, String bio, String college) {
        ProfileSaveInputData inputData = new ProfileSaveInputData(username, yearOfStudy, program, bio, college);
//        inputData.setYearOfStudy(yearOfStudy);
//        inputData.setProgram(program);
//        inputData.setBio(bio);
//        inputData.setCollege(college);

        saveInteractor.saveProfile(inputData);
    }
}
