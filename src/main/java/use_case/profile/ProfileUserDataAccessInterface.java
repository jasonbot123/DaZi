package use_case.profile;

public interface ProfileUserDataAccessInterface {
    /**
     * Fetches the profile data for the given userId.
     *
     * @param userId The ID of the user whose profile is being fetched.
     * @return ProfileInputData representing the user's profile.
     */
    ProfileInputData fetchProfile(String userId);

    /**
     * Saves or updates the profile data for the given user.
     *
     * @param profile The ProfileInputData containing updated profile details.
     */
    void saveProfile(ProfileInputData profile);
}