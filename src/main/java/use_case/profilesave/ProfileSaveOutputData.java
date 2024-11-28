package use_case.profilesave;

import org.bson.Document;

public class ProfileSaveOutputData {
    private String username;
    private String yearOfStudy;
    private String program;
    private String bio;
    private String college;

    // Constructor: Populate instance variables from the Document
    public ProfileSaveOutputData(Document document) {
        this.username = document.getString("username");
        this.yearOfStudy = document.getString("yearofstudy");
        this.program = document.getString("program");
        this.bio = document.getString("bio");
        this.college = document.getString("college");
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public String getProgram() {
        return program;
    }

    public String getBio() {
        return bio;
    }

    public String getCollege() {
        return college;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}