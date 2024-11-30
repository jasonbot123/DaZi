package use_case.search;

import entity.Post;

import java.util.List;

public interface SearchInputBoundary {
    List<Post> searchPostsByTitle(String keyword);
}


