package interface_adapter;

import data_access.InMemoryPostDataAccessObject;
import entity.Post;
import entity.Section;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search.SearchPresenter;
import org.junit.Test;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class searchViewModelTest {
    @Test
    public void testSearchUpdatesViewModel() {
        // Mock or use InMemoryPostDataAccessObject for testing
        InMemoryPostDataAccessObject mockDAO = new InMemoryPostDataAccessObject();

        // Add sample data to DAO
        mockDAO.addPost(new Post("Study Hard", "Content about studying", Section.STUDYING, "user1", LocalDateTime.now()));
        mockDAO.addPost(new Post("Gaming Night", "Gaming-related content", Section.GAMING, "user2", LocalDateTime.now()));

        SearchViewModel viewModel = new SearchViewModel();
        SearchOutputBoundary presenter = new SearchPresenter(viewModel);
        SearchInteractor interactor = new SearchInteractor(mockDAO, presenter);

        // Perform search
        interactor.searchPostsByTitle("Study");

        // Assert that the viewModel contains the correct results
        assertEquals(1, viewModel.getSearchResults().size());
        assertEquals("Study Hard", viewModel.getSearchResults().get(0).getTitle());
        assertNull(viewModel.getErrorMessage());
    }
}
