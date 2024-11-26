package entity;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;

public class Comment {
    private ObjectId id;
    private ObjectId postId;  // 使用ObjectId而不是String
    private String content;
    private String username;
    private LocalDateTime timestamp;

    public Comment(ObjectId postId, String content, String username, LocalDateTime timestamp) {
        this.id = new ObjectId();
        this.postId = postId;
        this.content = content;
        this.username = username;
        this.timestamp = timestamp;
    }

    // Getters
    public ObjectId getId() { return id; }
    public ObjectId getPostId() { return postId; }
    public String getContent() { return content; }
    public String getUsername() { return username; }
    public LocalDateTime getTimestamp() { return timestamp; }

    // Setters
    public void setId(ObjectId id) { this.id = id; }
    public void setPostId(ObjectId postId) { this.postId = postId; }
    public void setContent(String content) { this.content = content; }
    public void setUsername(String username) { this.username = username; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}