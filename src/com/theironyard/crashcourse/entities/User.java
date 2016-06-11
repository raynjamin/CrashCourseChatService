package com.theironyard.crashcourse.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 6/11/16.
 */
public class User {
    private List<Message> messages;

    public User() {
        messages = new ArrayList<>();
    }

    public User(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
