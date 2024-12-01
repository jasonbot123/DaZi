package use_case.chatsave;

import entity.ChatMessage;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ChatSaveOutputData {
    private final ChatMessage message;

    public ChatSaveOutputData(ChatMessage message) {
        this.message = message;
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

    public LocalDateTime getTimestamp() {
        return message.getTimestamp();
    }

}
