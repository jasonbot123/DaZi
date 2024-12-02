package view.ChatPageUI;

import com.mongodb.client.MongoDatabase;
import data_access.MongoChatContactDataAccess;
import data_access.MongoChatDataAccessObject;
import data_access.MongoDBConnection;
import javax.swing.border.TitledBorder;
import data_access.MongoPostDataAccessObject;
import entity.Post;
import entity.Section;
import interface_adapter.chat.ChatController;
import interface_adapter.chat.ChatPresenter;
import interface_adapter.contact.ChatContactController;
import interface_adapter.contact.ChatContactPresenter;

import use_case.contact.ChatContactInteractor;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChatPage extends JFrame {
    private final DefaultListModel<String> contactsListModel;

    public ChatPage(JFrame parentFrame, String username) {

        setTitle("Direct Chats");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(parentFrame);
        setLayout(new BorderLayout());

        MongoDatabase database = MongoDBConnection.getDatabase("chats");

        MongoChatContactDataAccess dataAccess = new MongoChatContactDataAccess(database);
        ChatContactPresenter contactPresenter = new ChatContactPresenter();
        ChatContactInteractor interactor = new ChatContactInteractor(contactPresenter, dataAccess);
        ChatContactController chatContactController = new ChatContactController(interactor);

        contactPresenter.setChatPage(this);


        contactsListModel = new DefaultListModel<>();
        JList<String> contactsList = new JList<>(contactsListModel);
        contactsList.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        contactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contactsList.setBackground(new Color(240, 248, 255));
        contactsList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(contactsList);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Direct Chats", TitledBorder.CENTER, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 14), Color.DARK_GRAY));
        add(scrollPane, BorderLayout.CENTER);

        contactsList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedUser = contactsList.getSelectedValue();
                if (selectedUser != null) {
                    ChatWindow chatWindow = new ChatWindow(selectedUser, username);
                    chatWindow.setVisible(true);
                }
            }
        });

        // Load chat contacts for the current user
        chatContactController.getChatContacts(username);

        setVisible(true);
    }

    public void displayContacts(List<String> contacts) {
        contactsListModel.clear();
        if (contacts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No contacts found.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (String contact : contacts) {
                contactsListModel.addElement(contact);
            }
        }
    }
}
