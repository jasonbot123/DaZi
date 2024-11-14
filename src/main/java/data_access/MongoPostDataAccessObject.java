package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.Post;
import org.bson.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import static com.mongodb.client.model.Filters.eq;


public class MongoPostDataAccessObject {
    private MongoCollection<Document> postCollection;

    public MongoPostDataAccessObject(MongoDatabase database) {
        this.postCollection = database.getCollection("Posts");
    }

    //  add a new post
    public void addPost(Post post) {
        Document postDoc = new Document("title", post.getTitle())
                .append("content", post.getContent())
                .append("section", post.getSection().toString()) // Store section as a string
                .append("timestamp", post.getTimestamp().toInstant(ZoneOffset.UTC))
                .append("username", post.getUsername());
        postCollection.insertOne(postDoc);
    }

    // get a post by title
    public Document getPostByTitle(String title) {
        return postCollection.find(eq("title", title)).first();
    }

    // delete a post by title
    public boolean deletePostByTitle(String title) {
        return postCollection.deleteOne(eq("title", title)).getDeletedCount() > 0;
    }

    // update post content by title
    public boolean updatePostContent(String title, String newContent) {
        return postCollection.updateOne(eq("title", title), new Document("$set", new Document("content", newContent))).getMatchedCount() > 0;
    }

}
