package service;

import data_access.MongoUserDataAccessObject;
import org.bson.Document;

public class UserService {
    private MongoUserDataAccessObject userDao;

    public UserService(MongoUserDataAccessObject userDao) {
        this.userDao = userDao;
    }

    public boolean signUp(String username, String bio, String college, String email, String password,
                          String program, String year) {
        if (userDao.userExists(username)) {
            System.out.println("User already exists!");
            return false;
        }

        userDao.addUser(username, bio, college, email, password, program, year);
        System.out.println("User created successfully!");
        return true;
    }

    public boolean login(String username, String password) {
        Document user = userDao.getUser(username);

        if (user == null) {
            System.out.println("User not found!");
            return false;
        }

        String storedPassword = user.getString("password");
        return storedPassword.equals(password);
    }


    public boolean deleteUser(String username) {
        boolean deleted = userDao.deleteUser(username);
        if (deleted) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found or deletion failed.");
        }
        return deleted;
    }

    // update the user's password by username
    public boolean updateUserPassword(String username, String newPassword) {
        boolean updated = userDao.updateUserPassword(username, newPassword);
        if (updated) {
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("User not found or update failed.");
        }
        return updated;
    }
}