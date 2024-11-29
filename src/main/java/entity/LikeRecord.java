package entity;

public class LikeRecord {
    private String username;
    private String postTitle;

    public LikeRecord(String username, String postTitle) {
        this.username = username;
        this.postTitle = postTitle;
    }

    public String getUsername() {
        return username;
    }

    public String getPostTitle() {
        return postTitle;
    }
}
