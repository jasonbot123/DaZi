package view.HomePageUI;

import data_access.MongoDBConnection;
import data_access.MongoPostDataAccessObject;
import entity.Post;
import interface_adapter.search.SearchViewModel;
import use_case.search.SearchInteractor;
import view.SearchPageUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class SearchBar extends JPanel {
    private JTextField searchField;
    private JButton searchButton;

    public SearchBar(JFrame parentFrame, SearchInteractor searchInteractor, SearchViewModel viewModel) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        searchField = new JTextField("What are you looking for...");
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 16));

        searchButton = new JButton("🔍");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            searchInteractor.searchPostsByTitle(keyword);

            if (viewModel.getErrorMessage() != null) {
                JOptionPane.showMessageDialog(this,
                        viewModel.getErrorMessage(),
                        "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                new SearchPageUI(parentFrame, viewModel);
                parentFrame.dispose();
            }
        });

        add(searchField, BorderLayout.CENTER);
        add(searchButton, BorderLayout.EAST);
    }
}