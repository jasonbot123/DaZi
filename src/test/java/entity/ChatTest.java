package entity;

import entity.ChatMessage;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class ChatTest {

    private ChatMessage chatMessage;
    private LocalDateTime timestamp;

    @BeforeEach
    public void setUp() {
        timestamp = LocalDateTime.of(2023, Month.OCTOBER, 15, 10, 30);
        chatMessage = new ChatMessage("Alice", "Bob", "Hello, Bob!", timestamp);
    }

    @Test
    public void testGetSender() {
        assertEquals("Alice", chatMessage.getSender());
    }

    @Test
    public void testGetReceiver() {
        assertEquals("Bob", chatMessage.getReceiver());
    }

    @Test
    public void testGetContent() {
        assertEquals("Hello, Bob!", chatMessage.getContent());
    }

    @Test
    public void testGetTimestamp() {
        assertEquals(timestamp, chatMessage.getTimestamp());
    }

    @Test
    public void testToDocument() {
        Document document = chatMessage.toDocument();
        assertEquals("Alice", document.getString("sender"));
        assertEquals("Bob", document.getString("receiver"));
        assertEquals("Hello, Bob!", document.getString("content"));
        assertEquals(timestamp, document.get("timestamp", LocalDateTime.class));
    }

    @Test
    public void testFromDocument() {
        Document document = new Document("sender", "Alice")
                .append("receiver", "Bob")
                .append("content", "Hello, Bob!")
                .append("timestamp", java.util.Date.from(timestamp.atZone(java.time.ZoneId.systemDefault()).toInstant()));

        ChatMessage fromDocument = ChatMessage.fromDocument(document);

        assertEquals(chatMessage.getSender(), fromDocument.getSender());
        assertEquals(chatMessage.getReceiver(), fromDocument.getReceiver());
        assertEquals(chatMessage.getContent(), fromDocument.getContent());
        assertEquals(chatMessage.getTimestamp(), fromDocument.getTimestamp());
    }
}
