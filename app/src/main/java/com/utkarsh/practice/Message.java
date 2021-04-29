package com.utkarsh.practice;

public class Message {

    String userMessage;
    String name;
    String key;

    public Message () {}   //Default Constructor:

    public Message(String userMessage, String name) {
        this.userMessage = userMessage;
        this.name = name;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return  "Message{"+
                "userMessage='"+userMessage+'\''+
                ",name='" + name + '\''+
                ",key='" + key + '\'' +
                '}';
    }
}
