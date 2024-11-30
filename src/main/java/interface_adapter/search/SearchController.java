package interface_adapter.search;

import use_case.search.SearchInputBoundary;

public class SearchController {
    private final SearchInputBoundary interactor;

    public SearchController(SearchInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void performSearch(String keyword) {
        interactor.searchPostsByTitle(keyword);
    }
}
