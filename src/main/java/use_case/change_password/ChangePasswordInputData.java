package use_case.change_password;

/**
 * The input data for the Change Password Use Case.
 */
public class ChangePasswordInputData {

    private final String username;
    private final String bio;
    private final String college;
    private final String email;
    private final String password;
    private final String program;
    private final String year;


    public ChangePasswordInputData(String name, String bio, String college, String email, String password, String program, String year) {
        this.username = name;
        this.bio = bio;
        this.college = college;
        this.email = email;
        this.password = password;
        this.program = program;
        this.year = year;
    }

    String getUsername() {
        return username;
    }

    String getBio() {return bio;}

    String getCollege() {return college;}

    String getEmail() {return email;}

    String getPassword() {
        return password;
    }

    String getProgram() {return program;}

    String getYear() {return year;}


}
