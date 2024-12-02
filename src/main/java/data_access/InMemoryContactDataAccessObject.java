package data_access;

import entity.ChatContact;
import use_case.contact.ChatContactDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class InMemoryContactDataAccessObject implements ChatContactDataAccessInterface {
    private final List<ChatContact> contacts;

    public InMemoryContactDataAccessObject() {
        this.contacts = new ArrayList<>();
    }

    @Override
    public List<ChatContact> getChatContacts(String username) {
        List<ChatContact> userContacts = new ArrayList<>();
        for (ChatContact contact : contacts) {
            if (contact.getUsername().equals(username)) {
                userContacts.add(contact);
            }
        }
        return userContacts;
    }

    // Method to add a contact for testing purposes
    public void addContact(ChatContact contact) {
        contacts.add(contact);
    }

    // Method to get all contacts for testing purposes
    public List<ChatContact> getContacts() {
        return contacts;
    }
}
