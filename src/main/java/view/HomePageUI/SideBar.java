package view.HomePageUI;

import interface_adapter.posts.PostsViewModel;
import view.SectionPageUI.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SideBar extends JPanel {
    private final PostsPanel postsPanel;
    private final PostsViewModel viewModel;

    public SideBar(String currentUsername, PostsPanel postsPanel, PostsViewModel viewModel) {
        this.postsPanel = postsPanel;
        this.viewModel = viewModel;

        setLayout(new BorderLayout());
        setBackground(new Color(0, 51, 102));

        // menu section
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(0, 51, 102));

        // emojis and buttons
        String[] emojis = {
                "📜", "📚", "🎮", "🍕", "🤝", "🌀"
        };
        List<String> buttonLabels = List.of(
                "Latest Post",
                "Studying",
                "Gaming",
                "Dining",
                "Hanging Out",
                "Others"
        );

        for (int i = 0; i < buttonLabels.size(); i++) {
            String buttonLabel = buttonLabels.get(i);
            JButton button = createStyledButton(emojis[i] + "  " + buttonLabel);
            menuPanel.add(button);
            menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));

            button.addActionListener(e -> {
                String sectionFilter = switch (buttonLabel) {
                    case "Studying" -> "STUDYING";
                    case "Gaming" -> "GAMING";
                    case "Dining" -> "DINING";
                    case "Hanging Out" -> "HANGING_OUT";
                    case "Others" -> "OTHERS";
                    default -> null; // For "Latest Post"
                };

                viewModel.clearPosts();
                postsPanel.updateSectionFilter(sectionFilter);

                switch (buttonLabel) {
                    case "Latest Post" -> new HomePage1(currentUsername); // Open HomePage
                    case "Studying" -> new StudyingUI(currentUsername);
                    case "Gaming" -> new GamingUI(currentUsername);
                    case "Dining" -> new DiningUI(currentUsername);
                    case "Hanging Out" -> new HangingOutUI(currentUsername);
                    case "Others" -> new OthersUI(currentUsername);
                    default -> System.out.println("Unknown button");
                }
            });
        }

        add(menuPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(new Color(0, 51, 102));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        return button;
    }
}