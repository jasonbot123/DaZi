package entity;

import org.bson.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ChatMessage {
    private final String sender;
    private final String receiver;
    private final String content;
    private final LocalDateTime timestamp;

    public ChatMessage(String sender, String receiver, String content, LocalDateTime timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Document toDocument() {
        return new Document("sender", sender)
                .append("receiver", receiver)
                .append("content", content)
                .append("timestamp", timestamp);
    }

    public static ChatMessage fromDocument(Document document) {
        return new ChatMessage(
                document.getString("sender"),
                document.getString("receiver"),
                document.getString("content"),
                document.getDate("timestamp").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        );
    }

}
