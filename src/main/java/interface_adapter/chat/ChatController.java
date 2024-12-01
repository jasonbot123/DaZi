package interface_adapter.chat;

import use_case.chatsave.ChatSaveInputBoundary;
import use_case.chatsave.ChatSaveInputData;

import java.util.List;

public class ChatController {

    private final ChatSaveInputBoundary chatSaveInputBoundary;

    public ChatController(ChatSaveInputBoundary chatSaveInputBoundary) {
        this.chatSaveInputBoundary = chatSaveInputBoundary;
    }

    public void sendMessage(String sender, String receiver, String content) {
        ChatSaveInputData inputData = new ChatSaveInputData(sender, receiver, content);
        chatSaveInputBoundary.sendMessage(inputData);
    }

    public void loadPreviousMessages(String username, String contact) {
        chatSaveInputBoundary.loadPreviousMessages(username, contact);
    }
}
