package use_case.post;

import data_access.InMemoryPostDataAccessObject;
import entity.Post;
import entity.Section;
import interface_adapter.posts.PostsViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.HomePageUI.PostsPanel;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is the unit tests for the {@link PostsInteractor} class.
 * This test class verifies the behavior of creating posts and using the in-memory post data access object.
 * The tests ensure that posts are stored, retrieved, and behave as expected based on the design of the system.
 */
class PostsInteractorTest {
    private PostsInteractor interactor;
    private PostDataAccessInterface inMemoryDataAccess;
    private PostsViewModel viewModel;
    private PostsPanel postsPanel;
    private String username;

    @BeforeEach
    void setUp() {
        inMemoryDataAccess = new InMemoryPostDataAccessObject();
        String username = "user1";
        String sectionFilter = "STUDYING";

        viewModel = new PostsViewModel();
        postsPanel = new PostsPanel(username, sectionFilter,viewModel);
        interactor = new PostsInteractor(inMemoryDataAccess, viewModel,postsPanel);
    }

    /**
     * This tests if a valid post is successfully created and stored in the data access object.
     */
    @Test
    void testCreateValidPost() {
        Post validPost = new Post("Valid Title", "This is valid content.", Section.STUDYING, "user1", LocalDateTime.now());

        interactor.createPost(validPost);

        List<Post> posts = inMemoryDataAccess.getPostsByPage(0, 10);
        assertTrue(posts.contains(validPost), "The created post should be stored in the data access.");
    }


    /**
     * This tests if a post with a short title is stored in the data access object. (Edge case)
     * We decided to put this test because there is no validation for title length.
     */
    @Test
    void testCreatePostWithShortTitle() {
        Post invalidPost = new Post("No", "Content is valid.", Section.STUDYING, "user1", LocalDateTime.now());

        interactor.createPost(invalidPost);

        List<Post> posts = inMemoryDataAccess.getPostsByPage(0, 10);
        assertTrue(posts.contains(invalidPost), "Posts with short titles are stored since validation is not enforced.");
        // Optional: Verify that the stored post has the expected short title.
        Post storedPost = posts.get(posts.indexOf(invalidPost));
        assertEquals("No", storedPost.getTitle(), "The title of the stored post should match the short title.");
    }

    /**
     * This tests ensures a post with empty content is not stored in the database and can not be created because
     * all posts must have valid title and content.
     */
    @Test
    void testCreatePostWithEmptyContent() {
        String title = "No content for this post";
        String content = "";
        String username = "user1";
        Section section = Section.STUDYING;

        Post invalidPost = new Post(title, content, section, username, LocalDateTime.now());
        interactor.createPost(invalidPost);

        List<Post> posts = inMemoryDataAccess.getPostsByPage(0, 10);
        assertFalse(posts.contains(invalidPost), "Invalid posts should not be created and stored.");
        assertEquals("Failed to create post", viewModel.getErrorMessage(), "missing content.");
    }

    /**
     * This tests ensures a post with empty title is not stored in the database and can not be created because
     * all posts must have valid title and content.
     */
    @Test
    void testCreatePostWithEmptyTitle() {
        String title = "";
        String content = "Valid Content";
        String username = "user1";
        Section section = Section.STUDYING;

        Post invalidPost = new Post(title, content, section, username, LocalDateTime.now());
        interactor.createPost(invalidPost);

        List<Post> posts = inMemoryDataAccess.getPostsByPage(0, 10);

        assertFalse(posts.contains(invalidPost), "The post with an empty title should not be created and stored.");
        assertNotNull(viewModel.getErrorMessage(), "empty title.");
    }

    /**
     * This tests ensures a post with null title is not stored in the database and can not be created because
     * all posts must have valid title and content.
     */
    @Test
    void testCreatePostWithNullTitle() {
        String title = null;
        String content = "Should NOT be a valid post!";
        String username = "user1";
        Section section = Section.STUDYING;

        Post invalidPost = new Post(title, content, section, username, LocalDateTime.now());
        interactor.createPost(invalidPost);

        List<Post> posts = inMemoryDataAccess.getPostsByPage(0, 10);
        assertFalse(posts.contains(invalidPost), "Invalid posts should not be stored in the data access.");
        assertEquals("Failed to create post", viewModel.getErrorMessage(), "missing title.");
    }

    /**
     * This tests ensures a post with empty content is not stored in the database and can not be created because
     * all posts must have valid title and content.
     */
    @Test
    void testCreatePostWithNullContent() {
        String title = "Still not a post";
        String content = null;
        String username = "user1";
        Section section = Section.STUDYING;

        Post invalidPost = new Post(title, content, section, username, LocalDateTime.now());
        interactor.createPost(invalidPost);

        List<Post> posts = inMemoryDataAccess.getPostsByPage(0, 10);
        assertFalse(posts.contains(invalidPost), "Null content should not be stored or created.");
        assertEquals("Failed to create post", viewModel.getErrorMessage(), "missing content.");
    }
}