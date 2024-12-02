package data_access;

import entity.ChatContact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.contact.ChatContactDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryContactDaoTest {

    private ChatContactDataAccessInterface contactDao;

    @BeforeEach
    public void setUp() {
        contactDao = new InMemoryContactDataAccessObject();
    }

    @Test
    public void testGetChatContactsEmpty() {
        List<ChatContact> contacts = contactDao.getChatContacts("Alice");
        assertTrue(contacts.isEmpty(), "Expected no contacts for the user Alice");
    }

    @Test
    public void testGetChatContacts() {
        InMemoryContactDataAccessObject testDao = (InMemoryContactDataAccessObject) contactDao;
        testDao.addContact(new ChatContact("Alice"));
        testDao.addContact(new ChatContact("Bob"));
        testDao.addContact(new ChatContact("Alice"));

        List<ChatContact> contacts = contactDao.getChatContacts("Alice");
        assertEquals(2, contacts.size(), "Expected 2 contacts for the user Alice");
        assertEquals("Alice", contacts.get(0).getUsername());
        assertEquals("Alice", contacts.get(1).getUsername());
    }

    @Test
    public void testAddContact() {
        InMemoryContactDataAccessObject testDao = (InMemoryContactDataAccessObject) contactDao;
        testDao.addContact(new ChatContact("Charlie"));

        List<ChatContact> contacts = testDao.getContacts();
        assertEquals(1, contacts.size(), "Expected 1 contact to be added");
        assertEquals("Charlie", contacts.get(0).getUsername(), "The contact username should be Charlie");
    }
}


