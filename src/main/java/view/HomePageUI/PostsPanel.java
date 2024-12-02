package view.HomePageUI;

import entity.Post;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import use_case.post.PostsInteractor;
import interface_adapter.posts.PostsViewModel;
import data_access.MongoLikeRecordDataAccessObject;
import data_access.MongoDBConnection;
import entity.LikeRecord;
import data_access.MongoPostDataAccessObject;

public class PostsPanel extends JPanel {
    private static final int PAGE_SIZE = 10;
    private final DefaultListModel<Post> postListModel = new DefaultListModel<>();
    private final JList<Post> postList = new JList<>(postListModel);
    private PostsInteractor interactor;
    private final PostsViewModel viewModel;
    private String sectionFilter;
    private String currentuser;
    private final MongoLikeRecordDataAccessObject likeRecordDAO;
    private final MongoPostDataAccessObject postDAO;

    public PostsPanel(String username, String sectionFilter, PostsViewModel viewModel) {
        this.viewModel = viewModel;
        this.sectionFilter = sectionFilter;
        this.currentuser = username;
        this.likeRecordDAO = new MongoLikeRecordDataAccessObject(MongoDBConnection.getDatabase("PostDataBase"));
        this.postDAO = new MongoPostDataAccessObject(MongoDBConnection.getDatabase("PostDataBase"));

        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        postList.setCellRenderer(new PostCellRenderer());
        postList.setFixedCellHeight(150);
        postList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        postList.setBackground(Color.WHITE);
        postList.setSelectionBackground(new Color(240, 240, 240));
        postList.setBorder(null);

        JScrollPane scrollPane = new JScrollPane(postList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        scrollPane.getVerticalScrollBar().addAdjustmentListener(e -> {
            JScrollBar scrollBar = (JScrollBar) e.getAdjustable();
            int extent = scrollBar.getModel().getExtent();
            int maximum = scrollBar.getModel().getMaximum();
            int value = e.getValue();

            if (!viewModel.isLoading() && value + extent > maximum - 50) {
                // System.out.println("get posts for section: " + sectionFilter);
                if (sectionFilter != null) {
                    viewModel.setLoading(true);
                    interactor.getPostsBySection(sectionFilter, PAGE_SIZE);
                } else {
                    viewModel.setLoading(true);
                    interactor.getThePosts(PAGE_SIZE);
                }
            }
        });

        postList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = postList.locationToIndex(evt.getPoint());
                if (index != -1) {
                    Post post = postListModel.getElementAt(index);
                    Rectangle cellBounds = postList.getCellBounds(index, index);
                    Point clickPoint = new Point(evt.getPoint().x - cellBounds.x,
                            evt.getPoint().y - cellBounds.y);

                    int bottomPanelY = cellBounds.height - 65;
                    int likeX = cellBounds.width - 270;
                    int commentX = cellBounds.width - 150;
                    int buttonHeight = 60;

                    if (clickPoint.y >= bottomPanelY && clickPoint.y <= bottomPanelY + buttonHeight) {
                        if (clickPoint.x >= likeX && clickPoint.x < commentX - 10) {
                            handleLikeClick(post);
                        }
                        else if (clickPoint.x >= commentX) {
                            CommentPage commentPage = new CommentPage(post, currentuser);
                            commentPage.setVisible(true);
                        }
                    }
                }
            }
        });
    }

    public void updatePosts(List<Post> posts) {
        SwingUtilities.invokeLater(() -> {
            postListModel.clear(); // Clear the old posts
            for (Post post : posts) {
                postListModel.addElement(post);
            }
            postList.repaint(); // refresh  UI
        });
    }

    public void setInteractor(PostsInteractor interactor) {
        this.interactor = interactor;
        if (sectionFilter != null) {
            interactor.getPostsBySection(sectionFilter, 10);
        } else {
            interactor.getThePosts(10);
        }
    }
    public void updateSectionFilter(String sectionFilter) {
        this.sectionFilter = sectionFilter;
        if (interactor != null) {
            loadPostsForCurrentSection();
        }
    }

    // helper
    private void loadPostsForCurrentSection() {
        if (sectionFilter != null) {
            interactor.getPostsBySection(sectionFilter, PAGE_SIZE);
        } else {
            interactor.getThePosts(PAGE_SIZE);
        }
    }

    private void handleLikeClick(Post post) {
        boolean hasLiked = likeRecordDAO.hasUserLiked(currentuser, post.getId().toString());
        
        if (!hasLiked) {
            post.setLikes(post.getLikes() + 1);
            likeRecordDAO.addLikeRecord(new LikeRecord(currentuser, post.getId().toString()));
            postDAO.updateLikes(post.getId(), post.getLikes());
        } else {
            post.setLikes(Math.max(0, post.getLikes() - 1));
            likeRecordDAO.removeLikeRecord(currentuser, post.getId().toString());
            postDAO.updateLikes(post.getId(), post.getLikes());
        }
        
        postList.repaint();
    }

}
