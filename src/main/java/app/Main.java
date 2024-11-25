package app;

import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;
import data_access.MongoUserDataAccessObject;
import service.PostService;
import service.UserService;

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
        MongoDBConnection connection = new MongoDBConnection();
        MongoUserDataAccessObject userDao = new MongoUserDataAccessObject(connection.getDatabase("UserDataBase"));
        UserService userService = new UserService(userDao);

        // Test post1
        MongoPostDataAccessObject postDao = new MongoPostDataAccessObject(connection.getDatabase("PostDataBase"));
        PostService postService = new PostService(postDao);

        String title = "My First Post";
        String content = "test post 123";
        String sectionName = "GAMING";
        String username = "celine";

        postService.addPost(title, content, sectionName, username);

        postService.getPostByTitle(title);

        String newContent = "updated content of my first post.";
        boolean updateSuccess = postService.updatePostContent(title, newContent);
        System.out.println("Content Update Success: " + updateSuccess);



        /*
        // test1
        userService.signUp("celine123456", "test@email.com", "pass123");

        // test2
        String username = "celine";
        String email = "celine@exampleemail.com";
        String password = "celine123";
        String newPassword = "newPassword123";

        // sign up celine
        boolean signupSuccess = userService.signUp(username, email, password);
        System.out.println("Signup Success: " + signupSuccess);

        // check if user exists
        boolean userExists = userDao.userExists(username);
        System.out.println("User Exists: " + userExists);

        // try to log in with the correct password
        boolean loginSuccess = userService.login(username, password);
        System.out.println("Login Success: " + loginSuccess);

        // try to update the user's password
        boolean updatePasswordSuccess = userService.updateUserPassword(username, newPassword);
        System.out.println("Password Update Success: " + updatePasswordSuccess);

        // try to log in with the new password
        boolean loginNewPasswordSuccess = userService.login(username, newPassword);
        System.out.println("Login with New Password Success: " + loginNewPasswordSuccess);

        // delete the user
        boolean deleteSuccess = userService.deleteUser(username);
        System.out.println("User Deletion Success: " + deleteSuccess);

        // verify user no longer exists
        boolean userExistsAfterDeletion = userDao.userExists(username);
        System.out.println("User Exists After Deletion: " + userExistsAfterDeletion);

         */
    }
}

    /*
    public static void main(String[] args) {

        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addChangePasswordUseCase()
                                            .addLogoutUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }

     */

