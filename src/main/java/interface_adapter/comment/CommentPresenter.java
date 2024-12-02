package interface_adapter.comment;

import use_case.comment.CommentOutputBoundary;
import use_case.comment.CommentOutputData;

public class CommentPresenter implements CommentOutputBoundary {
    private final CommentViewModel viewModel;

    public CommentPresenter(CommentViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(CommentOutputData data) {
        viewModel.setComments(data.getComments());
        viewModel.setError(null);
    }

    @Override
    public void prepareFailView(String error) {
        viewModel.setError(error);
    }
} 