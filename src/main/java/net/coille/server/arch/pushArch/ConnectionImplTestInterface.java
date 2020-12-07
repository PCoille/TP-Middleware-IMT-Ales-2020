package net.coille.server.arch.pushArch;

import net.coille.common.arch.pushArch.Connection;
import net.coille.common.arch.pushArch.Receiver;

import java.util.Map;

public interface ConnectionImplTestInterface extends Connection {
    public Map<String, Receiver> getReceiverMap();
}
