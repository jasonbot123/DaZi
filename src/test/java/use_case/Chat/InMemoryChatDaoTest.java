package use_case.Chat;


import data_access.InMemoryChatDataAccessObject;
import entity.ChatMessage;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.chatsave.ChatSaveDataAccessInterface;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryChatDaoTest {
    private ChatSaveDataAccessInterface chatDao;
    private LocalDateTime timestamp;

    @BeforeEach
    public void setUp() {
        chatDao = new InMemoryChatDataAccessObject();
        timestamp = LocalDateTime.of(2023, 10, 15, 10, 30);
    }

    @Test
    public void testSaveMessage() {
        Document message = new Document("sender", "Alice")
                .append("receiver", "Bob")
                .append("content", "Hello, Bob!")
                .append("timestamp", java.util.Date.from(timestamp.atZone(java.time.ZoneId.systemDefault()).toInstant()));

        chatDao.saveMessage(message);
        List<ChatMessage> messages = chatDao.getPreviousMessages("Alice", "Bob");

        assertEquals(1, messages.size());
        ChatMessage savedMessage = messages.get(0);
        assertEquals("Alice", savedMessage.getSender());
        assertEquals("Bob", savedMessage.getReceiver());
        assertEquals("Hello, Bob!", savedMessage.getContent());
        assertEquals(timestamp, savedMessage.getTimestamp());
    }

    @Test
    public void testGetPreviousMessages() {
        Document message1 = new Document("sender", "Alice")
                .append("receiver", "Bob")
                .append("content", "Hello, Bob!")
                .append("timestamp", java.util.Date.from(timestamp.atZone(java.time.ZoneId.systemDefault()).toInstant()));

        Document message2 = new Document("sender", "Bob")
                .append("receiver", "Alice")
                .append("content", "Hi, Alice!")
                .append("timestamp", java.util.Date.from(timestamp.plusMinutes(5).atZone(java.time.ZoneId.systemDefault()).toInstant()));

        Document message3 = new Document("sender", "Alice")
                .append("receiver", "Charlie")
                .append("content", "Hey, Charlie!")
                .append("timestamp", java.util.Date.from(timestamp.plusMinutes(10).atZone(java.time.ZoneId.systemDefault()).toInstant()));

        chatDao.saveMessage(message1);
        chatDao.saveMessage(message2);
        chatDao.saveMessage(message3);

        List<ChatMessage> conversation = chatDao.getPreviousMessages("Alice", "Bob");

        assertEquals(2, conversation.size());
        assertEquals("Alice", conversation.get(0).getSender());
        assertEquals("Bob", conversation.get(0).getReceiver());
        assertEquals("Hello, Bob!", conversation.get(0).getContent());

        assertEquals("Bob", conversation.get(1).getSender());
        assertEquals("Alice", conversation.get(1).getReceiver());
        assertEquals("Hi, Alice!", conversation.get(1).getContent());
    }

    @Test
    public void testNoMessagesBetweenUsers() {
        Document message = new Document("sender", "Alice")
                .append("receiver", "Charlie")
                .append("content", "Hey, Charlie!")
                .append("timestamp", java.util.Date.from(timestamp.atZone(java.time.ZoneId.systemDefault()).toInstant()));

        chatDao.saveMessage(message);

        List<ChatMessage> conversation = chatDao.getPreviousMessages("Alice", "Bob");

        assertTrue(conversation.isEmpty());
    }
}
