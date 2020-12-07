package net.coille.common.message;

import java.io.Serializable;

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
    public boolean equals(Object obj) {
        if (obj instanceof PersonalMessageImpl) {
            if (sender.equals(((PersonalMessageImpl) obj).sender) &&
                    rawMessage.equals(((PersonalMessageImpl) obj).rawMessage) &&
                    receiver.equals(((PersonalMessageImpl) obj).receiver)) {
                return true;
            }
        }
        return false;
    }
}
