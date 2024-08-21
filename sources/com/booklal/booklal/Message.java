package com.booklal.booklal;

public class Message {
    private int feeling = -1;
    private String message;
    private String messageId;
    private String senderId;
    private long timestamp;

    public Message() {
    }

    public Message(String str, String str2, long j) {
        this.message = str;
        this.senderId = str2;
        this.timestamp = j;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String str) {
        this.messageId = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public void setSenderId(String str) {
        this.senderId = str;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public int getFeeling() {
        return this.feeling;
    }

    public void setFeeling(int i) {
        this.feeling = i;
    }
}
