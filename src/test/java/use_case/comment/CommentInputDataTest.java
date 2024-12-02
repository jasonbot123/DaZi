package use_case.comment;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommentInputDataTest {
    @Test
    void constructor_ShouldInitializeAllFields() {
        ObjectId postId = new ObjectId();
        String content = "Test comment";
        String username = "testUser";
        
        CommentInputData inputData = new CommentInputData(postId, content, username);
        
        assertEquals(postId, inputData.getPostId());
        assertEquals(content, inputData.getContent());
        assertEquals(username, inputData.getUsername());
    }
} 