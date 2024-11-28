package data_access;

import entity.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryPostDataAccessObject {
    private List<Post> posts = new ArrayList<>();

    // Simulate adding a post to the "database"
    public void addPost(Post post) {
        posts.add(post);
    }

    // Simulate fetching posts by section
    public List<Post> getPostsBySection(String section, int page, int pageSize) {
        return posts.stream()
                .filter(post -> post.getSection().toString().equalsIgnoreCase(section))
                .skip(page * pageSize)
                .limit(pageSize)
                .toList();
    }

    public List<Post> searchPostsByTitle(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>(); // Return empty list for empty or null keyword
        }
        // Filter posts whose title contains the keyword (case-insensitive)
        return posts.stream()
                .filter(post -> post.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
