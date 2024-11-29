package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import entity.Post;
import entity.Section;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class MongoPostDataAccessObject {
    private MongoCollection<Document> postCollection;

    public MongoPostDataAccessObject(MongoDatabase database) {
        this.postCollection = database.getCollection("Posts");
    }

    //  add a new post
    public void addPost(Post post) {
        Document postDoc = new Document("_id", post.getId())
                .append("title", post.getTitle())
                .append("content", post.getContent())
                .append("section", post.getSection().toString())
                .append("timestamp", post.getTimestamp().toInstant(ZoneOffset.UTC))
                .append("username", post.getUsername())
                .append("likes", post.getLikes());
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

    // get all posts in the database, for displaying the posts on HomePage1
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (MongoCursor<Document> cursor = postCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                posts.add(documentToPost(doc));
            }
        }
        return posts;
    }

    public void updateLikes(ObjectId postId, int likes) {
        postCollection.updateOne(
                eq("_id", postId),
                new Document("$set", new Document("likes", likes))
        );
    }

    public List<Post> getPostsByPage(int page, int pageSize) {
        List<Post> posts = new ArrayList<>();
        try (MongoCursor<Document> cursor = postCollection.find()
                .sort(new Document("timestamp", -1))
                .skip(page * pageSize)
                .limit(pageSize)
                .iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                posts.add(documentToPost(doc));
            }
        }
        return posts;
    }

    private Post documentToPost(Document doc) {
        String title = doc.getString("title");
        String content = doc.getString("content");
        String section = doc.getString("section");
        String username = doc.getString("username");
        LocalDateTime timestamp = LocalDateTime.ofInstant(
                doc.getDate("timestamp").toInstant(),
                ZoneOffset.UTC
        );
        Post post = new Post(title, content, Section.valueOf(section), username, timestamp);
        post.setId(doc.getObjectId("_id"));
        post.setLikes(doc.getInteger("likes", 0));
        return post;
    }

    public void createIndexes() {
        postCollection.createIndex(new Document("timestamp", -1));
        postCollection.createIndex(new Document("_id", 1));
    }
}


