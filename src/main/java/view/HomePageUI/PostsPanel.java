package view.HomePageUI;

import entity.Post;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import use_case.post.PostsInteractor;
import interface_adapter.posts.PostsViewModel;

public class PostsPanel extends JPanel {
    private static final int PAGE_SIZE = 10;
    private final DefaultListModel<Post> postListModel = new DefaultListModel<>();
    private final JList<Post> postList = new JList<>(postListModel);
    private PostsInteractor interactor;
    private final PostsViewModel viewModel;
    private String sectionFilter;
    private String currentUsername;

    public PostsPanel(String username, String sectionFilter, PostsViewModel viewModel) {
        System.out.println("PostsPanel initialized");
        this.viewModel = viewModel;
        this.sectionFilter = sectionFilter;
        this.currentUsername = username;

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

        // 禁用默认的列表选择行为
        postList.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                super.setSelectionInterval(-1, -1);
            }
        });

        // 添加鼠标监听器
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                System.out.println("Mouse pressed"); // 调试信息
                int index = postList.locationToIndex(evt.getPoint());
                if (index != -1) {
                    Post post = postListModel.getElementAt(index);
                    Rectangle cellBounds = postList.getCellBounds(index, index);
                    
                    if (cellBounds != null) {
                        Point clickPoint = new Point(evt.getPoint().x - cellBounds.x,
                                evt.getPoint().y - cellBounds.y);

                        System.out.println("Click coordinates: " + clickPoint.x + ", " + clickPoint.y);
                        System.out.println("Cell bounds: " + cellBounds);
                        
                        // 调整点击区域的计算
                        int bottomPanelY = cellBounds.height - 35;  // 略微调整点击区域
                        int commentX = cellBounds.width - 110;      // 扩大点击区域
                        int commentWidth = 100;                     // 设置点击区域宽度
                        
                        System.out.println("Bottom panel Y: " + bottomPanelY);
                        System.out.println("Comment X: " + commentX);

                        if (clickPoint.y >= bottomPanelY && clickPoint.y <= cellBounds.height) {
                            if (clickPoint.x >= commentX && clickPoint.x <= commentX + commentWidth) {
                                System.out.println("Comment button clicked!");
                                handleCommentClick(post);
                                evt.consume(); // 阻止事件继续传播
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                System.out.println("Mouse entered list"); // 调试信息
                postList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                System.out.println("Mouse exited list"); // 调试信息
                postList.setCursor(Cursor.getDefaultCursor());
            }
        };

        // 同时添加到 JList 和 ScrollPane
        postList.addMouseListener(mouseAdapter);
        postList.addMouseMotionListener(mouseAdapter);

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

    private void handleCommentClick(Post post) {
        System.out.println("Opening comment page for post: " + post.getTitle());
        System.out.println("Current username: " + currentUsername);
        CommentPage commentPage = new CommentPage(post, currentUsername);
        commentPage.setVisible(true);
    }

    // 添加 PostCellRenderer 内部类
    private static class PostCellRenderer extends JPanel implements ListCellRenderer<Post> {
        private final JLabel titleLabel;
        private final JLabel contentLabel;
        private final JLabel userLabel;
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
                    BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)),
                    BorderFactory.createEmptyBorder(0, 0, 10, 0)
            ));

            contentLabel = new JLabel();
            contentLabel.setVerticalAlignment(JLabel.TOP);
            contentLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

            userLabel = new JLabel();
            userLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
            userLabel.setForeground(new Color(100, 100, 100));

            commentLabel = new JLabel("Comments");
            commentLabel.setHorizontalAlignment(SwingConstants.CENTER);
            commentLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
            commentLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            commentLabel.setOpaque(true);
            commentLabel.setBackground(new Color(245, 245, 245));
            commentLabel.setForeground(new Color(0, 102, 204));

            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            
            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            buttonsPanel.setOpaque(false);
            buttonsPanel.add(commentLabel);

            bottomPanel.setOpaque(false);
            bottomPanel.add(userLabel, BorderLayout.WEST);
            bottomPanel.add(buttonsPanel, BorderLayout.EAST);

            JPanel contentPanel = new JPanel(new BorderLayout(5, 10));
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

            if (isSelected) {
                setBackground(new Color(240, 240, 240));
                commentLabel.setBackground(new Color(235, 235, 235));
            } else {
                setBackground(Color.WHITE);
                commentLabel.setBackground(new Color(245, 245, 245));
            }

            return this;
        }
    }

}
