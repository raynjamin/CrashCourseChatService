package com.theironyard.crashcourse.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a chat channel.
 */
public class Channel {
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    private List<User> users;

    public Channel() {
        users = new ArrayList<User>();
    }

    public List<Message> displayRecentMessages() {
        return users.stream().flatMap(u -> u.getMessages().stream())
                .sorted((a, b) -> (int)(a.getDateTime().getTime() - b.getDateTime().getTime()))
                .limit(50)
                .collect(Collectors.toList());
    }
}
