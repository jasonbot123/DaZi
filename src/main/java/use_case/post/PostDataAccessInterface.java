package use_case.post;

import entity.Post;

import java.util.List;

/**
 * Defines how should the SearchInteractor connect with the MongoDB database.
 * Abstract, so methods here retrieve posts without exposing implementation details.
 */

public interface PostDataAccessInterface {
    List<Post> searchPostsByTitle(String keyword);

    List<Post> getPostsBySection(String section, int page, int pageSize);

    List<Post> getPostsByPage(int i, int pageSize);

    void addPost(Post post);
}