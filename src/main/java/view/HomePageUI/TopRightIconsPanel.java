package view.HomePageUI;

import view.CreatePostUI.CreatePostPage;
import view.SectionPageUI.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopRightIconsPanel extends JPanel {
    public TopRightIconsPanel(JFrame parentFrame) {
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        String[] icons = {"📩", "➕", "👤", "🔔", "🚪"};

        for (String icon : icons) {
            JButton iconButton = new JButton(icon);
            add(iconButton);

            // Example ActionListener for "➕" button
            if ("➕".equals(icon)) {
                iconButton.addActionListener(e -> {
                    if (parentFrame instanceof HomePage1) {
                        new CreatePostPage((HomePage1) parentFrame); // redirect to CreatePostPage
                    } else if (parentFrame instanceof StudyingUI) {
                        new CreatePostPage((StudyingUI) parentFrame);
                    } else if (parentFrame instanceof DiningUI) {
                        new CreatePostPage((DiningUI) parentFrame);
                    } else if (parentFrame instanceof GamingUI) {
                        new CreatePostPage((GamingUI) parentFrame);
                    } else if (parentFrame instanceof HangingOutUI) {
                        new CreatePostPage((HangingOutUI) parentFrame);
                    } else if (parentFrame instanceof  OthersUI) {
                        new CreatePostPage((OthersUI) parentFrame);
                    }

                });
            }
        }
    }
}