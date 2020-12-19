package net.coille.imt.middleware.common.message;

import java.io.Serializable;
import java.util.Objects;

public abstract class Message implements Serializable {
    protected String sender;
    protected String rawMessage;

    protected Message(String sender, String rawMessage) {
        this.sender = sender;
        this.rawMessage = rawMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!Objects.equals(sender, message.sender)) return false;
        return Objects.equals(rawMessage, message.rawMessage);
    }

    @Override
    public int hashCode() {
        int result = sender != null ? sender.hashCode() : 0;
        result = 31 * result + (rawMessage != null ? rawMessage.hashCode() : 0);
        return result;
    }

    public String getSender() {
        return sender;
    }

    public String getRawMessage() {
        return rawMessage;
    }
}
