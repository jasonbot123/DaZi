package entity;

import java.time.LocalDateTime;

public class Comment {
    private String postId;
    private String content;
    private String username;
    private LocalDateTime timestamp;

    public Comment(String postId, String content, String username, LocalDateTime timestamp) {
        this.postId = postId;
        this.content = content;
        this.username = username;
        this.timestamp = timestamp;
    }

    // Getters
    public String getPostId() { return postId; }
    public String getContent() { return content; }
    public String getUsername() { return username; }
    public LocalDateTime getTimestamp() { return timestamp; }

    // Setters
    public void setPostId(String postId) { this.postId = postId; }
    public void setContent(String content) { this.content = content; }
    public void setUsername(String username) { this.username = username; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}