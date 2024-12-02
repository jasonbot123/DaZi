package use_case.chatsave;

import java.util.List;

public interface ChatSaveInputBoundary {
    void sendMessage(ChatSaveInputData inputData);
    void loadPreviousMessages(String username, String receiver);
}
