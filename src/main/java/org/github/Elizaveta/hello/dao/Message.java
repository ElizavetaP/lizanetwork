package org.github.Elizaveta.hello.dao;

public class Message {
    private int senderID;
    private int recipientID;
    private String date;
    private long messageID;
    private String type;
    private String text;

    public Message(int senderID, int recipientID, String date, long messageID, String type, String text) {
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.date = date;
        this.messageID = messageID;
        this.type = type;
        this.text = text;
    }

    public int getSenderID() {
        return senderID;
    }

    public int getRecipientID() {
        return recipientID;
    }

    public String getDate() {
        return date;
    }

    public long getMessageID() {
        return messageID;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
