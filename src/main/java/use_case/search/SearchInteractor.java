package use_case.search;

import data_access.MongoPostDataAccessObject;
import entity.Post;
import use_case.post.PostDataAccessInterface;

import java.util.List;

public class SearchInteractor implements SearchInputBoundary{
    private final MongoPostDataAccessObject postDAO;
    private final PostDataAccessInterface dataAccess;
    private final SearchOutputBoundary outputBoundary;

    public SearchInteractor(MongoPostDataAccessObject postDAO) {
        this.postDAO = postDAO;
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void searchPostsByTitle(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be empty");
        }
        List<Post> results = dataAccess.searchPostsByTitle(keyword);
        outputBoundary.presentSearchResults(results);
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