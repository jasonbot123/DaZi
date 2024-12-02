package use_case.login;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import view.HomePageUI.HomePage1;
import view.LoginView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;


/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;
    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final User user = userDataAccessObject.get(loginInputData.getUsername());

                userDataAccessObject.setCurrentUsername(user.getName());
                final LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
                //loginPresenter.prepareSuccessView(loginOutputData);
                loginPresenter.switchToMainView();
            }

        }
    }

    @Override
    public void switchToSignUpView() {
        loginPresenter.switchToSignUpView();

    }

    @Override
    public void switchToMainView() {
        loginPresenter.switchToMainView();
    }
}
