package data_access;

import entity.ChatMessage;
import org.bson.Document;
import use_case.chatsave.ChatSaveDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class InMemoryChatDataAccessObject implements ChatSaveDataAccessInterface {
    private final List<ChatMessage> messages;

    public InMemoryChatDataAccessObject() {
        messages = new ArrayList<ChatMessage>();
    }

    @Override
    public void saveMessage(Document message) {
        ChatMessage chatMessage = ChatMessage.fromDocument(message);
        messages.add(chatMessage);
    }

    @Override
    public List<ChatMessage> getPreviousMessages(String username, String receiver) {
        List<ChatMessage> conversation = new ArrayList<>();
        for (ChatMessage message : messages) {
            if ((message.getSender().equals(username) && message.getReceiver().equals(receiver)) ||
                    (message.getSender().equals(receiver) && message.getReceiver().equals(username))) {
                conversation.add(message);
            }
        }
        return conversation;
    }
}
