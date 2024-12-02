package view.HomePageUI;

import interface_adapter.posts.PostsViewModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.post.PostsInteractor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PostsPanelTest {
    @Test
    void setInteractor_ShouldLoadInitialPosts() {
        // Setup
        MockitoAnnotations.openMocks(this);
        PostsInteractor mockInteractor = mock(PostsInteractor.class);
        PostsViewModel mockViewModel = mock(PostsViewModel.class);
        PostsPanel postsPanel = new PostsPanel("testUser", null, mockViewModel);

        // Call method
        postsPanel.setInteractor(mockInteractor);

        // Verify
        verify(mockInteractor).getThePosts(10);
    }
} 