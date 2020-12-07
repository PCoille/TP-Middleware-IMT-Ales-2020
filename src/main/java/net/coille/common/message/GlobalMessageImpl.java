package net.coille.common.message;

import java.io.Serializable;

public class GlobalMessageImpl extends MessageImpl implements Serializable {
    public GlobalMessageImpl(String sender, String rawMessage) {
        super(sender, rawMessage);
    }
}
