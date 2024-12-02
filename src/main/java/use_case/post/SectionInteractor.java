package use_case.post;

import entity.Post;
import interface_adapter.posts.SectionViewModel;

import java.util.List;

public class SectionInteractor {
    private final PostDataAccessInterface dataAccess;
    private final SectionViewModel viewModel;

    public SectionInteractor(PostDataAccessInterface dataAccess, SectionViewModel viewModel) {
        this.dataAccess = dataAccess;
        this.viewModel = viewModel;
    }

    public void loadPostsBySection(String section) {
        if (section == null || section.isEmpty()) {
            viewModel.setErrorMessage("Section cannot be empty.");
            return;
        }
        List<Post> posts = dataAccess.getPostsBySection(section, 0, 10);
        if (posts.isEmpty()) {
            viewModel.setErrorMessage("No posts available for this section.");
        } else {
            viewModel.setPosts(posts);
        }
    }
}
