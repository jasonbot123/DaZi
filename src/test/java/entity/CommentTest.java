package entity;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Test
    void testConstructorAndGetters() {
        // Prepare test data
        ObjectId postId = new ObjectId();
        String content = "This is a test comment";
        String username = "Test User";
        LocalDateTime timestamp = LocalDateTime.now();

        // Create Comment object
        Comment comment = new Comment(postId, content, username, timestamp);

        // Verify all fields are correctly set
        assertNotNull(comment.getId());
        assertEquals(postId, comment.getPostId());
        assertEquals(content, comment.getContent());
        assertEquals(username, comment.getUsername());
        assertEquals(timestamp, comment.getTimestamp());
    }

    @Test
    void testSetters() {
        // Create an initial Comment object
        Comment comment = new Comment(
            new ObjectId(),
            "Initial content",
            "Initial user",
            LocalDateTime.now()
        );

        // Prepare new test data
        ObjectId newId = new ObjectId();
        ObjectId newPostId = new ObjectId();
        String newContent = "Updated content";
        String newUsername = "New user";
        LocalDateTime newTimestamp = LocalDateTime.now().plusHours(1);

        // Test all setter methods
        comment.setId(newId);
        comment.setPostId(newPostId);
        comment.setContent(newContent);
        comment.setUsername(newUsername);
        comment.setTimestamp(newTimestamp);

        // Verify all fields are correctly updated
        assertEquals(newId, comment.getId());
        assertEquals(newPostId, comment.getPostId());
        assertEquals(newContent, comment.getContent());
        assertEquals(newUsername, comment.getUsername());
        assertEquals(newTimestamp, comment.getTimestamp());
    }
} 