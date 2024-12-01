package view.ChatPageUI;

import com.mongodb.client.MongoDatabase;
import data_access.MongoChatContactDataAccess;
import data_access.MongoDBConnection;
import data_access.MongoChatDataAccessObject;
import entity.ChatContact;
import interface_adapter.chat.ChatController;
import interface_adapter.chat.ChatPresenter;
import interface_adapter.contact.ChatContactController;
import interface_adapter.contact.ChatContactPresenter;
import use_case.chatsave.ChatSaveDataAccessInterface;
import use_case.chatsave.ChatSaveInteractor;
import use_case.contact.ChatContactInteractor;

import javax.swing.*;
import java.util.List;

public class MockChatViewApp {
    public static void main(String[] args) {
        // Establish MongoDB connection
        MongoDatabase database = MongoDBConnection.getDatabase("chats");

        // Create Data Access Object
        MongoChatContactDataAccess chatDataAccess = new MongoChatContactDataAccess(database);

        // Create Presenter
//        ChatPresenter chatPresenter = new ChatPresenter();

        // Create Interactor
//        ChatSaveInteractor chatInteractor = new ChatSaveInteractor(chatPresenter, chatDataAccess);

        // Create Controller
//        ChatController chatController = new ChatController(chatInteractor);

        // Create GUI Window for Chat
        String currentUser = "jason";
        String chatPartner = "Jeremy";
//        ChatWindow chatWindow = new ChatWindow(chatPartner);

        //  Make chat window visible
//        chatWindow.setVisible(true);
        ChatContactPresenter contactPresenter = new ChatContactPresenter();
        ChatContactInteractor interactor = new ChatContactInteractor(contactPresenter, chatDataAccess);
        ChatContactController chatContactController = new ChatContactController(interactor);

        JFrame frame = new JFrame();
        ChatPage chatPage = new ChatPage(frame);
        chatPage.setVisible(true);

        List<ChatContact> contacts = chatDataAccess.getChatContacts("jason");
        System.out.println(contacts);


    }

}
