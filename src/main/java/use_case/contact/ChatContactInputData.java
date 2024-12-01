package use_case.contact;

public class ChatContactInputData {
    private final String currentUsername;

    public ChatContactInputData(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}
