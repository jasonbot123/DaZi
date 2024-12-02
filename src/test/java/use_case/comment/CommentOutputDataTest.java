package use_case.comment;

import entity.Comment;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CommentOutputDataTest {
    @Test
    void constructor_ShouldInitializeAllFields() {
        List<Comment> comments = new ArrayList<>();
        boolean success = true;
        String message = "Test message";
        
        CommentOutputData outputData = new CommentOutputData(comments, success, message);
        
        assertEquals(comments, outputData.getComments());
        assertTrue(outputData.isSuccess());
        assertEquals(message, outputData.getMessage());
    }
} 