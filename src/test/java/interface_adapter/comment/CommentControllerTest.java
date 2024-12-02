package interface_adapter.comment;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.comment.CommentInputBoundary;
import use_case.comment.CommentInputData;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class CommentControllerTest {
    @Mock
    private CommentInputBoundary mockCommentInteractor;
    private CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commentController = new CommentController(mockCommentInteractor);
    }

    @Test
    void addComment_ShouldCallInteractor() {
        // Prepare test data
        ObjectId postId = new ObjectId();
        String content = "Test comment";
        String username = "Test user";

        // Execute method
        commentController.addComment(postId, content, username);

        // Verify interactor was called correctly
        verify(mockCommentInteractor).addComment(any(CommentInputData.class));
    }

    @Test
    void loadComments_ShouldCallInteractor() {
        // Prepare test data
        ObjectId postId = new ObjectId();

        // Execute method
        commentController.loadComments(postId);

        // Verify interactor was called correctly
        verify(mockCommentInteractor).loadComments(postId);
    }
} 