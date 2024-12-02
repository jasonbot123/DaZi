package view.HomePageUI;

import view.ChatPageUI.ChatPage;
import view.CreatePostUI.CreatePostPage;
import view.ProfilePageUI.CreateProfilePage;

import javax.swing.*;
import java.awt.*;

public class TopRightIconsPanel extends JPanel {

    public TopRightIconsPanel(HomePage1 parentFrame) {
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        String[] icons = {"💬", "➕", "👤", "🔔", "🚪"}; // placeholder for icons

        for (String icon : icons) {
            JButton iconButton = new JButton(icon);
            add(iconButton);

            // TODO: actionListener for all icons

            // actionListener for the create post button
            if ("➕".equals(icon)) {
                iconButton.addActionListener(e -> new CreatePostPage(parentFrame));
            }
            if ("💬".equals(icon)) {
                iconButton.addActionListener(e -> new ChatPage(parentFrame));
            }
            if ("👤".equals(icon)) {
              iconButton.addActionListener(e -> {
                  CreateProfilePage createProfilepage = new CreateProfilePage();
                  createProfilepage.launchSaveProfilePage("Jason");
                  });
            }
        }
    }
}

/*
if ("➕".equals(icon)) {
                iconButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Dispose the current frame and open the CreatePostPage
                        parentFrame.dispose();
                        SwingUtilities.invokeLater(() -> new CreatePostPage(parentFrame));
                    }
                });
            }
 */