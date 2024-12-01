package use_case.search;

import entity.Post;

import java.util.List;

/**
 * The interface that defines what operations the search interactor must implement for the use case.
 */

public interface SearchInputBoundary {
    List<Post> searchPostsByTitle(String keyword);
}


