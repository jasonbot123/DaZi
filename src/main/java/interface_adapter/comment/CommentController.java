package interface_adapter.comment;

import org.bson.types.ObjectId;
import use_case.comment.CommentInputBoundary;
import use_case.comment.CommentInputData;

public class CommentController {
    private final CommentInputBoundary commentInteractor;

    public CommentController(CommentInputBoundary commentInteractor) {
        this.commentInteractor = commentInteractor;
    }

    public void addComment(ObjectId postId, String content, String username) {
        CommentInputData inputData = new CommentInputData(postId, content, username);
        commentInteractor.addComment(inputData);
    }

    public void loadComments(ObjectId postId) {
        commentInteractor.loadComments(postId);
    }
} 