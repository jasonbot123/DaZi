package use_case.comment;

import org.bson.types.ObjectId;

public interface CommentInputBoundary {
    void addComment(CommentInputData commentInputData);
    void loadComments(ObjectId postId);
} 