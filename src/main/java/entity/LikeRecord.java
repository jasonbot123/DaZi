package entity;

public class LikeRecord {
    private String username;
    private String postId;

    public LikeRecord(String username, String postId) {
        this.username = username;
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public String getPostId() {
        return postId;
    }
}
