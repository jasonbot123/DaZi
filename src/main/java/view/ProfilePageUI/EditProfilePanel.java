package view.ProfilePageUI;

import interface_adapter.profile.ProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProfilePanel extends JPanel {
    private final ProfileViewModel viewModel;

    public EditProfilePanel(ProfileViewModel viewModel) {
        this.viewModel = viewModel;

        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Profile Information"));

        // Non-editable fields
        add(new JLabel("Username:"));
        add(new JLabel(viewModel.getUsername())); // Display username from ViewModel

        add(new JLabel("Email:"));
        add(new JLabel(viewModel.getEmail())); // Display email from ViewModel

        // Editable fields
        add(new JLabel("Year of Study:"));
        JTextField yearOfStudyField = new JTextField(viewModel.getYearOfStudy());
        add(yearOfStudyField);

        add(new JLabel("Program:"));
        JTextField programField = new JTextField(viewModel.getProgram());
        add(programField);

        add(new JLabel("Bio:"));
        JTextArea bioArea = new JTextArea(viewModel.getBio());
        bioArea.setRows(3);
        JScrollPane bioScrollPane = new JScrollPane(bioArea);
        add(bioScrollPane);

        add(new JLabel("College:"));
        JTextField collegeField = new JTextField(viewModel.getCollege());
        add(collegeField);

        // Save Button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the ViewModel with new values
                viewModel.setYearOfStudy(yearOfStudyField.getText());
                viewModel.setProgram(programField.getText());
                viewModel.setBio(bioArea.getText());
                viewModel.setCollege(collegeField.getText());

                // Provide feedback to the user
                JOptionPane.showMessageDialog(null, "Profile updated successfully!");
            }
        });
        add(saveButton);
    }
}