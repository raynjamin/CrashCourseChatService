package com.theironyard.crashcourse.server;

import com.google.gson.Gson;
import com.theironyard.crashcourse.entities.Message;
import spark.Spark;

public class Main {

    public static void main(String[] args) {
        Spark.webSocket("/ws", ChatWebSocket.class);
        Spark.init();
    }
}
