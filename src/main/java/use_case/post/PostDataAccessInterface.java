package use_case.post;

import entity.Post;

import java.util.List;

public interface PostDataAccessInterface {
    List<Post> searchPostsByTitle(String keyword);
}