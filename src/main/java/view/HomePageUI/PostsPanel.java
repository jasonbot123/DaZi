package view.HomePageUI;

import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;
import data_access.MongoLikeRecordDataAccessObject;
import entity.Post;
import entity.LikeRecord;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PostsPanel extends JPanel {
    private static final int PAGE_SIZE = 10;
    private int currentPage = 0;
    private boolean isLoading = false;
    private final DefaultListModel<Post> postListModel;
    private JList<Post> postList;
    private MongoPostDataAccessObject postDAO;
    private MongoLikeRecordDataAccessObject likeRecordDAO;
    private String currentUsername;
    private String sectionFilter;

    public PostsPanel(String username, String sectionFilter) {
        this.currentUsername = username;
        this.sectionFilter = sectionFilter;

        setLayout(new BorderLayout());

        postDAO = new MongoPostDataAccessObject(MongoDBConnection.getDatabase("posts"));
        likeRecordDAO = new MongoLikeRecordDataAccessObject(MongoDBConnection.getDatabase("posts"));

        postListModel = new DefaultListModel<>();
        postList = new JList<>(postListModel);


        postList.setCellRenderer(new PostCellRenderer());

        postList.setFixedCellHeight(150);

        postList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        postList.setBackground(Color.WHITE);
        postList.setSelectionBackground(new Color(240, 240, 240));
        postList.setBorder(null);

        // Adjust click region calculation
        JScrollPane scrollPane = new JScrollPane(postList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(scrollPane, BorderLayout.CENTER);
        if (sectionFilter != null) {
            loadPostsBySection(sectionFilter);
        } else {
            loadMorePosts();
        }

        scrollPane.getVerticalScrollBar().addAdjustmentListener(e -> {
            JScrollBar scrollBar = (JScrollBar) e.getAdjustable();
            int extent = scrollBar.getModel().getExtent();
            int maximum = scrollBar.getModel().getMaximum();
            int value = e.getValue();

            if (!isLoading && value + extent > maximum - 50) { // Near the bottom
                if (sectionFilter != null) {
                    // System.out.println("loadPostsBySection for section: " + sectionFilter);
                    loadPostsBySection(sectionFilter);
                } else {
                    loadMorePosts();
                }
            }
        });

        addListListeners();

        /*
        scrollPane.getVerticalScrollBar().addAdjustmentListener(e -> {
            JScrollBar scrollBar = (JScrollBar) e.getAdjustable();
            int extent = scrollBar.getModel().getExtent();
            int maximum = scrollBar.getModel().getMaximum();
            int value = e.getValue();

            if (!isLoading && value + extent > maximum - 50) { // 近底部时加载更多
                loadMorePosts();
            }
        });

        if (sectionFilter != null) {
            loadPostsBySection(sectionFilter);
        } else {
            loadMorePosts();
        }

        addListListeners();

         */
    }

    private void loadMorePosts() {
        if (isLoading) return;
        isLoading = true;

        SwingWorker<List<Post>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Post> doInBackground() {
                return postDAO.getPostsByPage(currentPage, PAGE_SIZE);
            }

            @Override
            protected void done() {
                try {
                    List<Post> posts = get();
                    if (!posts.isEmpty()) {
                        for (Post post : posts) {
                            postListModel.addElement(post);
                        }
                        currentPage++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    isLoading = false;
                }
            }
        };
        worker.execute();
    }

    // helper, for different section's post loading
    public void loadPostsBySection(String section) {
        // System.out.println("Loading posts for: " + section);
        sectionFilter = section;
        currentPage = 0;
        postListModel.clear();
        isLoading = true;

        SwingWorker<List<Post>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Post> doInBackground() {
                return postDAO.getPostsBySection(sectionFilter, currentPage, PAGE_SIZE);
            }

            @Override
            protected void done() {
                try {
                    List<Post> posts = get();

                    for (Post post : posts) {
                        if (!postListModel.contains(post)) { // Check for duplicates
                            postListModel.addElement(post);
                        }
                    }
                    currentPage++;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    isLoading = false;
                }
            }
        };

        worker.execute();
    }

    private void loadPostsFromDatabase() {
        currentPage = 0;
        postListModel.clear();
        loadMorePosts();
    }

    public void addPost(Post post) {
        // add post to the UI only
        if (sectionFilter == null || post.getSection().toString().equalsIgnoreCase(sectionFilter)) {
            SwingUtilities.invokeLater(() -> {
                if (!postListModel.contains(post)) {
                    postListModel.addElement(post);
                }
            });
        }
    }

    private class PostCellRenderer extends JPanel implements ListCellRenderer<Post> {
        private final JLabel titleLabel;
        private final JLabel contentLabel;
        private final JLabel userLabel;
        private final JLabel likeLabel;
        private final JLabel commentLabel;

        public PostCellRenderer() {
            setLayout(new BorderLayout(5, 5));

            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(5, 5, 5, 5),
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                            BorderFactory.createEmptyBorder(10, 10, 10, 10)
                    )
            ));

            titleLabel = new JLabel();
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

            titleLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)), // 底部边框线
                    BorderFactory.createEmptyBorder(0, 0, 10, 0)
            ));

            contentLabel = new JLabel();
            contentLabel.setVerticalAlignment(JLabel.TOP);
            contentLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

            userLabel = new JLabel();
            userLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
            userLabel.setForeground(new Color(100, 100, 100));

            likeLabel = new JLabel();
            likeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            likeLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
            likeLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            likeLabel.setOpaque(true);
            likeLabel.setBackground(Color.WHITE);

            commentLabel = new JLabel("Comments");
            commentLabel.setHorizontalAlignment(SwingConstants.CENTER);
            commentLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
            commentLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            commentLabel.setOpaque(true);
            commentLabel.setBackground(Color.WHITE);

            JPanel bottomPanel = new JPanel(new BorderLayout());
            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            buttonsPanel.setOpaque(false);

            buttonsPanel.add(likeLabel);
            buttonsPanel.add(commentLabel);

            bottomPanel.setOpaque(false);
            bottomPanel.add(userLabel, BorderLayout.WEST);
            bottomPanel.add(buttonsPanel, BorderLayout.EAST);

            JPanel contentPanel = new JPanel(new BorderLayout(5, 10)); // 增加垂直间距
            contentPanel.setOpaque(false);
            contentPanel.add(titleLabel, BorderLayout.NORTH);
            contentPanel.add(contentLabel, BorderLayout.CENTER);

            add(contentPanel, BorderLayout.CENTER);
            add(bottomPanel, BorderLayout.SOUTH);

            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Post> list,
                                                      Post post,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            titleLabel.setText(post.getTitle());
            contentLabel.setText("<html><body style='width: 300px'>" +
                    post.getTruncatedContent(200) + "</html>");
            userLabel.setText("Posted by: " + post.getUsername());

            likeLabel.setText("❤ " + post.getLikes());
            likeLabel.setForeground(new Color(128, 128, 128)); // 统一使用灰色
            likeLabel.setBackground(Color.WHITE);
            likeLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));

            if (isSelected) {
                setBackground(new Color(240, 240, 240));
                likeLabel.setBackground(new Color(245, 245, 245));
                commentLabel.setBackground(new Color(245, 245, 245));
            } else {
                setBackground(Color.WHITE);
                commentLabel.setBackground(Color.WHITE);
            }

            return this;
        }
    }

    private void addListListeners() {
        postList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = postList.locationToIndex(evt.getPoint());
                if (index != -1) {
                    Post post = postListModel.getElementAt(index);
                    Rectangle cellBounds = postList.getCellBounds(index, index);
                    Point clickPoint = new Point(evt.getPoint().x - cellBounds.x,
                            evt.getPoint().y - cellBounds.y);

                    int bottomPanelY = cellBounds.height - 40; // 增加可点击区域
                    int likeX = cellBounds.width - 180; // 调整位置
                    int commentX = cellBounds.width - 100;

                    if (clickPoint.y >= bottomPanelY && clickPoint.y <= bottomPanelY + 30) {
                        if (clickPoint.x >= likeX && clickPoint.x < likeX + 70) {
                            handleLikeClick(post);
                        } else if (clickPoint.x >= commentX && clickPoint.x < commentX + 90) {
                            handleCommentClick(post);
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                postList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                postList.setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    private void handleLikeClick(Post post) {
        boolean hasLiked = likeRecordDAO.hasUserLiked(currentUsername, post.getId().toString());

        if (!hasLiked) {
            post.setLikes(post.getLikes() + 1);
            likeRecordDAO.addLikeRecord(new LikeRecord(currentUsername, post.getId().toString()));
        } else {
            post.setLikes(Math.max(0, post.getLikes() - 1));
            likeRecordDAO.removeLikeRecord(currentUsername, post.getId().toString());
        }
        postDAO.updateLikes(post.getId(), post.getLikes());

        SwingUtilities.invokeLater(() -> {
            postList.repaint();
        });
    }

    private void handleCommentClick(Post post) {
        CommentPage commentPage = new CommentPage(post);
        commentPage.setVisible(true);
    }
}