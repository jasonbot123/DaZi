package use_case.comment;

import entity.Comment;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;
import java.util.List;

public class CommentInteractor implements CommentInputBoundary {
    private final CommentDataAccessInterface commentDAO;
    private final CommentOutputBoundary commentPresenter;

    public CommentInteractor(CommentDataAccessInterface commentDAO, 
                           CommentOutputBoundary commentPresenter) {
        this.commentDAO = commentDAO;
        this.commentPresenter = commentPresenter;
    }

    @Override
    public void addComment(CommentInputData inputData) {
        try {
            Comment comment = new Comment(
                inputData.getPostId(),
                inputData.getContent(),
                inputData.getUsername(),
                LocalDateTime.now()
            );
            commentDAO.addComment(comment);
            List<Comment> updatedComments = commentDAO.getCommentsForPost(inputData.getPostId());
            commentPresenter.prepareSuccessView(new CommentOutputData(updatedComments, true, "Comment added successfully"));
        } catch (Exception e) {
            commentPresenter.prepareFailView("Failed to add comment: " + e.getMessage());
        }
    }

    @Override
    public void loadComments(ObjectId postId) {
        try {
            List<Comment> comments = commentDAO.getCommentsForPost(postId);
            commentPresenter.prepareSuccessView(new CommentOutputData(comments, true, "Comments loaded successfully"));
        } catch (Exception e) {
            commentPresenter.prepareFailView("Failed to load comments: " + e.getMessage());
        }
    }
} 