package interface_adapter.search;

import use_case.search.SearchInputBoundary;

/**
 * Handles user input from the UI and forwarding it to the search interactor.
 */

public class SearchController {
    private final SearchInputBoundary interactor;

    public SearchController(SearchInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void performSearch(String keyword) {
        interactor.searchPostsByTitle(keyword);
    }
}
