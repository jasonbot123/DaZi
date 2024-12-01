package entity;

import org.bson.Document;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id);
    }

    /**
     * Generate a unique integer (hash code) for each Post object based on its id.
     * @return intergers of unique id.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Convert the MongoDB document object into Post object.
     * @param doc the MongoDB document object (json)
     * @return post
     */
    public static Post fromDocument(Document doc) {
        String title = doc.getString("title");
        String content = doc.getString("content");
        String sectionString = doc.getString("section");
        Section section;

        try {
            section = Section.valueOf(sectionString.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid section: " + sectionString);
            return null; // Return null for invalid sections
        }

        String username = doc.getString("username");
        LocalDateTime timestamp = doc.getDate("timestamp").toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
        int likes = doc.getInteger("likes", 0);

        Post post = new Post(title, content, section, username, timestamp);
        post.setLikes(likes);

        Object idField = doc.get("_id");
        if (idField instanceof ObjectId) {
            post.setId((ObjectId) idField);
        }

        return post;
    }

    public String getTruncatedContent(int maxLength) {
        return content.length() > maxLength ? content.substring(0, maxLength) + "..." : content;
    }
}