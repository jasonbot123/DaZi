package use_case.profilesave;

public class ProfileSaveInputData {
    private final String username;
    private String yearOfStudy;
    private String program;
    private String bio;
    private String college;

    public ProfileSaveInputData(String username, String yearOfStudy, String program, String bio, String college) {
        this.username = username;
        this.yearOfStudy = yearOfStudy;
        this.program = program;
        this.bio = bio;
        this.college = college;
    }

    public String getUsername() { return username; }
    public String getYearOfStudy() { return yearOfStudy; }
    public void setYearOfStudy(String yearOfStudy) { this.yearOfStudy = yearOfStudy; }
    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getCollege() { return college; }
    public void setCollege(String college) { this.college = college; }
}