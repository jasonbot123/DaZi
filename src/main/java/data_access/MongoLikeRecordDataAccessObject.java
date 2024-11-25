package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.LikeRecord;
import org.bson.Document;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class MongoLikeRecordDataAccessObject {
    private final MongoCollection<Document> likeCollection;

    public MongoLikeRecordDataAccessObject(MongoDatabase database) {
        this.likeCollection = database.getCollection("LikeRecords");
    }

    public void addLikeRecord(LikeRecord record) {
        Document doc = new Document("username", record.getUsername())
                .append("postTitle", record.getPostTitle());
        likeCollection.insertOne(doc);
    }

    public void removeLikeRecord(String username, String postTitle) {
        likeCollection.deleteOne(
                and(
                        eq("username", username),
                        eq("postTitle", postTitle)
                )
        );
    }

    public boolean hasUserLiked(String username, String postTitle) {
        return likeCollection.find(
                and(
                        eq("username", username),
                        eq("postTitle", postTitle)
                )
        ).first() != null;
    }
}