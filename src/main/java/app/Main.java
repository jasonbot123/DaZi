package app;

import data_access.*;

import entity.CommonUserFactory;

import service.PostService;
import service.UserService;
import data_access.MongoDBConnection;
import data_access.MongoLoginUserDataAccessObject;
import data_access.MongoUserDataAccessObject;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */

    public static void main(String[] args) {
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        MongoDBConnection connection = new MongoDBConnection();
        MongoUserDataAccessObject userDao = new MongoUserDataAccessObject(connection.getDatabase("UserDataBase"));
        UserService userService = new UserService(userDao);

        // Test post1
        MongoPostDataAccessObject postDao = new MongoPostDataAccessObject(connection.getDatabase("PostDataBase"));
        PostService postService = new PostService(postDao);

        // Initializing controllers and interactors within AppBuilder
        MongoLoginUserDataAccessObject loginUserDAO = new MongoLoginUserDataAccessObject(userDao);
        MongoLoginUserDataAccessObject signUpUserDAO = new MongoLoginUserDataAccessObject(userDao);

        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addLoggedInView()
                .addSignupUseCase(signUpUserDAO)
                .addLoginUseCase(loginUserDAO)
                .addChangePasswordUseCase()
                .addLogoutUseCase()
                .build();

        application.pack();
        application.setVisible(true);

    }
}



