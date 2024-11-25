package use_case.post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import entity.Post;
import entity.Section;

public class ManagePost {
    private List<Post> posts;

    public ManagePost() {
        this.posts = new ArrayList<>();
    }

    public void addPost(String title, String content, Section section, String username) { //TODO: username
        Post post = new Post(title, content, section, username, LocalDateTime.now());
        this.posts.add(post);
    }

    public List<Post> getPosts() {
        return this.posts;
    }

}
