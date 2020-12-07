package net.coille.common.arch.pushArch;

import net.coille.common.message.GlobalMessageImpl;
import net.coille.common.message.PersonalMessageImpl;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Emitter extends Remote {
    void sendMessage(PersonalMessageImpl message) throws RemoteException;
    void sendMessageToAll(GlobalMessageImpl message) throws RemoteException;
}
