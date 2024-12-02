package use_case.profilesave;

import org.bson.Document;

public class ProfileSaveInputData {
//    private final String username;
//    private String yearOfStudy;
//    private String program;
//    private String bio;
//    private String college;
    private final Document profileDoc;

    public ProfileSaveInputData(String username, String yearOfStudy, String program, String bio, String college) {
        this.profileDoc = new Document("username", username)
                .append("yearofstudy", yearOfStudy)
                .append("program", program)
                .append("bio", bio)
                .append("college", college);
    }
        public String getUsername() {
            return profileDoc.getString("username");
        }

        public void setUsername(String username) {
            if (username == null || username.isEmpty()) {
                throw new IllegalArgumentException("Username cannot be null or empty.");
            }
            profileDoc.put("username", username);
        }

        public String getYearOfStudy() {
            return profileDoc.getString("yearofstudy");
        }

        public void setYearOfStudy(String yearOfStudy) {
            profileDoc.put("yearofstudy", yearOfStudy);
        }

        public String getProgram() {
            return profileDoc.getString("program");
        }

        public void setProgram(String program) {
            profileDoc.put("program", program);
        }

        public String getBio() {
            return profileDoc.getString("bio");
        }

        public void setBio(String bio) {
            profileDoc.put("bio", bio);
        }

        public String getCollege() {
            return profileDoc.getString("college");
        }

        public void setCollege(String college) {
            profileDoc.put("college", college);
        }

        // Getter for the complete Document
        public Document getProfileDocument() {
            return profileDoc;
        }
    }