package use_case.comment;

public interface CommentOutputBoundary {
    void prepareSuccessView(CommentOutputData data);
    void prepareFailView(String error);
} 