package use_case.profilesave;

public interface ProfileSaveDataAccessInterface {
    ProfileSaveInputData saveProfile(String username, String yearOfStudy, String program, String bio, String college);
}
