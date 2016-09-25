package org.github.Elizaveta.hello.dao;

public class MessageWithAvatar {
    private Message message;
    private String avatar;

    public MessageWithAvatar(Message messages, String avatar) {
        this.message = messages;
        this.avatar = avatar;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
