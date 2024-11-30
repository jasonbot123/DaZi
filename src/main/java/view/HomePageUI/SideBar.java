package view.HomePageUI;

import view.SectionPageUI.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SideBar extends JPanel {

    public SideBar(String currentUsername) {
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
            String label = buttonLabels.get(i);
            JButton button = createStyledButton(emojis[i] + "  " + label);
            menuPanel.add(button);
            menuPanel.add(Box.createRigidArea(new Dimension(0, 15))); // ertical spacing

            button.addActionListener(e -> {
                switch (label) {
                    case "Latest Post" -> new HomePage1(currentUsername); // redirect to HomePage
                    case "Studying" -> new StudyingUI(currentUsername);
                    case "Gaming" -> new GamingUI(currentUsername);
                    case "Dining" -> new DiningUI(currentUsername);
                    case "Hanging Out" -> new HangingOutUI(currentUsername);
                    case "Others" -> new OthersUI(currentUsername);
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