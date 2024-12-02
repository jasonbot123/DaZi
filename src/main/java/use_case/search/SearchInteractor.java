package use_case.search;

import data_access.MongoPostDataAccessObject;
import entity.Post;
import interface_adapter.search.SearchViewModel;
import use_case.post.PostDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * The search use case interactor that implements the SearchInputBoundary interface.
 * Get data from the Data Access Interface then pass the data to the Output Boundary.
 */
public class SearchInteractor implements SearchInputBoundary{
    private final PostDataAccessInterface dataAccess;
    private final SearchOutputBoundary presenter;

    public SearchInteractor(PostDataAccessInterface dataAccess, SearchOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public List<Post> searchPostsByTitle(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            presenter.presentSearchResults(new ArrayList<>());
            return new ArrayList<>();
        }
        List<Post> results = dataAccess.searchPostsByTitle(keyword);
        presenter.presentSearchResults(results);
        return results;
    }

}