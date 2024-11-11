package use_case.post;

import entity.Section;

// creating a new post.
public class CreateAPost {
    private ManagePost managePost;

    public CreateAPost(ManagePost managePost) {
        this.managePost = managePost;
    }

    // check the title and content before put into storage
    // both should be not mull and not empty
    private String validate(String title, String content) {
        if (title == null || title.trim().isEmpty()) {
            return "Title cannot be empty.";
        }
        if (content == null || content.trim().isEmpty()) {
            return "Content cannot be empty.";
        }
        return null;
    }

    // create a new post before validation, then add the post if valid.
    public String execute(String title, String content, Section section, String username) { //TODO: username
        String validationMsg = validate(title, content);

        if (validationMsg == null) {
            managePost.addPost(title, content, section, username);
            return null;
        } else {
            return validationMsg; // return error message
        }
    }

}
