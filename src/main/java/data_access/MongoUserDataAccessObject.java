package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;

public class MongoUserDataAccessObject {

    private final MongoCollection<Document> userCollection;

    public MongoUserDataAccessObject() {
        MongoDatabase database = MongoDBConnection.getDatabase("UserDatabase"); // Replace with your database name
        userCollection = database.getCollection("users"); // Replace with your collection name
    }

    // Add User
    public void addUser(String username, String password) {
        Document user = new Document("username", username)
                .append("password", password);
        userCollection.insertOne(user);
    }

    // Find User by Username
    public Document getUser(String username) {
        return userCollection.find(eq("username", username)).first();
    }

    // Update User Password
    public void updateUserPassword(String username, String newPassword) {
        Bson filter = eq("username", username);
        Bson updateOperation = new Document("$set", new Document("password", newPassword));
        userCollection.updateOne(filter, updateOperation);
    }

    // Delete User
    public void deleteUser(String username) {
        userCollection.deleteOne(eq("username", username));
    }

    // Check if User Exists
    public boolean userExists(String username) {
        return getUser(username) != null;
    }
}

