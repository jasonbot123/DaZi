package entity;

import java.time.LocalDateTime;

public class Post {
    private String title;
    private String content;
    private Section section;
    private LocalDateTime timestamp;
    private String username;

    public Post(String title, String content, Section section, String username, LocalDateTime timestamp) {
        this.title = title;
        this.content = content;
        this.section = section;
        this.timestamp = timestamp;
        this.timestamp = LocalDateTime.now();
        this.username = username;
    }

    // Getters
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

    // Setters
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

    // TODO: for displaying limited content on the homepage, not implemented to Homepage
    public String getTruncatedContent(int maxLength) {
        return content.length() > maxLength ? content.substring(0, maxLength) + "..." : content;
    }
}