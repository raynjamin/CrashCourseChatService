package com.theironyard.crashcourse.entities;

import java.util.Date;

/**
 * Represents a message sent to the backend.
 */
public class Message {
    private String type;
    private String text;
    private String name;
    private Date dateTime;

    public Message(String type, String text, String name) {
        this.type = type;
        this.text = text;
        this.name = name;

        dateTime = new Date();
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
