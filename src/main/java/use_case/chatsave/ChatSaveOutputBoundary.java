package use_case.chatsave;

import java.util.List;

public interface ChatSaveOutputBoundary {
    void presentMessage(ChatSaveOutputData outputData);
    void presentPreviousMessages(List<ChatSaveOutputData> messages);
    // void displayMessagesInView(List<ChatSaveOutputData> messages);
}
