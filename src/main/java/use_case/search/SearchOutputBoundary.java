package use_case.search;

import entity.Post;

import java.util.List;

public interface SearchOutputBoundary {
    void presentSearchResults(List<Post> results);
}