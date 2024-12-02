package entity;

import java.util.List;

public class Profile {
    private String yearOfStudy;
    private String program;
    private String bio;
    private String college;
    private List<String> posts;

    public Profile(String userId, String username, String email) {
    }

    // Getters
    public String getYearOfStudy() { return yearOfStudy; }
    public String getProgram() { return program; }
    public String getBio() { return bio; }
    public String getCollege() { return college; }

    // Setters
    public void setYearOfStudy(String yearOfStudy) { this.yearOfStudy = yearOfStudy; }
    public void setProgram(String program) { this.program = program; }
    public void setBio(String bio) { this.bio = bio; }
    public void setCollege(String college) { this.college = college; }
}
