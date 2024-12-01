package use_case.change_password;

/**
 * The input data for the Change Password Use Case.
 */
public class ChangePasswordInputData {

    private final String password;
    private final String username;
    private final String email;

    public ChangePasswordInputData(String password, String username, String email) {
        this.password = password;
        this.username = username;
        this.email = email;
    }

    String getPassword() {
        return password;
    }

    String getUsername() {
        return username;
    }

    String getEmail() {return email;}

}
