package use_case.comment;

import entity.Comment;
import org.bson.types.ObjectId;
import java.util.List;

public interface CommentDataAccessInterface {
    void addComment(Comment comment);
    List<Comment> getCommentsForPost(ObjectId postId);
} 