package use_case.search;

import data_access.MongoPostDataAccessObject;
import entity.Post;
import interface_adapter.search.SearchViewModel;
import use_case.post.PostDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class SearchInteractor implements SearchInputBoundary{
    private final PostDataAccessInterface dataAccess;
    private final SearchViewModel viewModel;

    public SearchInteractor(PostDataAccessInterface dataAccess, SearchViewModel viewModel1) {
        this.dataAccess = dataAccess;
        this.viewModel = viewModel1;
    }

    @Override
    public List<Post> searchPostsByTitle(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            viewModel.setErrorMessage("Keyword cannot be empty.");
            return new ArrayList<>();
        }

        List<Post> results = dataAccess.searchPostsByTitle(keyword);
        if (results.isEmpty()) {
            viewModel.setErrorMessage("No posts found for keyword: " + keyword);
        } else {
            viewModel.setSearchResults(results);
        }
        return results;
    }

    /*
    public List<Post> searchPostsByTitle(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be null or empty.");
        }
        return postDAO.searchPostsByTitle(keyword.trim());
    }

     */
}