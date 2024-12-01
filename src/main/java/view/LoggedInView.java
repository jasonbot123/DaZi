package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.logout.LogoutController;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private final JLabel usernameLabel = new JLabel();
    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePasswordButton;
    private final JButton logoutButton;
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        // Set layout and background
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(600, 400));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Welcome to DaZi", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(34, 34, 34));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(title, gbc);

        // Username display
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(new JLabel("Logged in as:"), gbc);

        gbc.gridx = 1;
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(usernameLabel, gbc);

        // Password input
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(new JLabel("New Password:"), gbc);

        gbc.gridx = 1;
        passwordInputField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(passwordInputField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        passwordErrorField.setForeground(Color.RED);
        passwordErrorField.setFont(new Font("Arial", Font.ITALIC, 12));
        this.add(passwordErrorField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(Color.WHITE);

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setFont(new Font("Arial", Font.BOLD, 14));
        changePasswordButton.setBackground(new Color(40, 167, 69));
        changePasswordButton.setForeground(Color.BLACK);
        changePasswordButton.setFocusPainted(false);
        changePasswordButton.addActionListener(e -> {
            LoggedInState state = loggedInViewModel.getState();
            changePasswordController.execute(
                    state.getUsername(),
                    state.getPassword(),
                    state.getEmail()
            );
        });

        logoutButton = new JButton("Log Out");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(new Color(220, 53, 69));
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(e -> {
            LoggedInState state = loggedInViewModel.getState();
            logoutController.execute(state.getUsername());
        });

        buttonPanel.add(changePasswordButton);
        buttonPanel.add(logoutButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        this.add(buttonPanel, gbc);

        // Add document listeners
        passwordInputField.getDocument().addDocumentListener(createPasswordListener());
    }

    private DocumentListener createPasswordListener() {
        return new DocumentListener() {
            private void updateState() {
                LoggedInState state = loggedInViewModel.getState();
                state.setPassword(passwordInputField.getText());
                loggedInViewModel.setState(state);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateState();
            }
        };
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            LoggedInState state = (LoggedInState) evt.getNewValue();
            usernameLabel.setText(state.getUsername());
        } else if ("password".equals(evt.getPropertyName())) {
            LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, "Password updated for " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }
}
