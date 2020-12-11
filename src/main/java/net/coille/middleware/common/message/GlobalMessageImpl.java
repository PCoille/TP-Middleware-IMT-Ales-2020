package net.coille.middleware.common.message;

import java.io.Serializable;

public class GlobalMessageImpl extends Message implements Serializable {
    public GlobalMessageImpl(String sender, String rawMessage) {
        super(sender, rawMessage);
    }
}
