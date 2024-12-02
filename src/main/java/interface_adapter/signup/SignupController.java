package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the Signup Use Case.
 */
public class SignupController {

    private final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case with the provided user details.
     * @param username the desired username for the new account
     * @param bio a brief biography or description of the user
     * @param college the college the user is affiliated with
     * @param email the email address for the new account
     * @param password1 the primary password for the new account
     * @param program the academic program the user is enrolled in
     * @param year the year of study the user is in
     * @param password2 the confirmation of the password (must match password1)
     */
    public void execute(String username, String bio, String college, String email, String password1, String program,
                        String year, String password2) {
        final SignupInputData signupInputData = new SignupInputData(
                username, bio, college, email, password1, program, year, password2);

        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        userSignupUseCaseInteractor.switchToLoginView();
    }
}
