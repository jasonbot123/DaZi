package use_case.chatsave;

import entity.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatSaveInteractor implements ChatSaveInputBoundary{
    private final ChatSaveOutputBoundary presenter;
    private final ChatSaveDataAccessInterface chatDataAccess;

    public ChatSaveInteractor(ChatSaveOutputBoundary presenter, ChatSaveDataAccessInterface chatDataAccess) {
        this.presenter = presenter;
        this.chatDataAccess = chatDataAccess;
    }

    @Override
    public void sendMessage(ChatSaveInputData inputData) {
        // Save message to data access
        chatDataAccess.saveMessage(inputData.getMessage().toDocument());
        // Present the saved message
        presenter.presentMessage(new ChatSaveOutputData(inputData.getMessage()));
    }

    @Override
    public void loadPreviousMessages(String username, String contact) {
        // Retrieve previous messages from data access
        List<ChatMessage> messages = chatDataAccess.getPreviousMessages(username, contact);
        List<ChatSaveOutputData> outputDataList = new ArrayList<>();
        for (ChatMessage message : messages) {
            outputDataList.add(new ChatSaveOutputData(message));
        }
        // Present the previous messages
        presenter.presentPreviousMessages(outputDataList);
//      // presenter.displayMessagesInView(outputDataList);
    }

}
