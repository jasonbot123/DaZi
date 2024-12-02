package interface_adapter.comment;

import entity.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.comment.CommentOutputData;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

class CommentPresenterTest {
    @Mock
    private CommentViewModel mockViewModel;
    private CommentPresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new CommentPresenter(mockViewModel);
    }

    @Test
    void prepareSuccessView_ShouldUpdateViewModel() {
        // Test data
        List<Comment> comments = new ArrayList<>();
        CommentOutputData outputData = new CommentOutputData(comments, true, "Success");

        // Call method
        presenter.prepareSuccessView(outputData);

        // Verify
        verify(mockViewModel).setComments(comments);
        verify(mockViewModel).setError(null);
    }

    @Test
    void prepareFailView_ShouldUpdateViewModel() {
        // Call method
        presenter.prepareFailView("Error");

        // Verify
        verify(mockViewModel).setError("Error");
    }
} 