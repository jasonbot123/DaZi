package interface_adapter.change_password;

/**
 * The State information representing the logged-in user.
 */
public class LoggedInState {
    private String username = "";
    private String bio = "";
    private String college = "";
    private String email = "";
    private String password = "";
    private String program = "";
    private String year = "";
    private String passwordError;

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        bio = copy.bio;
        college = copy.college;
        email = copy.email;
        password = copy.password;
        program = copy.program;
        year = copy.year;
        passwordError = copy.passwordError;
    }

    public LoggedInState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {return bio;}

    public void setBio(String bio) {this.bio = bio;}

    public String getCollege() {return college;}

    public void setCollege(String college) {this.college = college;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {
        return password;
    }

    public String getProgram() {return program;}

    public void setProgram(String program) {this.program = program;}

    public String getYear() {return year;}

    public void setYear(String year) {this.year = year;}

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }


}
