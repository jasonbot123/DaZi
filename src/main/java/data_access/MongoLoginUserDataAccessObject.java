package data_access;

import entity.CommonUser;
import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

public class MongoLoginUserDataAccessObject implements LoginUserDataAccessInterface, SignupUserDataAccessInterface {

    private final MongoUserDataAccessObject userDataAccessObject;
    private String currentUsername = null;

    public MongoLoginUserDataAccessObject(MongoUserDataAccessObject userDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public boolean existsByName(String username) {
        return userDataAccessObject.userExists(username);
    }

    @Override
    public void save(User user) {
        userDataAccessObject.addUser(user.getName(), user.getEmail(), user.getPassword());
    }

    @Override
    public User get(String username) {
        var userDocument = userDataAccessObject.getUser(username);
        if (userDocument != null) {
            String name = userDocument.getString("username");
            String email = userDocument.getString("email");
            String password = userDocument.getString("password");
            return new CommonUser(name, email, password);
        }
        return null;
    }

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }

    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }
}