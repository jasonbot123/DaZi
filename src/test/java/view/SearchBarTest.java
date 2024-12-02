package view;

import data_access.InMemoryPostDataAccessObject;
import entity.Post;
import entity.Section;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchBarTest {

    @Test
    void testSearchPostsByKeyword() {
        // in-memory DAO
        InMemoryPostDataAccessObject dao = new InMemoryPostDataAccessObject();

        dao.addPost(new Post("Gaming title", "123", Section.GAMING, "user1", LocalDateTime.now()));
        dao.addPost(new Post("Study title", "456", Section.STUDYING, "user2", LocalDateTime.now()));
        dao.addPost(new Post("Gaming title2", "789", Section.GAMING, "user3", LocalDateTime.now()));

        List<Post> gamingPosts = dao.searchPostsByTitle("Gaming");
        assertEquals(2, gamingPosts.size());
        assertEquals("Gaming title", gamingPosts.get(0).getTitle());
        assertEquals("Gaming title2", gamingPosts.get(1).getTitle());

        List<Post> studyPosts = dao.searchPostsByTitle("Study");
        assertEquals(1, studyPosts.size());
        assertEquals("Study title", studyPosts.get(0).getTitle());

        List<Post> noPosts = dao.searchPostsByTitle("nothing");
        assertEquals(0, noPosts.size());
    }

    @Test
    void testSearchWithEmptyKeyword() {
        InMemoryPostDataAccessObject dao = new InMemoryPostDataAccessObject();

        dao.addPost(new Post("Post 1", "Content 1", Section.OTHERS, "user1", LocalDateTime.now()));
        dao.addPost(new Post("Post 2", "Content 2", Section.GAMING, "user2", LocalDateTime.now()));

        List<Post> posts = dao.searchPostsByTitle("");
        assertEquals(0, posts.size()); // no results for an empty search
    }
}