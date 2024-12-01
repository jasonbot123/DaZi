package interface_adapter.contact;

import use_case.contact.ChatContactOutputBoundary;
import use_case.contact.ChatContactOutputData;
import view.ChatPageUI.ChatPage;

public class ChatContactPresenter implements ChatContactOutputBoundary {
    private ChatPage chatPage;

    public void setChatPage(ChatPage chatPage) {
        this.chatPage = chatPage;
    }

    @Override
    public void presentChatContacts(ChatContactOutputData outputData) {
        if (chatPage != null) {
            chatPage.displayContacts(outputData.getContactList());
        }
    }
}
