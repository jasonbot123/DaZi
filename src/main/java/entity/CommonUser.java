package entity;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String bio;
    private final String college;
    private final String email;
    private final String password;
    private final String program;
    private final String year;

    public CommonUser(String name, String bio, String college, String email, String password
    , String program, String year) {
        this.name = name;
        this.bio = bio;
        this.college = college;
        this.email = email;
        this.password = password;
        this.program = program;
        this.year = year;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getBio() {return bio;}

    public String getCollege() {return college;}

    @Override
    public String getEmail() {return email;}

    @Override
    public String getPassword() {
        return password;
    }

    public String getProgram() {return program;}

    public String getYear() {return year;}


}
