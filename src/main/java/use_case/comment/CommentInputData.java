package use_case.comment;

import org.bson.types.ObjectId;

public class CommentInputData {
    private final ObjectId postId;
    private final String content;
    private final String username;

    public CommentInputData(ObjectId postId, String content, String username) {
        this.postId = postId;
        this.content = content;
        this.username = username;
    }

    public ObjectId getPostId() { return postId; }
    public String getContent() { return content; }
    public String getUsername() { return username; }
} 