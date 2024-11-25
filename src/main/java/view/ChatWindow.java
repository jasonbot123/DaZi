package view;

import data_access.MongoChatDataAccessObject;
import data_access.MongoDBConnection;
import service.ChatService;
import use_case.chat.ManageChat;

import javax.swing.*;
import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChatWindow extends JFrame {
    private JTextArea chatArea;       // Area to display messages
    private JTextField messageField;  // Field to type a message
    private JButton sendButton;       // Button to send the message
    private String username;
    private String receiver;          // The receiver of the messages
    private ManageChat manageChat;

    public ChatWindow(String username, String receiver, ChatService chatService) {
        this.username = username;
        this.receiver = receiver;

        // Set up the frame
        setTitle("Chat Window - User: " + username);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Chat area where messages are displayed
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Message input field
        messageField = new JTextField(25);

        // Send button
        sendButton = new JButton("Send");

        // Panel for input field and button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(messageField);
        inputPanel.add(sendButton);

        // Initialize ChatHandler
        this.manageChat = new ManageChat(chatService, username, receiver, chatArea);

        // Add ActionListener to Send Button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText().trim();
                if (!message.isEmpty()) {
                    manageChat.sendMessage(message);
                    messageField.setText("");  // Clear the input field after sending
                }
            }
        });

        // Set layout and add components
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Load previous chat history
        manageChat.loadPreviousMessages();
    }

    public static void main(String[] args) {
        // Mock ChatService
        ChatService chatService = new ChatService(new MongoChatDataAccessObject(MongoDBConnection.getDatabase("ChatDB")));
        String username = "jason";
        String receiver = "janos";
        SwingUtilities.invokeLater(() -> {
            ChatWindow chatWindow = new ChatWindow(username, receiver, chatService);
            chatWindow.setVisible(true);
        });
    }
}
