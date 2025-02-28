package use_case.login;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface LoginInputBoundary {

    /**
     * Executes the login use case.
     * @param loginInputData the input data
     */
    void execute(LoginInputData loginInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToSignUpView();

    /**
     * Executes the switch to Homepage view use case.
     */
    void switchToMainView();
}
