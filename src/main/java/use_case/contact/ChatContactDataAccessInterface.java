package use_case.contact;

import entity.ChatContact;

import java.util.List;

public interface ChatContactDataAccessInterface {
    List<ChatContact> getChatContacts(String username);
}
