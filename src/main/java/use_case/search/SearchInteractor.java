package use_case.search;

import data_access.MongoPostDataAccessObject;
import entity.Post;

import java.util.List;

public class SearchInteractor {
    private final MongoPostDataAccessObject postDAO;

    public SearchInteractor(MongoPostDataAccessObject postDAO) {
        this.postDAO = postDAO;
    }

    public List<Post> searchPostsByTitle(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be null or empty.");
        }
        return postDAO.searchPostsByTitle(keyword.trim());
    }
}