package view.ChatPageUI;

import com.mongodb.client.MongoDatabase;
import data_access.MongoChatDataAccessObject;
import data_access.MongoDBConnection;
import interface_adapter.chat.ChatController;
import interface_adapter.chat.ChatPresenter;
import interface_adapter.contact.ChatContactController;
import interface_adapter.contact.ChatContactPresenter;
import use_case.chatsave.ChatSaveInteractor;
import use_case.chatsave.ChatSaveOutputData;
import java.time.format.DateTimeFormatter;


import javax.swing.*;
import java.awt.*;
import java.util.List;


public class ChatWindow extends JFrame {
    private final ChatController chatController;
    private final String currentUser;
    private final String chatPartner;
    private final JTextArea chatArea;
    private final JTextField inputField;

    public ChatWindow(String chatPartner, String currentUser) {
        this.chatPartner = chatPartner;
        this.currentUser = currentUser;

        setTitle("Chat with " + chatPartner);
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        MongoDatabase database = MongoDBConnection.getDatabase("chats");
        MongoChatDataAccessObject chatDataAccess = new MongoChatDataAccessObject(database);
        ChatPresenter chatPresenter = new ChatPresenter();
        ChatSaveInteractor chatInteractor = new ChatSaveInteractor(chatPresenter, chatDataAccess);
        this.chatController = new ChatController(chatInteractor);

        chatPresenter.setChatWindow(this);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        inputField = new JTextField();
        JButton sendButton = new JButton("Send");
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> {
            String content = inputField.getText().trim();
            if (!content.isEmpty()) {
                chatController.sendMessage(currentUser, chatPartner, content);
                inputField.setText("");
            }
        });

        // Load previous messages
        chatController.loadPreviousMessages(currentUser, chatPartner);
    }

    public void displayMessages(List<ChatSaveOutputData> messages) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (ChatSaveOutputData message : messages) {
            String formattedTimestamp = message.getTimestamp().format(formatter);
            chatArea.append(formattedTimestamp
                    + " [" + message.getSender() + " to " + message.getReceiver()
                    + "]: " + message.getContent() + "\n");
        }
    }
}
