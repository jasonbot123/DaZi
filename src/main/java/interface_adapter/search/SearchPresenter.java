package interface_adapter.search;

import entity.Post;
import use_case.search.SearchOutputBoundary;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel viewModel;

    public SearchPresenter(SearchViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentSearchResults(List<Post> results) {
        System.out.println("Presenting search results: " + results);// results for UI consumption
        if (results.isEmpty()) {

            viewModel.setSearchResults(new ArrayList<>());
            viewModel.setErrorMessage("No results found.");
        } else {
            System.out.println("else-Presenting search results: " + results);
            viewModel.setSearchResults(results);
            viewModel.setErrorMessage(null);
        }
        System.out.println("ViewModel after presenting: " + viewModel.getSearchResults());
    }

}
