package data_access;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import entity.Message;

import static com.mongodb.client.model.Filters.*;

public class MongoChatDataAccessObject {
    //Make it Final
    private MongoCollection<Document> messagesCollection;

    public MongoChatDataAccessObject(MongoDatabase database) {
        this.messagesCollection = database.getCollection("messages");
    }

    // Save a new message to the database
    public void saveMessage(Message message) {
        Document messageDoc = new Document("sender", message.getSender())
                .append("receiver", message.getReceiver())
                .append("content", message.getContent())
                .append("timestamp", message.getTimestamp());

        messagesCollection.insertOne(messageDoc);
    }

    // Retrieve all messages between two users
    public List<Message> getMessagesBetweenUsers(String user1, String user2) {
        List<Message> messages = new ArrayList<>();
        for (Document doc : messagesCollection.find(or(
                and(eq("sender", user1), eq("receiver", user2)),
                and(eq("sender", user2), eq("receiver", user1))
        )).sort(Sorts.ascending("timestamp"))) {  // Sort by timestamp
            Message message = new Message(
                    doc.getString("sender"),
                    doc.getString("receiver"),
                    doc.getString("content")
            );
            message.setTimestamp(LocalDateTime.ofInstant(doc.getDate("timestamp").toInstant(), ZoneId.systemDefault()));
            messages.add(message);
        }
        return messages;
    }

}
