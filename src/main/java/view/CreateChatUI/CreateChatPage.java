package view.CreateChatUI;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

import com.mongodb.client.MongoDatabase;
import data_access.MongoChatDataAccessObject;
import data_access.MongoDBConnection;
import javax.swing.border.TitledBorder;
import data_access.MongoPostDataAccessObject;
import entity.Post;
import entity.Section;
import service.ChatService;
import view.ChatWindow;
import view.HomePageUI.HomePage1;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CreateChatPage extends JFrame {

    public CreateChatPage(JFrame parentFrame) {
            // Set up the JFrame for direct chats
            setTitle("Direct Chats");
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setSize(300, 400);
            setLocationRelativeTo(parentFrame);

            setLayout(new BorderLayout());

            // Create a list to display direct chat contacts
            DefaultListModel<String> chatListModel = new DefaultListModel<>();
            JList<String> chatList = new JList<>(chatListModel);
            chatList.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            chatList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            chatList.setBackground(new Color(240, 248, 255));
            chatList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Fix this once Taha is done
            MongoDatabase database = MongoDBConnection.getDatabase("ChatDB");
            MongoChatDataAccessObject dao = new MongoChatDataAccessObject(database);
            List<String> directChats = dao.getChatContacts("jason");

            // Add direct chat contacts to the list
            for (String user : directChats) {
                chatListModel.addElement(user);
            }

            // Add the chat list to a scroll pane
            JScrollPane scrollPane = new JScrollPane(chatList);
            scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Direct Chats", TitledBorder.CENTER, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 14), Color.DARK_GRAY));
            add(scrollPane, BorderLayout.CENTER);

            // Add action listener to handle when a user is selected
            chatList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    String selectedUser = chatList.getSelectedValue();
                    if (selectedUser != null) {
                        // Open chat window with the selected user
                        ChatWindow chatWindow = new ChatWindow("jason", selectedUser, new ChatService(new MongoChatDataAccessObject(MongoDBConnection.getDatabase("ChatDB"))));
                        chatWindow.setVisible(true);
                        dispose();
                    }
                }
            });

            setVisible(true);
        }
}
