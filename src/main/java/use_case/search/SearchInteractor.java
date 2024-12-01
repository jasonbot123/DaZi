package use_case.search;

import data_access.MongoPostDataAccessObject;
import entity.Post;
import interface_adapter.search.SearchViewModel;
import use_case.post.PostDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println("Interactor results: " + results);
        presenter.presentSearchResults(results);
        return results;
    }

}