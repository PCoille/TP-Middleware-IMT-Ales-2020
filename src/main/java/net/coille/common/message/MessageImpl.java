package net.coille.common.message;

import java.io.Serializable;

public class MessageImpl implements Serializable {
    private String sender;
    private String rawMessage;

    protected MessageImpl(String sender, String rawMessage) {
        this.sender = sender;
        this.rawMessage = rawMessage;
    }

    public MessageImpl(MessageImpl message) {
        this.sender = message.sender;
        this.rawMessage = message.rawMessage;
    }

    public String getSender() {
        return sender;
    }

    public String getRawMessage() {
        return rawMessage;
    }
}
