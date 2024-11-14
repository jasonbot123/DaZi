package service;

import data_access.MongoPostDataAccessObject;
import entity.Post;
import entity.Section;
import org.bson.Document;

import java.time.LocalDateTime;

public class PostService {
    private MongoPostDataAccessObject postDao;

    public PostService(MongoPostDataAccessObject postDao) {
        this.postDao = postDao;
    }

    // add a new post
    public void addPost(String title, String content, String sectionName, String username) {
        // section is an enum
        Section section = Section.valueOf(sectionName.toUpperCase());
        Post post = new Post(title, content, section, username); // creates a new post with current timestamp
        postDao.addPost(post);
        System.out.println("Post added successfully!");
    }

    // get a post by title
    public Document getPostByTitle(String title) {
        Document postDoc = postDao.getPostByTitle(title);
        if (postDoc != null) {
            System.out.println("Post Found: " + postDoc.toJson());
        } else {
            System.out.println("Post not found.");
        }
        return postDoc;
    }

    // update a post's content
    public boolean updatePostContent(String title, String newContent) {
        boolean updated = postDao.updatePostContent(title, newContent);
        if (updated) {
            System.out.println("Post content updated successfully.");
        } else {
            System.out.println("Post not found or update failed.");
        }
        return updated;
    }

    // delete a post by title
    public boolean deletePostByTitle(String title) {
        boolean deleted = postDao.deletePostByTitle(title);
        if (deleted) {
            System.out.println("Post deleted successfully.");
        } else {
            System.out.println("Post not found or deletion failed.");
        }
        return deleted;
    }
}