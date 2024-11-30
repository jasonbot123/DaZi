package use_case.post;

import entity.Post;

import java.util.List;

public interface PostDataAccessInterface {
    List<Post> searchPostsByTitle(String keyword);
    List<Post> getPostsBySection(String section, int page, int pageSize);

    List<Post> getPostsByPage(int i, int pageSize);

    void addPost(Post post);
}