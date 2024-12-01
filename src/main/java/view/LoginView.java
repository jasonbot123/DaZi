package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

/**
 * The View for when the user is logging into the program.
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    private final JButton logIn;
    private final JButton cancel;
    private LoginController loginController;

    public LoginView(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        // Set layout and background
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(600, 400)); // Increased screen size
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Login to DaZi", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(34, 34, 34));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(title, gbc);

        // Username input
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameInputField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(usernameInputField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        usernameErrorField.setForeground(Color.RED);
        usernameErrorField.setFont(new Font("Arial", Font.ITALIC, 12));
        this.add(usernameErrorField, gbc);

        // Password input
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordInputField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(passwordInputField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        passwordErrorField.setForeground(Color.RED);
        passwordErrorField.setFont(new Font("Arial", Font.ITALIC, 12));
        this.add(passwordErrorField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(Color.WHITE);

        logIn = new JButton("Log In");
        logIn.setFont(new Font("Arial", Font.BOLD, 14));
        logIn.setBackground(new Color(51, 153, 255));
        logIn.setForeground(Color.BLACK); // Button font color set to black
        logIn.setFocusPainted(false);
        logIn.addActionListener(e -> {
            LoginState currentState = loginViewModel.getState();
            loginController.execute(
                    currentState.getUsername(),
                    currentState.getPassword()
            );
        });

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Arial", Font.BOLD, 14));
        cancel.setBackground(new Color(220, 53, 69));
        cancel.setForeground(Color.BLACK); // Button font color set to black
        cancel.setFocusPainted(false);
        cancel.addActionListener(this);

        buttonPanel.add(logIn);
        buttonPanel.add(cancel);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        this.add(buttonPanel, gbc);

        // Add document listeners
        usernameInputField.getDocument().addDocumentListener(createDocumentListener());
        passwordInputField.getDocument().addDocumentListener(createPasswordListener());
    }

    private DocumentListener createDocumentListener() {
        return new DocumentListener() {
            private void updateState() {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                loginViewModel.setState(currentState);
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

    private DocumentListener createPasswordListener() {
        return new DocumentListener() {
            private void updateState() {
                LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                loginViewModel.setState(currentState);
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
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == cancel) {
            usernameInputField.setText("");
            passwordInputField.setText("");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        usernameErrorField.setText(state.getLoginError());
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
