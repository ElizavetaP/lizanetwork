package org.github.Elizaveta.hello;

public class Message {
    private long ID_sender;
    private long ID_recipient;
    private String date;
    private long ID_message;
    private String type;
    private String text;

    public Message(long ID_sender, long ID_recipient, String date, long ID_message, String type, String text) {
        this.ID_sender = ID_sender;
        this.ID_recipient = ID_recipient;
        this.date = date;
        this.ID_message = ID_message;
        this.type = type;
        this.text = text;
    }

    public long getID_sender() {
        return ID_sender;
    }

    public long getID_recipient() {
        return ID_recipient;
    }

    public String getDate() {
        return date;
    }

    public long getID_message() {
        return ID_message;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
