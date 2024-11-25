package use_case.profile;

public interface ProfileInputBoundary {
    void loadProfile(ProfileInputData inputData);

    void saveProfile(ProfileInputData inputData);
}