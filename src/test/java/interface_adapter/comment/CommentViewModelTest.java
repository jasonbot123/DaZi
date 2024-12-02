package interface_adapter.comment;

import entity.Comment;
import org.junit.jupiter.api.Test;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

class CommentViewModelTest {

    

    @Test
    void setError_ShouldNotifyListener() {
        // Setup
        CommentViewModel viewModel = new CommentViewModel();
        PropertyChangeListener listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);

        // Call method
        viewModel.setError("Error");

        // Verify
        verify(listener).propertyChange(any());
    }
} 