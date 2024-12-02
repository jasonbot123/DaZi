package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import static com.mongodb.client.model.Updates.set;

import static com.mongodb.client.model.Filters.eq;

public class MongoUserDataAccessObject {
    private MongoCollection<Document> userCollection;

    public MongoUserDataAccessObject(MongoDatabase database) {
        // Ensure the collection name is correctly set to "users"
        this.userCollection = database.getCollection("users");
    }

    // add a user document with username, email, and password
    public void addUser(String username, String bio, String college, String email, String password
            , String program, String year) {
        Document user = new Document("username", username)
                .append("bio", bio)
                .append("college", college)
                .append("email", email)
                .append("password", password)
                .append("program", program)
                .append("year", year);
        userCollection.insertOne(user);
    }

    // get user
    public Document getUser(String username) {
        return userCollection.find(eq("username", username)).first();
    }

    public Document getPassword(String password) {
        return userCollection.find(eq("password", password)).first();
    }

    // check if a user exists by username
    public boolean userExists(String username) {
        return userCollection.find(eq("username", username)).first() != null;
    }

    // delete a user by username, returns true if deleted
    public boolean deleteUser(String username) {
        DeleteResult result = userCollection.deleteOne(eq("username", username));
        return result.getDeletedCount() > 0;
    }

    // update a user password by username
    public boolean updateUserPassword(String username, String newPassword) {
        UpdateResult result = userCollection.updateOne(eq("username", username), set("password", newPassword));
        return result.getMatchedCount() > 0; // Returns true if a document was updated
    }
}

/*
public class MongoUserDataAccessObject {

    private final MongoCollection<Document> userCollection;

    public MongoUserDataAccessObject(MongoDatabase userDataBase) {
        MongoDatabase database = MongoDBConnection.getDatabase("UserDatabase"); // Replace with your database name
        userCollection = database.getCollection("users"); // Replace with your collection name
    }

    // Add User
    public void addUser(String username, String email, String password) {
        Document user = new Document("username", username)
                .append("email", email)
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

 */

