package interface_adapter.search;

import entity.Post;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel {
    private List<Post> searchResults = new ArrayList<>();
    private String errorMessage = null;

    public List<Post> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Post> searchResults) {
        this.searchResults = searchResults;
        this.errorMessage = null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        this.searchResults = new ArrayList<>(); // Clear previous results
    }
}
