package use_case.search;

import entity.Post;

import java.util.List;

/**
 * Interface that defines the methods for formatting or transforming the results
 * from the search interactor into a form suitable for the searchPostsUI.
 */
public interface SearchOutputBoundary {
    void presentSearchResults(List<Post> results);
}