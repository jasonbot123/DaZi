package use_case.chatsave;

import entity.ChatMessage;
import org.bson.Document;

import java.time.LocalDateTime;

public class ChatSaveInputData {

    private final ChatMessage message;

    public ChatSaveInputData(String sender, String receiver, String content) {
        this.message = new ChatMessage(sender, receiver, content, LocalDateTime.now());
    }

    public ChatMessage getMessage() {
        return message;
    }

    public String getSender() {
        return message.getSender();
    }
    public String getReceiver() {
        return message.getReceiver();
    }

    public String getContent() {
        return message.getContent();
    }

    public LocalDateTime getTime() {
        return message.getTimestamp();
    }


}
