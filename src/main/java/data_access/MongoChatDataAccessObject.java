package data_access;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.ChatMessage;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import use_case.chatsave.ChatSaveDataAccessInterface;


import static com.mongodb.client.model.Filters.*;

public class MongoChatDataAccessObject implements ChatSaveDataAccessInterface {
    private final MongoCollection<Document> collection;

    public MongoChatDataAccessObject(MongoDatabase database) {
        this.collection = database.getCollection("chats");
    }

    @Override
    public void saveMessage(Document message) {
        collection.insertOne(message);
    }

    @Override
    public List<ChatMessage> getPreviousMessages(String username, String contact) {
        List<ChatMessage> messages = new ArrayList<>();
        collection.find(or(
                and(eq("sender", username), eq("receiver", contact)),
                and(eq("sender", contact), eq("receiver", username))
        )).forEach(doc -> messages.add(ChatMessage.fromDocument(doc)));
        return messages;
    }
}

