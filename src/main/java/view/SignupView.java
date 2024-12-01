package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

/**
 * The View for when the user is signing up for the program.
 */
public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "sign up";
    private final SignupViewModel signupViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JLabel repeatPasswordErrorField = new JLabel();
    private final JTextField emailInputField = new JTextField(15);
    private final JLabel emailErrorField = new JLabel();

    private final JButton signUp;
    private final JButton cancel;
    private final JButton toLogin;
    private SignupController signupController;

    public SignupView(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
        this.signupViewModel.addPropertyChangeListener(this);

        // Set layout and background
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(600, 500));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Create a DaZi Account", SwingConstants.CENTER);
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

        gbc.gridy = 2;
        usernameErrorField.setForeground(Color.RED);
        usernameErrorField.setFont(new Font("Arial", Font.ITALIC, 12));
        this.add(usernameErrorField, gbc);

        // Email input
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        emailInputField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(emailInputField, gbc);

        gbc.gridy = 4;
        emailErrorField.setForeground(Color.RED);
        emailErrorField.setFont(new Font("Arial", Font.ITALIC, 12));
        this.add(emailErrorField, gbc);

        // Password input
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordInputField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(passwordInputField, gbc);

        gbc.gridy = 6;
        passwordErrorField.setForeground(Color.RED);
        passwordErrorField.setFont(new Font("Arial", Font.ITALIC, 12));
        this.add(passwordErrorField, gbc);

        // Repeat Password input
        gbc.gridx = 0;
        gbc.gridy = 7;
        this.add(new JLabel("Repeat Password:"), gbc);

        gbc.gridx = 1;
        repeatPasswordInputField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(repeatPasswordInputField, gbc);

        gbc.gridy = 8;
        repeatPasswordErrorField.setForeground(Color.RED);
        repeatPasswordErrorField.setFont(new Font("Arial", Font.ITALIC, 12));
        this.add(repeatPasswordErrorField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(Color.WHITE);

        signUp = new JButton("Sign Up");
        signUp.setFont(new Font("Arial", Font.BOLD, 14));
        signUp.setBackground(new Color(51, 153, 255));
        signUp.setForeground(Color.BLACK);
        signUp.setFocusPainted(false);
        signUp.addActionListener(e -> {
            SignupState currentState = signupViewModel.getState();
            signupController.execute(
                    currentState.getUsername(),
                    currentState.getPassword(),
                    currentState.getRepeatPassword(),
                    currentState.getEmail()
            );
        });

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Arial", Font.BOLD, 14));
        cancel.setBackground(new Color(220, 53, 69));
        cancel.setForeground(Color.BLACK);
        cancel.setFocusPainted(false);
        cancel.addActionListener(this);

        toLogin = new JButton("Back to Login");
        toLogin.setFont(new Font("Arial", Font.BOLD, 14));
        toLogin.setBackground(new Color(102, 204, 102));
        toLogin.setForeground(Color.BLACK);
        toLogin.setFocusPainted(false);
        toLogin.addActionListener(e -> signupController.switchToLoginView());

        buttonPanel.add(signUp);
        buttonPanel.add(cancel);
        buttonPanel.add(toLogin);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        this.add(buttonPanel, gbc);

        // Add document listeners
        usernameInputField.getDocument().addDocumentListener(createDocumentListener(() -> {
            SignupState currentState = signupViewModel.getState();
            currentState.setUsername(usernameInputField.getText());
            signupViewModel.setState(currentState);
        }));

        emailInputField.getDocument().addDocumentListener(createDocumentListener(() -> {
            SignupState currentState = signupViewModel.getState();
            currentState.setEmail(emailInputField.getText());
            signupViewModel.setState(currentState);
        }));

        passwordInputField.getDocument().addDocumentListener(createDocumentListener(() -> {
            SignupState currentState = signupViewModel.getState();
            currentState.setPassword(new String(passwordInputField.getPassword()));
            signupViewModel.setState(currentState);
        }));

        repeatPasswordInputField.getDocument().addDocumentListener(createDocumentListener(() -> {
            SignupState currentState = signupViewModel.getState();
            currentState.setRepeatPassword(new String(repeatPasswordInputField.getPassword()));
            signupViewModel.setState(currentState);
        }));
    }

    private DocumentListener createDocumentListener(Runnable updateState) {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateState.run();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateState.run();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateState.run();
            }
        };
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == cancel) {
            usernameInputField.setText("");
            passwordInputField.setText("");
            repeatPasswordInputField.setText("");
            emailInputField.setText("");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        setFields(state);
        usernameErrorField.setText(state.getUsernameError());
        emailErrorField.setText(state.getEmailError());
        passwordErrorField.setText(state.getPasswordError());
        repeatPasswordErrorField.setText(state.getRepeatPasswordError());
    }

    private void setFields(SignupState state) {
        usernameInputField.setText(state.getUsername());
        emailInputField.setText(state.getEmail());
        passwordInputField.setText(state.getPassword());
        repeatPasswordInputField.setText(state.getRepeatPassword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setSignupController(SignupController signupController) {
        this.signupController = signupController;
    }
}
