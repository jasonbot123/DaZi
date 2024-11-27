package entity;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;

public class Post {
    private ObjectId id;
    private String title;
    private String content;
    private Section section;
    private LocalDateTime timestamp;
    private String username;
    private int likes;

    public Post(String title, String content, Section section, String username, LocalDateTime timestamp) {
        this.id = new ObjectId();
        this.title = title;
        this.content = content;
        this.section = section;
        this.timestamp = timestamp;
        this.username = username;
        this.likes = 0;
    }

    // Getters
    public ObjectId getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public Section getSection() {
        return section;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public String getUsername() {
        return username;
    }
    public int getLikes() {
        return likes;
    }

    // Setters
    public void setId(ObjectId id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setSection(Section section) {
        this.section = section;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }

    // TODO: for displaying limited content on the homepage, not implemented to Homepage
    public String getTruncatedContent(int maxLength) {
        return content.length() > maxLength ? content.substring(0, maxLength) + "..." : content;
    }
}