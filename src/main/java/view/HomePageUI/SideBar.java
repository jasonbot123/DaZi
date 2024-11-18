package view.HomePageUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SideBar extends JPanel {

    public SideBar() {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 51, 102)); // TODO: change sidebar background color

        //add(new LogoPanel(), BorderLayout.NORTH);

        // menu section
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(0, 51, 102));

        // emojis and buttons
        String[] emojis = {
                "📜", "💬", "📚", "🎮", "🍽️", "🤝", "🌀"
        };
        List<String> buttonLabels = List.of(
                "Latest Post",
                "Your Post",
                "Studying",
                "Gaming",
                "Dining",
                "Hanging Out",
                "Others"
        );

        for (int i = 0; i < buttonLabels.size(); i++) {
            JButton button = createStyledButton(emojis[i] + "  " + buttonLabels.get(i));
            menuPanel.add(button);
            menuPanel.add(Box.createRigidArea(new Dimension(0, 15))); // TODO: add more vertical spacing
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