package use_case.post;

import entity.Post;
import interface_adapter.posts.PostsViewModel;
import view.HomePageUI.PostsPanel;

import java.util.List;

public class PostsInteractor {
    private final PostDataAccessInterface postDAO;
    private final PostsViewModel viewModel;
    private final PostsPanel postsPanel;

    public PostsInteractor(PostDataAccessInterface postDAO, PostsViewModel viewModel, PostsPanel postsPanel) {
        this.postDAO = postDAO;
        this.viewModel = viewModel;
        this.postsPanel = postsPanel;
    }

    public void getThePosts(int pageSize) {
        viewModel.setLoading(true);
        new Thread(() -> {
            List<Post> posts = postDAO.getPostsByPage(viewModel.getPosts().size() / pageSize, pageSize);
            viewModel.addPosts(posts);
            postsPanel.updatePosts(viewModel.getPosts());
            viewModel.setLoading(false);
        }).start();
    }

    public void getPostsBySection(String section, int pageSize) {
        viewModel.setLoading(true);
        new Thread(() -> {
            try {
                List<Post> posts = postDAO.getPostsBySection(section, viewModel.getPosts().size() / pageSize, pageSize);
                viewModel.addPosts(posts);

                if (postsPanel != null) {
                    postsPanel.updatePosts(viewModel.getPosts());
                } else {
                    System.err.println("Error: postsPanel is null");
                }

            } catch (Exception e) {
                e.printStackTrace();
                viewModel.setErrorMessage("Failed to get posts for section: " + section);
            } finally {
                viewModel.setLoading(false);
            }
        }).start();
    }

    public void createPost(Post post) {
        try {
            postDAO.addPost(post);
            List<Post> updatedPosts = postDAO.getPostsBySection(post.getSection().toString(), 0, 10);
            viewModel.setPosts(updatedPosts);
        } catch (Exception e) {
            viewModel.setErrorMessage("Failed to create post");
        }
    }
}
