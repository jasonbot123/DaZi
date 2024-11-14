package view.HomePageUI;
import javax.swing.*;
import java.awt.*;

public class HomePage1 extends javax.swing.JFrame {
    public HomePage1() {
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame to maximize on startup
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Full-screen mode
        setPreferredSize(new Dimension(1200, 800));  // Fallback size

        // Use BorderLayout for responsive layout
        setLayout(new BorderLayout());

        // Add each section as a separate panel
        add(new SideBar(), BorderLayout.WEST);
        add(new SearchBar(), BorderLayout.NORTH);
        add(new PostsPanel(), BorderLayout.CENTER);

        pack();  // Sizes components based on preferred sizes
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomePage1::new);
    }

}
