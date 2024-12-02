package interface_adapter.change_password;

import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {
    private final ChangePasswordInputBoundary userChangePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case with the provided user details.
     * @param username the username of the user whose password is to be changed
     * @param bio a brief biography or description of the user
     * @param college the college the user is affiliated with
     * @param email the email address of the user
     * @param password the new password for the user account
     * @param program the academic program the user is enrolled in
     * @param year the year of study the user is in
     */
    public void execute(String username, String bio, String college, String email, String password, String program, String year) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(username, bio, college, email, password, program, year);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }
}
