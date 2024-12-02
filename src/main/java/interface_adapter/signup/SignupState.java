package interface_adapter.signup;

/**
 * The state for the Signup View Model.
 */
public class SignupState {
    private String username = "";
    private String usernameError;
    private String bio = "";
    private String bioError;
    private String college = "";
    private String collegeError;
    private String email = "";
    private String emailError;
    private String password = "";
    private String passwordError;
    private String program = "";
    private String programError;
    private String year = "";
    private String yearError;
    private String repeatPassword = "";
    private String repeatPasswordError;

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getBio() {return bio;}

    public String getBioError() {return bioError;}

    public void setBio(String bio) {this.bio = bio;}

    public void setBioError(String bioError) {this.bioError = bioError;}

    public String getCollege() {return college;}

    public String getCollegeError() {return collegeError;}

    public void setCollege(String college) {this.college = college;}

    public void setCollegeError(String collegeError) {this.collegeError = collegeError;}

    public String getEmail() {return this.email;}

    public String getEmailError() {return emailError;}

    public void setEmailError(String emailError) {this.emailError = emailError;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    public String getProgram() {return program;}

    public String getProgramError() {return programError;}

    public void setProgram(String program) {this.program = program;}

    public void setProgramError(String programError) {this.programError = programError;}

    public String getYear() {return year;}

    public String getYearError() {return yearError;}

    public void setYear(String year) {this.year = year;}

    public void setYearError(String yearError) {this.yearError = yearError;}

    @Override
    public String toString() {
        return "SignupState{"
                + "username='" + username + '\''
                + ", bio='" + bio + '\''
                + ", college='" + college + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + ", program='" + program + '\''
                + ", year='" + year + '\''
                + ", repeatPassword='" + repeatPassword + '\''
                + '}';
    }

}
