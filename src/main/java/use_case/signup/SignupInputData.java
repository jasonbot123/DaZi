package use_case.signup;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {

    private final String username;
    private final String bio;
    private final String college;
    private final String email;
    private final String password;
    private final String program;
    private final String year;
    private final String repeatPassword;

    public SignupInputData(String username, String bio, String college, String email, String password, String program, String year, String repeatPassword) {
        this.username = username;
        this.bio = bio;
        this.college = college;
        this.email = email;
        this.password = password;
        this.program = program;
        this.year = year;
        this.repeatPassword = repeatPassword;}

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

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
