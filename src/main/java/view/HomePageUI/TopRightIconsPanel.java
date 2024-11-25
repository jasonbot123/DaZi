package view.HomePageUI;

import view.CreatePostUI.CreatePostPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopRightIconsPanel extends JPanel {

    public TopRightIconsPanel(HomePage1 parentFrame) {
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        String[] icons = {"📩", "➕", "👤", "🔔", "🚪"}; // placeholder for icons

        for (String icon : icons) {
            JButton iconButton = new JButton(icon);
            add(iconButton);

            // TODO: actionListener for all icons

            // actionListener for the create post button
            if ("➕".equals(icon)) {
                iconButton.addActionListener(e -> new CreatePostPage(parentFrame));
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