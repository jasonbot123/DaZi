package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.Comment;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MongoCommentDataAccessObject {
    private MongoCollection<Document> commentCollection;

    public MongoCommentDataAccessObject(MongoDatabase database) {
        this.commentCollection = database.getCollection("Comments");
        commentCollection.createIndex(new Document("postId", 1));
    }

    public void addComment(Comment comment) {
        Document commentDoc = new Document("_id", comment.getId())
                .append("postId", comment.getPostId())
                .append("content", comment.getContent())
                .append("username", comment.getUsername())
                .append("timestamp", comment.getTimestamp().toInstant(ZoneOffset.UTC));
        commentCollection.insertOne(commentDoc);
    }

    public List<Comment> getCommentsForPost(ObjectId postId) {
        List<Comment> comments = new ArrayList<>();
        commentCollection.find(eq("postId", postId))
                .sort(new Document("timestamp", 1))
                .forEach(doc -> {
                    Comment comment = new Comment(
                            doc.getObjectId("postId"),
                            doc.getString("content"),
                            doc.getString("username"),
                            LocalDateTime.ofInstant(doc.getDate("timestamp").toInstant(), ZoneOffset.UTC)
                    );
                    comment.setId(doc.getObjectId("_id"));
                    comments.add(comment);
                });
        return comments;
    }
}