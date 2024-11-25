package use_case.chat;
import data_access.MongoChatDataAccessObject;
import service.ChatService;

import javax.swing.*;
import java.util.List;
import entity.Message;

public class ManageChat {
    private final ChatService chatService;
    private final String username;
    private final String receiver;
    private final JTextArea chatArea;

    public ManageChat(ChatService chatService, String username, String receiver, JTextArea chatArea) {
        this.chatService = chatService;
        this.username = username;
        this.receiver = receiver;
        this.chatArea = chatArea;
    }

    // Method to send a message
    public void sendMessage(String content) {
        if (content.isEmpty() || receiver.isEmpty()) {
            System.out.println("Content and receiver are required to send a message.");
            return;
        }

        // Ensure that the receiver remains unchanged and is used correctly
        SendMessage sendMessage = new SendMessage(chatService, username);
        sendMessage.execute(receiver, content);
        appendMessage("You: " + content);
    }

    // Method to add messages to the chat area
    public void appendMessage(String message) {
        chatArea.append(message + "\n");
    }

    // Method to load previous messages between the two users
    public void loadPreviousMessages() {
        List<Message> messages = chatService.getMessagesBetweenUsers(username, receiver);
        if (messages == null || messages.isEmpty()) {
            System.out.println("No previous messages found.");
        } else {
            for (Message message : messages) {
                String formattedMessage = message.getSender() + ": " + message.getContent();
                appendMessage(formattedMessage);
            }
        }
    }
}
