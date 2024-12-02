package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import entity.Post;
import entity.Section;
import org.bson.Document;
import org.bson.types.ObjectId;
import use_case.post.PostDataAccessInterface;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class MongoPostDataAccessObject implements PostDataAccessInterface {
    private MongoCollection<Document> postCollection;

    public MongoPostDataAccessObject(MongoDatabase database) {
        this.postCollection = database.getCollection("Posts");
    }

    //  add a new post
    @Override
    public void addPost(Post post) {
        try {
            Document doc = new Document()
                    .append("title", post.getTitle())
                    .append("content", post.getContent())
                    .append("section", post.getSection().toString())
                    .append("username", post.getUsername())
                    .append("timestamp", Date.from(post.getTimestamp().toInstant(ZoneOffset.UTC)))
                    .append("likes", post.getLikes());

            postCollection.insertOne(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // get a post by title
    public Document getPostByTitle(String title) {
        return postCollection.find(eq("title", title)).first();
    }

    // get a post by title, for search bar
    public List<Post> searchPostsByTitle(String title) {
        List<Post> posts = new ArrayList<>();

        try {
            // Use a case-insensitive regex to find matching titles
            for (Document doc : postCollection.find(new Document("title", new Document("$regex", title).append("$options", "i")))) {
                // System.out.println("Matching Document: " + doc.toJson()); // TODO
                Post post = Post.fromDocument(doc);
                if (post != null) {
                    posts.add(post);
                }
            }
        } catch (Exception e) {
            System.err.println("Error searching posts by title: " + e.getMessage());
            e.printStackTrace();
        }

        return posts;
    }

    // get a post by section, for section page
    public List<Post> getPostsBySection(String section, int page, int pageSize) {

        List<Post> posts = new ArrayList<>();

        try (MongoCursor<Document> cursor = postCollection.find(eq("section", section))
                .sort(new Document("timestamp", -1)) // Sort by most recent posts
                .skip(page * pageSize) // Pagination logic
                .limit(pageSize)
                .iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                // System.out.println("Document from MongoDB: " + doc.toJson());
                Post post = Post.fromDocument(doc);
                if (post != null) {
                    posts.add(post);
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching posts by section: " + e.getMessage());
            e.printStackTrace();
        }

        return posts;
    }

    // delete a post by title
    public boolean deletePostByTitle(String title) {
        return postCollection.deleteOne(eq("title", title)).getDeletedCount() > 0;
    }

    // update post content by title
    public boolean updatePostContent(String title, String newContent) {
        return postCollection.updateOne(eq("title", title), new Document("$set", new Document("content", newContent))).getMatchedCount() > 0;
    }

    // get all posts in the database, for displaying the posts on HomePage1
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (MongoCursor<Document> cursor = postCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                posts.add(documentToPost(doc));
            }
        }
        return posts;
    }

    public void updateLikes(ObjectId postId, int likes) {
        postCollection.updateOne(
                eq("_id", postId),
                new Document("$set", new Document("likes", likes))
        );
    }

    public List<Post> getPostsByPage(int page, int pageSize) {
        List<Post> posts = new ArrayList<>();
        try (MongoCursor<Document> cursor = postCollection.find()
                .sort(new Document("timestamp", -1))
                .skip(page * pageSize)
                .limit(pageSize)
                .iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                posts.add(documentToPost(doc));
            }
        }
        return posts;
    }


    public List<Post> getAllPostsByUsername(String username) {
        List<Post> posts = new ArrayList<>();
        try (MongoCursor<Document> cursor = postCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                if (username.equals(doc.getString("username"))) {
                    String title = doc.getString("title");
                    String content = doc.getString("content");
                    String section = doc.getString("section");
                    LocalDateTime timestamp = LocalDateTime.ofInstant(doc.getDate("timestamp").toInstant(), ZoneOffset.UTC);

                    posts.add(new Post(title, content, Section.valueOf(section), username, timestamp));
                }
            }
        }
        return posts;
    }


        private Post documentToPost(Document doc) {
            String title = doc.getString("title");
            String content = doc.getString("content");
            String section = doc.getString("section");
            String username = doc.getString("username");
            LocalDateTime timestamp = LocalDateTime.ofInstant(
                    doc.getDate("timestamp").toInstant(),
                    ZoneOffset.UTC
            );
            Post post = new Post(title, content, Section.valueOf(section), username, timestamp);
            post.setId(doc.getObjectId("_id"));
            post.setLikes(doc.getInteger("likes", 0));
            return post;
        }



        public void createIndexes() {
            postCollection.createIndex(new Document("timestamp", -1));
            postCollection.createIndex(new Document("_id", 1));

        }
    }


