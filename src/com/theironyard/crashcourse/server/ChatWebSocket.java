package com.theironyard.crashcourse.server;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.theironyard.crashcourse.entities.Channel;
import com.theironyard.crashcourse.entities.Message;
import com.theironyard.crashcourse.entities.User;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@WebSocket
public class ChatWebSocket {
    private static final Channel channel = new Channel();
    private static final Map<Session, User> connections = new ConcurrentHashMap<>();

    @OnWebSocketConnect
    public void connected(Session session) {
        User newUser = new User();

        channel.getUsers().add(newUser);
        connections.putIfAbsent(session, newUser);
    }

    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        channel.getUsers().remove(connections.get(session));
        connections.remove(session);
    }

    @OnWebSocketMessage
    public void message(Session session, String message) throws IOException {
        System.out.println("Got: " + message);   // Print message
        Message incomingMessage = null;

        try {
            incomingMessage = new Gson().fromJson(message, Message.class);
        } catch (JsonSyntaxException ex) {
            ex.printStackTrace();
        }

        if (incomingMessage != null) {
            connections.get(session).getMessages().add(incomingMessage);
            broadCastMessage(channel, incomingMessage);
        }
    }


    private void broadCastMessage(Channel channel, Message message) {
        channel.getUsers().forEach(user -> {
            try {
               Session userSession = connections.entrySet()
                       .stream()
                       .filter(es -> es.getValue() == user)
                       .findFirst().get()
                       .getKey();

                if (userSession.isOpen()) {
                    userSession.getRemote().sendString(new Gson().toJson(message));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
