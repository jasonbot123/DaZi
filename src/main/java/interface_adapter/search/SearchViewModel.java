package interface_adapter.search;

import entity.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * A place to hold data that the UI observes to display search results or error messages.
 */

public class SearchViewModel {
    private List<Post> searchResults = new ArrayList<>();
    private String errorMessage = null;

    public void setSearchResults(List<Post> searchResults) {

        this.searchResults = new ArrayList<>(searchResults);
    }

    public List<Post> getSearchResults() {

        return new ArrayList<>(this.searchResults);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {

        this.errorMessage = errorMessage;
        if (errorMessage != null) {
            this.searchResults = new ArrayList<>();
        }
    }
}
