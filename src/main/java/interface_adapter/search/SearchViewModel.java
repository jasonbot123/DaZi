package interface_adapter.search;

import entity.Post;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel {
    private List<Post> searchResults = new ArrayList<>();
    private String errorMessage = null;

    public void setSearchResults(List<Post> searchResults) {
        System.out.println("SearchViewModel - set - Setting search results: " + searchResults);
        this.searchResults = new ArrayList<>(searchResults); // Deep copy
    }

    public List<Post> getSearchResults() {
        System.out.println("SearchViewModel - get - Returning search results: " + searchResults);
        return new ArrayList<>(this.searchResults); // Return a copy
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        this.searchResults = new ArrayList<>(); // Clear previous results
    }
}
