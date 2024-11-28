package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import use_case.profileview.ProfileViewDataAccessInterface;
import use_case.profileview.ProfileViewInputData;

import static com.mongodb.client.model.Filters.eq;

public class MongoUserProfileViewDataAccessObject implements ProfileViewDataAccessInterface {
    private final MongoCollection<Document> profilesCollection;

    public MongoUserProfileViewDataAccessObject(MongoDatabase database) {
        this.profilesCollection = database.getCollection("users");
    }

    @Override
    public ProfileViewInputData fetchProfile(String username) {
        // Fetch the profile document from MongoDB
        Document profileDoc = profilesCollection.find(eq("username", username)).first();
        if (profileDoc == null) {
            return null; // Or throw an exception if profile not found
        }
        // Convert Document to ProfileInputData
        return documentToProfileInputData(profileDoc);
    }

//    @Override
//    public void saveProfile(ProfileSaveInputData profile) {
//        // Convert ProfileInputData to Document
//        Document profileDoc = profileInputDataToDocument(profile);
//
//        // Update the profile in MongoDB or insert if not existing
//        profilesCollection.replaceOne(eq("username", profile.getUsername()), profileDoc, new com.mongodb.client.model.ReplaceOptions().upsert(true));
//    }

    private ProfileViewInputData documentToProfileInputData(Document doc) {
        ProfileViewInputData profile = new ProfileViewInputData(doc.getString("username"));
        profile.setEmail(doc.getString("email"));
        profile.setYearOfStudy(doc.getString("yearofstudy"));
        profile.setProgram(doc.getString("program"));
        profile.setBio(doc.getString("bio"));
        profile.setCollege(doc.getString("college"));
//        profile.setPosts(doc.getList("posts", String.class));
        return profile;
    }

    private Document profileInputDataToDocument(ProfileViewInputData profile) {
        Document doc = new Document("username", profile.getUsername())
                .append("email", profile.getEmail())
                .append("yearofstudy", profile.getYearOfStudy())
                .append("program", profile.getProgram())
                .append("bio", profile.getBio())
                .append("college", profile.getCollege());
//                .append("posts", profile.getPosts() == null ? new ArrayList<>() : profile.getPosts());
        return doc;
    }
}