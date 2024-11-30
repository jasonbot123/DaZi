package use_case.search;

import data_access.InMemoryPostDataAccessObject;
import entity.Post;
import entity.Section;
import interface_adapter.search.SearchPresenter;
import org.junit.jupiter.api.BeforeEach;
import use_case.search.SearchInteractor;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchInteractorTest {

    private InMemoryPostDataAccessObject dao;

    @BeforeEach
    void setup() {
        dao = new InMemoryPostDataAccessObject();
        dao.addPost(new Post("Study1", "hello", Section.STUDYING, "user1", LocalDateTime.now()));
        dao.addPost(new Post("Study2", "hello", Section.STUDYING, "user2", LocalDateTime.now()));
        dao.addPost(new Post("Gaming1", "246810", Section.GAMING, "user3", LocalDateTime.now()));
        dao.addPost(new Post("Gaming2", "hi", Section.GAMING, "user4", LocalDateTime.now()));
        dao.addPost(new Post("Dining1", "123", Section.DINING, "user5", LocalDateTime.now()));
        dao.addPost(new Post("Other0", "something", Section.OTHERS, "user6", LocalDateTime.now()));

    }

    @Test
    void testGetPostsBySection() {
        // Fetch posts by section
        List<Post> studyingPosts = dao.getPostsBySection("STUDYING", 0, 10);
        assertEquals(2, studyingPosts.size());
        assertEquals("Study1", studyingPosts.get(0).getTitle());
        assertEquals("Study2", studyingPosts.get(1).getTitle());

        List<Post> gamingPosts = dao.getPostsBySection("GAMING", 0, 10);
        assertEquals(2, gamingPosts.size());
        assertEquals("Gaming1", gamingPosts.get(0).getTitle());
        assertEquals("Gaming2", gamingPosts.get(1).getTitle());

        List<Post> otherPosts = dao.getPostsBySection("DINING", 0, 10);
        assertEquals(1, otherPosts.size());
        assertEquals("Dining1", otherPosts.get(0).getTitle());
    }


    @Test
    public void testSearchPostsByTitle(){
        List<Post> results = dao.searchPostsByTitle("Gaming");
        assertEquals(2, results.size());
        assertEquals("Gaming2", results.get(1).getTitle());

        results = dao.searchPostsByTitle("Does not exist");
        assertEquals(0, results.size());

        results = dao.searchPostsByTitle("Study");
        assertEquals(2, results.size());
    }
}
