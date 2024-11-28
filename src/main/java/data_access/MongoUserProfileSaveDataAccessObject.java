package data_access;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import use_case.profilesave.*;
import static com.mongodb.client.model.Filters.eq;

public class MongoUserProfileSaveDataAccessObject implements ProfileSaveDataAccessInterface {
    private final MongoCollection<Document> profilesCollection;

    public MongoUserProfileSaveDataAccessObject(MongoDatabase database) {
        this.profilesCollection = database.getCollection("users");
    }

    @Override
    public void saveProfile(ProfileSaveInputData profile) {
        Document profileDoc = profile.getProfileDocument();
        // Update the profile in MongoDB
        profilesCollection.updateOne(eq("username", profileDoc.getString("username")), new Document("$set", profileDoc));
    }
}



