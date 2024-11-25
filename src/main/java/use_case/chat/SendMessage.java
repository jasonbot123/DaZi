package use_case.chat;

import service.ChatService;
import view.ChatWindow;

public class SendMessage {
    private final ChatService chatService;
    private final String username;

    public SendMessage(ChatService chatService, String username) {
        this.chatService = chatService;
        this.username = username;
    }

    public void execute(String receiver, String content) {
        if (receiver.isEmpty() || content.isEmpty()) {
            System.out.println("Receiver and content are required to send a message.");
            return;
        }

        chatService.sendMessage(username, receiver, content);
        System.out.println("Message sent successfully.");
    }
}

