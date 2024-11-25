package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.Comment;
import org.bson.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MongoCommentDataAccessObject {
    private MongoCollection<Document> commentCollection;

    public MongoCommentDataAccessObject(MongoDatabase database) {
        this.commentCollection = database.getCollection("Comments");
    }

    public void addComment(Comment comment) {
        Document commentDoc = new Document("postId", comment.getPostId())
                .append("content", comment.getContent())
                .append("username", comment.getUsername())
                .append("timestamp", comment.getTimestamp().toInstant(ZoneOffset.UTC));
        commentCollection.insertOne(commentDoc);
    }

    public List<Comment> getCommentsForPost(String postId) {
        List<Comment> comments = new ArrayList<>();
        commentCollection.find(eq("postId", postId)).forEach(doc -> {
            Comment comment = new Comment(
                    doc.getString("postId"),
                    doc.getString("content"),
                    doc.getString("username"),
                    LocalDateTime.ofInstant(doc.getDate("timestamp").toInstant(), ZoneOffset.UTC)
            );
            comments.add(comment);
        });
        return comments;
    }
}