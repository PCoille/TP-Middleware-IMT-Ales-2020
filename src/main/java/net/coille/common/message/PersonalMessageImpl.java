package net.coille.common.message;

import java.io.Serializable;

public class PersonalMessageImpl extends MessageImpl implements Serializable {
    private String receiver;

    public PersonalMessageImpl(String sender, String receiver, String rawMessage) {
        super(sender,rawMessage);
        this.receiver = receiver;
    }

    public PersonalMessageImpl(MessageImpl message, String receiver) {
        super(message);
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public boolean senderIsReceiver() {
        return this.getSender().equals(this.getReceiver());
    }

}
