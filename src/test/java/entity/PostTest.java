package entity;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PostTest {

    @Test
    void testFromDocument() {

        Document doc = new Document("_id", new ObjectId("67465b7a481b4c2a4c9f65f9"))
                .append("title", "Test Post")
                .append("content", "This is a test content.")
                .append("section", "OTHERS")
                .append("username", "testUser")
                .append("timestamp", new Date())
                .append("likes", 10);


        Post post1 = Post.fromDocument(doc);
        Post post2 = Post.fromDocument(doc);


        assertEquals(post1, post2, "Posts created from the same document should be equal.");
        assertEquals(post1.hashCode(), post2.hashCode(), "Hash codes of posts should match.");
    }

    @Test
    void testFromDocumentWithInvalidSection() {
        // Prepare a Document with an invalid section
        Document doc = new Document("_id", new ObjectId())
                .append("title", "Invalid Section Test")
                .append("content", "This content has an invalid section.")
                .append("section", "INVALID") // Invalid section
                .append("username", "testUser")
                .append("timestamp", new Date())
                .append("likes", 0);

        // Verify that fromDocument returns null for invalid sections
        Post post = Post.fromDocument(doc);
        assertEquals(null, post, "Post should be null for invalid sections.");
    }
}