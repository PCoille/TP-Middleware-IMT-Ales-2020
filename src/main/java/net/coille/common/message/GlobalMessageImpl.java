package net.coille.common.message;

import java.io.Serializable;

public class GlobalMessageImpl extends Message implements Serializable {
    public GlobalMessageImpl(String sender, String rawMessage) {
        super(sender, rawMessage);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GlobalMessageImpl) {
            if (sender.equals(((GlobalMessageImpl) obj).sender) &&
                rawMessage.equals(((GlobalMessageImpl) obj).rawMessage)) {
                return true;
            }
        }
        return false;
    }
}
