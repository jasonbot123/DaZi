package interface_adapter.chat;

import use_case.chatsave.ChatSaveOutputBoundary;
import use_case.chatsave.ChatSaveOutputData;
import view.ChatPageUI.ChatWindow;

import java.util.List;

public class ChatPresenter implements ChatSaveOutputBoundary {
    private ChatWindow chatWindow;

    public void setChatWindow(ChatWindow chatWindow) {
        this.chatWindow = chatWindow;
    }


    @Override
    public void presentMessage(ChatSaveOutputData outputData) {
        if (chatWindow != null) {
            chatWindow.displayMessages(List.of(outputData));
        }
    }

    @Override
    public void presentPreviousMessages(List<ChatSaveOutputData> messages) {
        if (chatWindow != null) {
            chatWindow.displayMessages(messages);
        }
    }

    }

//    @Override
//    public void presentChatContacts(List<String> contacts) {
//        if (chatPage != null) {
//            chatPage.displayContacts(contacts);
//        }
//    }


//    @Override
//    public void displayMessagesInView(List<ChatSaveOutputData> messages) {
//        if (chatWindow != null) {
//            chatWindow.displayMessages(messages);
//        }
//    }

