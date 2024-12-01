package interface_adapter.search;

import entity.Post;
import use_case.search.SearchOutputBoundary;

import java.util.ArrayList;
import java.util.List;

/**
 * Format and process the search results from the search interactor into a format for the SearchPostsUI.
 */

public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel viewModel;

    public SearchPresenter(SearchViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentSearchResults(List<Post> results) {

        if (results.isEmpty()) {
            viewModel.setSearchResults(new ArrayList<>());
            viewModel.setErrorMessage("No results found.");
        } else {
            viewModel.setErrorMessage(null); // Clear any previous error
            viewModel.setSearchResults(results);
        }

    }

}
