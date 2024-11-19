package entity;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Date;

public class Message {
    private ObjectId id;
    private LocalDateTime timestamp;
    private String content;
    private String sender;
    private String receiver;

    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    /// Getters and setters
    public ObjectId getId() {
        return id;
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


    public void setId(ObjectId id) {
        this.id = id;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

