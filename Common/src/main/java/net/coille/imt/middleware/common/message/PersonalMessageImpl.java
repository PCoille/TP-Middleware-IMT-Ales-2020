package net.coille.imt.middleware.common.message;

import java.io.Serializable;
import java.util.Objects;

public class PersonalMessageImpl extends Message implements Serializable {
    protected String receiver;

    public PersonalMessageImpl(String sender, String receiver, String rawMessage) {
        super(sender,rawMessage);
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public boolean senderIsReceiver() {
        return this.getSender().equals(this.getReceiver());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PersonalMessageImpl message = (PersonalMessageImpl) o;

        return Objects.equals(receiver, message.receiver);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        return result;
    }
}
