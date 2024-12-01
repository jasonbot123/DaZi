package use_case.contact;

import entity.ChatContact;

import java.util.ArrayList;
import java.util.List;

public class ChatContactInteractor implements ChatContactInputBoundary{
    private final ChatContactOutputBoundary presenter;
    private final ChatContactDataAccessInterface dataAccess;

    public ChatContactInteractor(ChatContactOutputBoundary presenter, ChatContactDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void retrieveChatContacts(ChatContactInputData inputData) {
        List<ChatContact> contacts = dataAccess.getChatContacts(inputData.getCurrentUsername());
        List<String> contactNames = new ArrayList<>();

        for (ChatContact contact : contacts) {
            contactNames.add(contact.getUsername()); // Extracting just the contact names
        }

        ChatContactOutputData outputData = new ChatContactOutputData(contactNames);
        presenter.presentChatContacts(outputData);
    }
}
