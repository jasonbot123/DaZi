package entity;

import use_case.post.CreateAPost;
import use_case.post.ManagePost;

import java.time.LocalDateTime;

public class Post {
    private String title;
    private String content;
    private Section section;
    private LocalDateTime timestamp;
    private String username; // Placeholder until user management is implemented

    public Post(String title, String content, Section section, String username) {
        this.title = title;
        this.content = content;
        this.section = section;
        this.timestamp = LocalDateTime.now();
        this.username = username;
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

    // for displaying the limited content on homepage
    public String getTruncatedContent(int maxLength) {
        return content.length() > maxLength ? content.substring(0, maxLength) + "..." : content;
    }
}