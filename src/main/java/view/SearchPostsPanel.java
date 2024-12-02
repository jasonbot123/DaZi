package view;

import entity.Post;
import interface_adapter.search.SearchViewModel;
import view.HomePageUI.PostCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import use_case.post.PostsInteractor;
import interface_adapter.posts.PostsViewModel;

public class SearchPostsPanel extends JPanel {
    private static final int PAGE_SIZE = 10;
    private final DefaultListModel<Post> postListModel = new DefaultListModel<>();
    private final JList<Post> postList = new JList<>(postListModel);
    private final SearchViewModel viewModel;

    public SearchPostsPanel(SearchViewModel viewModel) {
        this.viewModel = viewModel;
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
    }

    public void updateSearchResults(List<Post> searchResults) {
        SwingUtilities.invokeLater(() -> {
            postListModel.clear();
            for (Post post : searchResults) {
                postListModel.addElement(post);
            }
            postList.repaint();
        });
    }

}