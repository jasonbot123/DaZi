package data_access;

import entity.Post;
import entity.Section;
import use_case.post.PostDataAccessInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryPostDataAccessObject implements PostDataAccessInterface {
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        if (post.getTitle() == null || post.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Post title cannot be null or empty.");
        }
        if (post.getContent() == null || post.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Post content cannot be null or empty.");
        }
        if (post.getSection() == null) {
            throw new IllegalArgumentException("Post section cannot be null.");
        }

        posts.add(post);
    }

    public List<Post> getPostsBySection(String section, int page, int pageSize) {
        return posts.stream()
                .filter(post -> post.getSection().toString().equalsIgnoreCase(section))
                .skip(page * pageSize)
                .limit(pageSize)
                .toList();
    }

    @Override
    public List<Post> getPostsByPage(int page, int pageSize) {
        int start = page * pageSize;
        int end = Math.min(start + pageSize, posts.size());
        if (start >= posts.size()) {
            return new ArrayList<>();
        }
        return posts.subList(start, end);
    }

    public List<Post> searchPostsByTitle(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>(); // Return empty list for empty or null keyword
        }
        return posts.stream()
                .filter(post -> post.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
