package interface_adapter.search;

import entity.Post;
import use_case.search.SearchOutputBoundary;

import java.util.List;

public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel searchViewModel;

    public SearchPresenter(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
    }

    @Override
    public void presentSearchResults(List<Post> results) {
        searchViewModel.setSearchResults(results);
    }
}
