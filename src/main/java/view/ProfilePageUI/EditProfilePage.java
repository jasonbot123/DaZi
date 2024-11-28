package view.ProfilePageUI;
import data_access.MongoDBConnection;
import data_access.MongoUserProfileSaveDataAccessObject;
import interface_adapter.profilesave.*;
import interface_adapter.profileview.*;
import service.ProfileService;
import use_case.profilesave.*;
import use_case.profileview.*;

import javax.swing.*;
import java.awt.*;

public class EditProfilePage extends JFrame {
    public EditProfilePage(ProfileViewModel viewModel, ProfileSaveController saveController) {
        setTitle("Edit Profile Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        String[] yearOptions = {"1st Year", "2nd Year", "3rd Year", "4th Year", "Graduate"};
        String[] collegeOptions = {"University College", "Science College", "Business College", "Arts College", "Law College"};

        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createTitledBorder("Edit Profile Information"));

        JPanel editPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        editPanel.setBorder(BorderFactory.createTitledBorder("Edit Profile Information"));

        editPanel.add(new JLabel("Year of Study:"));
        JTextField yearOfStudyField = new JTextField(viewModel.getYearOfStudy());
        editPanel.add(yearOfStudyField);

        mainPanel.add(new JLabel("Year of Study:"));
        JComboBox<String> yearOfStudyCombo = new JComboBox<>(yearOptions);
        yearOfStudyCombo.setSelectedItem(viewModel.getYearOfStudy()); // Set the initial selection
        mainPanel.add(yearOfStudyCombo);

        editPanel.add(new JLabel("Program:"));
        JTextField programField = new JTextField(viewModel.getProgram());
        editPanel.add(programField);

        editPanel.add(new JLabel("College:"));
        JTextField collegeField = new JTextField(viewModel.getCollege());
        editPanel.add(collegeField);

        editPanel.add(new JLabel("Bio:"));
        JTextArea bioArea = new JTextArea(viewModel.getBio());
        bioArea.setRows(3);
        JScrollPane bioScrollPane = new JScrollPane(bioArea);
        editPanel.add(bioScrollPane);
        add(editPanel, BorderLayout.CENTER);


        // Save Button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            saveController.saveProfile("Jason", yearOfStudyField.getText(), programField.getText(), bioArea.getText(), collegeField.getText());
            JOptionPane.showMessageDialog(this, "Profile updated successfully!");
            dispose(); // Close Edit Profile Page
        });
            // Update ViewModel with new values
            viewModel.setYearOfStudy(yearOfStudyField.getText());
            viewModel.setProgram(programField.getText());
            viewModel.setBio(bioArea.getText());
            viewModel.setCollege(collegeField.getText());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
