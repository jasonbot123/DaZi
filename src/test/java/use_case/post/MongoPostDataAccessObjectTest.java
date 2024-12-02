package use_case.post;

import data_access.InMemoryPostDataAccessObject;
import entity.Post;
import entity.Section;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryPostDataAccessObjectTest {

    @Test
    void testGetPostsBySection() {
        // in memory DAO
        InMemoryPostDataAccessObject dao = new InMemoryPostDataAccessObject();

        dao.addPost(new Post("Post 1", "Content 1", Section.STUDYING, "user1", LocalDateTime.now()));
        dao.addPost(new Post("Post 2", "Content 2", Section.STUDYING, "user2", LocalDateTime.now()));
        dao.addPost(new Post("Post 3", "Content 3", Section.GAMING, "user3", LocalDateTime.now()));

        List<Post> studyingPosts = dao.getPostsBySection("STUDYING", 0, 10);
        assertEquals(2, studyingPosts.size());
        assertEquals("Post 1", studyingPosts.get(0).getTitle());
        assertEquals("Post 2", studyingPosts.get(1).getTitle());

        List<Post> gamingPosts = dao.getPostsBySection("GAMING", 0, 10);
        assertEquals(1, gamingPosts.size());
        assertEquals("Post 3", gamingPosts.get(0).getTitle());

        List<Post> otherPosts = dao.getPostsBySection("DINING", 0, 10);
        assertEquals(0, otherPosts.size());
    }

    @Test
    void testPages() {
        InMemoryPostDataAccessObject dao = new InMemoryPostDataAccessObject();

        // test multiple tests
        for (int i = 1; i <= 15; i++) {
            dao.addPost(new Post("Post " + i, "Content " + i, Section.STUDYING, "user" + i, LocalDateTime.now()));
        }

        List<Post> firstPage = dao.getPostsBySection("STUDYING", 0, 5);
        assertEquals(5, firstPage.size());
        assertEquals("Post 1", firstPage.get(0).getTitle());
        assertEquals("Post 5", firstPage.get(4).getTitle());


        List<Post> secondPage = dao.getPostsBySection("STUDYING", 1, 5);
        assertEquals(5, secondPage.size());
        assertEquals("Post 6", secondPage.get(0).getTitle());
        assertEquals("Post 10", secondPage.get(4).getTitle());


        List<Post> thirdPage = dao.getPostsBySection("STUDYING", 2, 5);
        assertEquals(5, thirdPage.size());
        assertEquals("Post 11", thirdPage.get(0).getTitle());
        assertEquals("Post 15", thirdPage.get(4).getTitle());

        // Test fourth page (no results)
        List<Post> fourthPage = dao.getPostsBySection("STUDYING", 3, 5);
        assertEquals(0, fourthPage.size());
    }
}