package com.rabbitmqdemo.model;

public class Message {
    private String id;
    private String content;

    // Default constructor (needed for JSON conversion)
    public Message() {
    }

    public Message(String id, String content) {
        this.id = id;
        this.content = content;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}