package view;

import javax.swing.*;
import java.awt.*;

import entity.Post;
import use_case.post.ManagePost;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends JFrame {

    private ManagePost managePost;
    private JPanel postPanelArea;

    public HomePage() {
        this.managePost = new ManagePost();
        fullWindowUI();

    }

    // Setup of home page
    private void fullWindowUI() {
        setTitle("DaZi - Home Page");

        // adjust the window size according to your computer
        // setSize(800, 600);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.8);
        int height = (int) (screenSize.height * 0.8);
        setSize(width, height);
        // min size to avoid layout issues
        setMinimumSize(new Dimension(600, 400));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createSearchBar(), BorderLayout.NORTH);
        add(createSidebar(), BorderLayout.WEST);
        add(createPostArea(), BorderLayout.CENTER);

    }



    public ManagePost getManagePost() {
        return this.managePost;
    }


    // top panel: logo, search bar, icons
    private JPanel createSearchBar() {
        JPanel topBar = new JPanel(new BorderLayout());

        // top-right corner, icon button

        // resize the logo
        ImageIcon originalLogoIcon = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image scaledImage = originalLogoIcon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledImage);
        // Create the button with the resized logo
        JButton cornerIconButton = new JButton(scaledLogoIcon);
        cornerIconButton.setToolTipText("Corner Icon");
        topBar.add(cornerIconButton, BorderLayout.WEST);
        topBar.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0)); // adjust the margin

        // Panel for search field and search button
        JPanel searchPanelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        searchPanelWrapper.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        JPanel searchPanel = new JPanel(new BorderLayout(5, 0)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g; // cast to Graphics2D
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(240, 240, 255)); // TODO: colour can change
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // rounded corners
            }
        };
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // textfield size

        JTextField searchField = new JTextField("What's on your mind today?", 60);
        searchField.setBorder(BorderFactory.createEmptyBorder());
        searchField.setOpaque(false);

        // search icon
        int searchIconSize = 20;
        ImageIcon originalSearchIcon = new ImageIcon(getClass().getResource("/images/search_icon.png"));
        Image scaledSearchImage = originalSearchIcon.getImage()
                .getScaledInstance(searchIconSize, searchIconSize, Image.SCALE_SMOOTH);
        JLabel searchIcon = new JLabel(new ImageIcon(scaledSearchImage));

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchIcon, BorderLayout.EAST);

        searchPanelWrapper.add(searchPanel);
        topBar.add(searchPanelWrapper, BorderLayout.CENTER);

        // Top-right icons
        int iconSize = 20;
        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        JButton createPostButton = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/images/createPost_icon.png")).getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH)));
        JButton profileButton = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/images/profile_icon.png")).getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH)));
        JButton notificationsButton = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/images/notify_icon.png")).getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH)));
        JButton logoutButton = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/images/logout_icon.png")).getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH)));

        // TODO: action listener for all icon buttons
        // for creating posts
        createPostButton.addActionListener(e -> {
            CreatePostPage createPostPage = new CreatePostPage(this, this.managePost);
            createPostPage.setVisible(true);
        });

        iconPanel.add(createPostButton);
        iconPanel.add(profileButton);
        iconPanel.add(notificationsButton);
        iconPanel.add(logoutButton);
        iconPanel.setOpaque(false);

        topBar.add(iconPanel, BorderLayout.EAST);

        return topBar;
    }

    // left section panel
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        JLabel sidebarTitle = new JLabel("Section"); // TODO: name this sth else
        sidebarTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(sidebarTitle);

        sidebar.add(Box.createVerticalStrut(20));

        // Add section buttons (Studying, Gaming, Dining, etc.)
        String[] sections = {"Latest Post", "Your Post", "Studying", "Gaming", "Dining", "Hanging Out", "Others"};

        for (String section : sections) {
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));

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
    // TODO: I put these into the seachbar method, consider refactor it to here
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
        postPanelArea = new JPanel();
        postPanelArea.setLayout(new BoxLayout(postPanelArea, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(postPanelArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        refreshPosts();
        return scrollPane;
    }

    public void refreshPosts() {
        postPanelArea.removeAll(); // clear existing posts

        List<Post> posts = this.managePost.getPosts();
        // to display most recent first
        for (int i = posts.size() - 1; i >= 0; i--) {
            Post post = posts.get(i);
            JPanel postPanel = createPostPanel(post);
            postPanelArea.add(postPanel);
            postPanelArea.add(Box.createVerticalStrut(15));
        }

        postPanelArea.revalidate();
        postPanelArea.repaint();
    }

    // TODO: change it to a scorl panel
    // TODO: adjust the space between post info and title and content
    private JPanel createPostPanel(Post post) {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BorderLayout());
        postPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(2, 8, 2, 8)
        ));

        // display section, time, username, user pic
        JLabel sectionLabel = new JLabel(post.getSection().toString());
        JLabel timestampLabel = new JLabel(post.getTimestamp().toString()); // TODO: dont include decimal for time
        JLabel usernameLabel = new JLabel(post.getUsername());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
        topPanel.add(sectionLabel);
        topPanel.add(new JLabel(" | ")); // TODO: change this into sth else if needed
        topPanel.add(timestampLabel);
        topPanel.add(new JLabel(" | "));
        topPanel.add(usernameLabel);

        // display title and content
        JLabel titleLabel = new JLabel("<html><b>" + post.getTitle() + "</b></html>");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(3, 0, 2, 0));

        JTextArea contentArea = new JTextArea(post.getTruncatedContent(50)); // display 50 chars max
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setEditable(false);
        contentArea.setOpaque(false); // Match background
        contentArea.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));

        postPanel.add(topPanel, BorderLayout.NORTH);
        postPanel.add(titleLabel, BorderLayout.CENTER);
        postPanel.add(contentArea, BorderLayout.SOUTH);

        return postPanel;
    }


    public static void main(String[] args) {
        // create an instance of the Runnable interface
        SwingUtilities.invokeLater( () -> {
            HomePage homePage = new HomePage();
            new HomePage().setVisible(true);
        });
    }
}
