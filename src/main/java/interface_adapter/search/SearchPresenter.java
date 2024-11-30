package interface_adapter.search;

import entity.Post;
import use_case.search.SearchOutputBoundary;

import java.util.List;

public class SearchPresenter implements SearchOutputBoundary {
    @Override
    public void presentSearchResults(List<Post> results) { // results for UI consumption
        results.forEach(post -> System.out.println("Title: " + post.getTitle() + ", Content: " + post.getContent()));
    }

}
