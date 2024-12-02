package interface_adapter.comment;

import entity.Comment;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class CommentViewModel {
    private List<Comment> comments = new ArrayList<>();
    private String error = null;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void setComments(List<Comment> comments) {
        List<Comment> oldComments = this.comments;
        this.comments = comments;
        support.firePropertyChange("comments", oldComments, comments);
    }

    public void setError(String error) {
        String oldError = this.error;
        this.error = error;
        support.firePropertyChange("error", oldError, error);
    }

    public List<Comment> getComments() { return comments; }
    public String getError() { return error; }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
} 