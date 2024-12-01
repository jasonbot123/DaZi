package use_case.comment;

import entity.Comment;
import java.util.List;

public class CommentOutputData {
    private final List<Comment> comments;
    private final boolean success;
    private final String message;

    public CommentOutputData(List<Comment> comments, boolean success, String message) {
        this.comments = comments;
        this.success = success;
        this.message = message;
    }

    public List<Comment> getComments() { return comments; }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
} 