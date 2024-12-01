package use_case.contact;

import java.util.List;

public class ChatContactOutputData {
    private final List<String> contactList;

    public ChatContactOutputData(List<String> contactList) {
        this.contactList = contactList;
    }

    public List<String> getContactList() {
        return contactList;
    }
}
