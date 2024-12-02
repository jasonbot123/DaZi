package interface_adapter.posts;

import entity.Post;

import java.util.List;

public class SectionViewModel {
    private List<Post> posts;
    private String errorMessage;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
