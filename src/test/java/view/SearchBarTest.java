package view;

import org.junit.jupiter.api.Test;
import view.HomePageUI.SearchBar;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class SearchBarTest {

    @Test
    void testSearchBarInitialization() {
        SearchBar searchBar = new SearchBar(null); // Pass null for simplicity
        JTextField searchField = searchBar.getSearchField();
        JButton searchButton = searchBar.getSearchButton();

        assertNotNull(searchField);
        assertNotNull(searchButton);

        // Check default state
        assertEquals("", searchField.getText());
        assertTrue(searchButton.isEnabled());
    }

    @Test
    void testSearchBarAction() {
        // Simulate user input
        SearchBar searchBar = new SearchBar(null);
        JTextField searchField = searchBar.getSearchField();
        searchField.setText("testQuery");

        assertEquals("testQuery", searchField.getText());
    }
}