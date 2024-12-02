package use_case.chatsave;

import entity.ChatMessage;
import org.bson.Document;

import java.util.List;

public interface ChatSaveDataAccessInterface {
    void saveMessage(Document message);
    List<ChatMessage> getPreviousMessages(String username, String receiver);
}
