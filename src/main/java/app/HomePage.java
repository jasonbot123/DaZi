package app;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {
    public HomePage() {

        // Setup of home page
        setTitle("DaZi - Home Page");

        // adjust the window size according to your computer
        setSize(800, 600);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.8);
        int height = (int) (screenSize.height * 0.8);
        setSize(width, height);
        // min size to avoid layout issues
        setMinimumSize(new Dimension(600, 400));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
    }

    // adding components: navigation and posts
    private void initComponents() {
        add(createSearchBar(), BorderLayout.NORTH);
        add(createSidebar(), BorderLayout.WEST);
        add(createPostArea(), BorderLayout.CENTER);

    }

    // top panel: icon, search bar, icons
    private JPanel createSearchBar() {
        JPanel topBar = new JPanel(new BorderLayout());

        // top-right corner, icon button
        // TODO: Replace with the actual icon path
        JButton cornerIconButton = new JButton(new ImageIcon("path/to/corner_icon.png"));
        cornerIconButton.setToolTipText("Corner Icon");
        topBar.add(cornerIconButton, BorderLayout.WEST);

        // Panel for search field and search button
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

        JTextField searchField = new JTextField("Hinted search text", 20);
        JButton searchButton = new JButton("Search");

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        topBar.add(searchPanel, BorderLayout.CENTER);

        // icon panel: Profile, Notifications, Logout, top right corner
        topBar.add(createIconPanel(), BorderLayout.EAST);

        return topBar;
    }

    // left section panel
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        JLabel sidebarTitle = new JLabel("Categories"); // TODO: name this sth else
        sidebarTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(sidebarTitle);

        sidebar.add(Box.createVerticalStrut(20));

        // Add section buttons (Studying, Gaming, Dining, etc.)
        String[] sections = {"Latest Post", "Your Post", "Studying", "Gaming", "Dining", "Hanging Out", "Others"};

        for (String section : sections) {
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));

            // TODO: add icon, empty label to leave space for an icon later now
            JLabel iconLabel = new JLabel();
            iconLabel.setPreferredSize(new Dimension(20, 20)); //space for icon

            JButton sectionButton = new JButton(section);
            sectionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPanel.add(iconLabel);
            buttonPanel.add(sectionButton);

            sidebar.add(buttonPanel);
            sidebar.add(Box.createVerticalStrut(10));
        }

        return sidebar;
    }

    // top right corner, logout notification, profile, create post
    private JPanel createIconPanel() {
        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Profile button
        JButton profileButton = new JButton("Profile");
        iconPanel.add(profileButton);

        // Notifications button
        JButton notificationsButton = new JButton("Notifications");
        iconPanel.add(notificationsButton);

        // Log Out button
        JButton logoutButton = new JButton("Log Out");
        iconPanel.add(logoutButton);

        /*
        logoutButton.addActionListener(e -> {
            System.out.println("User logged out"); // TODO: replace with actual action
            dispose(); // Close the HomePage window
        });
         */

        return iconPanel;
    }

    // posts panel
    private JScrollPane createPostArea() {
        JPanel postPanelArea = new JPanel();
        postPanelArea.setLayout(new BoxLayout(postPanelArea, BoxLayout.Y_AXIS));

        // TODO: delete for loop and replace with actual posts
        for (int i = 0; i < 10; i++) {
            JPanel postPanel = new JPanel();
            postPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            postPanel.setLayout(new BorderLayout());

            JLabel titleLabel = new JLabel("Post Title Example " + (i + 1));

            // Increase the height of the description area by setting preferred size
            JTextArea descriptionArea = new JTextArea("Sample post description for post " + (i + 1));
            descriptionArea.setLineWrap(true);
            descriptionArea.setWrapStyleWord(true);
            descriptionArea.setEditable(false);
            descriptionArea.setPreferredSize(new Dimension(descriptionArea.getWidth(), 60));

            postPanel.add(titleLabel, BorderLayout.NORTH);
            postPanel.add(descriptionArea, BorderLayout.CENTER);

            postPanelArea.add(postPanel);

            // Add space between posts
            postPanelArea.add(Box.createVerticalStrut(10)); // 10 pixels of vertical space between posts
        }

        JScrollPane scrollPane = new JScrollPane(postPanelArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }

    public static void main(String[] args) {
        // create an instance of the Runnable interface
        SwingUtilities.invokeLater( () -> {
            HomePage homePage = new HomePage();
            new HomePage().setVisible(true);
        });
    }
}
