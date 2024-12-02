package view.ProfilePageUI;
import interface_adapter.profilesave.*;
import interface_adapter.profileview.*;

import javax.swing.*;
import java.awt.*;

public class EditProfilePage extends JFrame {
    public EditProfilePage(ProfileViewModel viewModel, ProfileSaveController saveController, String username) {
        setTitle("Edit Profile Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        String[] yearOptions = {"1st Year", "2nd Year", "3rd Year", "4th Year", "Graduate"};
        String[] collegeOptions = {"University College", "Woodsworth College", "St. Michael's College", "Victoria College", "Innis College", "New College", "Trinity College"};

        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createTitledBorder("Edit Profile Information"));

        mainPanel.add(new JLabel("Year of Study:"));
        JComboBox<String> yearOfStudyCombo = new JComboBox<>(yearOptions);
        yearOfStudyCombo.setSelectedItem(viewModel.getYearOfStudy()); // Set the initial selection
        mainPanel.add(yearOfStudyCombo);

        mainPanel.add(new JLabel("College:"));
        JComboBox<String> collegeCombo = new JComboBox<>(collegeOptions);
        collegeCombo.setSelectedItem(viewModel.getCollege()); // Set the initial selection
        mainPanel.add(collegeCombo);

        mainPanel.add(new JLabel("Program:"));
        JTextField programField = new JTextField(viewModel.getProgram());
        mainPanel.add(programField);

        mainPanel.add(new JLabel("Bio:"));
        JTextArea bioArea = new JTextArea(viewModel.getBio());
        bioArea.setRows(3);
        JScrollPane bioScrollPane = new JScrollPane(bioArea);
        mainPanel.add(bioScrollPane);
        add(mainPanel, BorderLayout.CENTER);


        // Save Button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String selectedYear = (String) yearOfStudyCombo.getSelectedItem();
            String selectedCollege = (String) collegeCombo.getSelectedItem();
            saveController.saveProfile(username, selectedYear, programField.getText(), bioArea.getText(), selectedCollege);
            JOptionPane.showMessageDialog(this, "Profile updated successfully!");
            viewModel.setYearOfStudy(selectedYear);
            viewModel.setProgram(programField.getText());
            viewModel.setBio(bioArea.getText());
            viewModel.setCollege(selectedCollege);
            dispose();// Close Edit Profile Page
            new SelfProfilePage(viewModel, username);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
