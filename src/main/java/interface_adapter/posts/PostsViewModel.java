package interface_adapter.posts;

import entity.Post;

import java.util.ArrayList;
import java.util.List;

public class PostsViewModel {
    private final List<Post> posts = new ArrayList<>();
    private boolean loading = false;
    private String errorMessage;

    public List<Post> getPosts() {
        return new ArrayList<>(posts); // return a copy to avoid external modification
    }

    public void setPosts(List<Post> newPosts) {
        posts.clear();
        posts.addAll(newPosts);
    }

    public void addPosts(List<Post> newPosts) {
        posts.addAll(newPosts);
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String msg) { this.errorMessage = msg;}
}