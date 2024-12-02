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
        likeCollection.createIndex(new Document("username", 1).append("postId", 1));
    }

    public void addLikeRecord(LikeRecord record) {
        Document doc = new Document("username", record.getUsername())
                .append("postId", record.getPostId());
        likeCollection.insertOne(doc);
    }

    public void removeLikeRecord(String username, String postId) {
        likeCollection.deleteOne(
                and(
                        eq("username", username),
                        eq("postId", postId)
                )
        );
    }

    public boolean hasUserLiked(String username, String postId) {
        return likeCollection.find(
                and(
                        eq("username", username),
                        eq("postId", postId)
                )
        ).first() != null;
    }
}