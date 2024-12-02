package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    public void testChatContactCreation() {
        String currentUsername = "Alice";
        ChatContact contact = new ChatContact(currentUsername);

        assertNotNull(contact, "ChatContact should be created successfully");
        assertEquals(currentUsername, contact.getUsername(), "Username should match the input username");
    }

    @Test
    public void testGetUsername() {
        String currentUsername = "Bob";
        ChatContact contact = new ChatContact(currentUsername);

        String username = contact.getUsername();
        assertEquals("Bob", username, "The username should be Bob");
    }
}
