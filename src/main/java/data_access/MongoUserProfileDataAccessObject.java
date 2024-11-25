package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import use_case.profile.ProfileUserDataAccessInterface;
import use_case.profile.ProfileInputData;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MongoUserProfileDataAccessObject implements ProfileUserDataAccessInterface {
    private final MongoCollection<Document> profilesCollection;

    public MongoUserProfileDataAccessObject(MongoDatabase database) {
        this.profilesCollection = database.getCollection("profiles");
    }

    @Override
    public ProfileInputData fetchProfile(String username) {
        // Fetch the profile document from MongoDB
        Document profileDoc = profilesCollection.find(eq("username", username)).first();
        if (profileDoc == null) {
            return null; // Or throw an exception if profile not found
        }
        // Convert Document to ProfileInputData
        return documentToProfileInputData(profileDoc);
    }

    @Override
    public void saveProfile(ProfileInputData profile) {
        // Convert ProfileInputData to Document
        Document profileDoc = profileInputDataToDocument(profile);

        // Update the profile in MongoDB or insert if not existing
        profilesCollection.replaceOne(eq("username", profile.getUsername()), profileDoc, new com.mongodb.client.model.ReplaceOptions().upsert(true));
    }

    private ProfileInputData documentToProfileInputData(Document doc) {
        ProfileInputData profile = new ProfileInputData(doc.getString("username"));
        profile.setEmail(doc.getString("email"));
        profile.setYearOfStudy(doc.getString("yearOfStudy"));
        profile.setProgram(doc.getString("program"));
        profile.setBio(doc.getString("bio"));
        profile.setCollege(doc.getString("college"));
//        profile.setPosts(doc.getList("posts", String.class));
        return profile;
    }

    private Document profileInputDataToDocument(ProfileInputData profile) {
        Document doc = new Document("username", profile.getUsername())
                .append("email", profile.getEmail())
                .append("yearOfStudy", profile.getYearOfStudy())
                .append("program", profile.getProgram())
                .append("bio", profile.getBio())
                .append("college", profile.getCollege());
//                .append("posts", profile.getPosts() == null ? new ArrayList<>() : profile.getPosts());
        return doc;
    }
}