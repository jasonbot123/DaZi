package use_case.signup;

import entity.User;
import entity.UserFactory;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;


    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (!signupInputData.getUsername().isEmpty() && !signupInputData.getPassword().isEmpty()
                && !signupInputData.getRepeatPassword().isEmpty() && !signupInputData.getEmail().isEmpty()) {

            if (signupInputData.getEmail().contains("@mail.utoronto.ca")) {
                if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
                    userPresenter.prepareFailView("User already exists.");
                }
                else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
                    userPresenter.prepareFailView("Passwords don't match.");
                }
                else {
                    final User user = userFactory.create(signupInputData.getUsername(), signupInputData.getBio(),
                            signupInputData.getCollege(),signupInputData.getEmail(), signupInputData.getPassword(),
                            signupInputData.getProgram(), signupInputData.getYear());
                    userDataAccessObject.save(user);

                    final SignupOutputData signupOutputData = new SignupOutputData(user.getName(), false);
                    userPresenter.prepareSuccessView(signupOutputData);

                }
            }else{
                userPresenter.prepareFailView("Must have a University of Toronto email.");
            } }else{
            userPresenter.prepareFailView("Please fill out all fields.");
        }

    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
