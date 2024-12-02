package interface_adapter.contact;

import use_case.contact.ChatContactInputBoundary;
import use_case.contact.ChatContactInputData;

public class ChatContactController {
    private final ChatContactInputBoundary interactor;

    public ChatContactController(ChatContactInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void getChatContacts(String username) {
        ChatContactInputData inputData = new ChatContactInputData(username);
        interactor.retrieveChatContacts(inputData);
    }
}
