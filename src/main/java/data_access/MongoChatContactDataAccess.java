package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.ChatContact;
import org.bson.Document;
import use_case.contact.ChatContactDataAccessInterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static com.mongodb.client.model.Filters.*;

public class MongoChatContactDataAccess implements ChatContactDataAccessInterface {
    private final MongoCollection<Document> collection;

    public MongoChatContactDataAccess(MongoDatabase database) {
        this.collection = database.getCollection("chats");
    }

    @Override
    public List<ChatContact> getChatContacts(String username) {
        Set<String> contactSet = new HashSet<>();
        List<ChatContact> contactList = new ArrayList<>();

        collection.find(or(eq("sender", username), eq("receiver", username))).forEach(doc -> {
            String sender = doc.getString("sender");
            String receiver = doc.getString("receiver");
            if (!sender.equals(username)) {
                contactSet.add(sender);
            }
            if (!receiver.equals(username)) {
                contactSet.add(receiver);
            }
        });

        for (String contact : contactSet) {
            contactList.add(new ChatContact(contact));
        }

        return contactList;
    }
}
