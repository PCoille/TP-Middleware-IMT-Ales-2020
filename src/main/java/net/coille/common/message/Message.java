package net.coille.common.message;

import java.io.Serializable;

public abstract class Message implements Serializable {
    protected String sender;
    protected String rawMessage;

    protected Message(String sender, String rawMessage) {
        this.sender = sender;
        this.rawMessage = rawMessage;
    }

    public String getSender() {
        return sender;
    }

    public String getRawMessage() {
        return rawMessage;
    }
}
