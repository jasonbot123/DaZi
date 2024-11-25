package service;

import data_access.MongoChatDataAccessObject;
import data_access.MongoPostDataAccessObject;
import entity.Message;
import org.bson.Document;
import java.util.List;

public class ChatService {
    private final MongoChatDataAccessObject chatDAO;

    public ChatService(MongoChatDataAccessObject chatDAO) {
        this.chatDAO = chatDAO;
    }

    public void sendMessage(String sender, String receiver, String content) {
        Message message = new Message(sender, receiver, content);
        chatDAO.saveMessage(message);
    }

    public List<Message> getMessagesBetweenUsers(String user1, String user2) {
        List<Message> messages = chatDAO.getMessagesBetweenUsers(user1, user2);
        System.out.println("Messages retrieved by service: " + messages.size());
        return messages;
    }
}
