package use_case.profileview;

public class ProfileViewOutputData {
    private String username;
    private String email;
    private String yearOfStudy;
    private String program;
    private String bio;
    private String college;
//    private List<String> posts;

    // Constructor
    public ProfileViewOutputData(ProfileViewInputData doc) {
        this.username = doc.getUsername();
        this.email = doc.getEmail();
        this.yearOfStudy = doc.getYearOfStudy();
        this.program = doc.getProgram();
        this.bio = doc.getBio();
        this.college = doc.getCollege();
    }

    // Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}