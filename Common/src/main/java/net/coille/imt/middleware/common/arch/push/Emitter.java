package net.coille.imt.middleware.common.arch.push;

import net.coille.imt.middleware.common.message.GlobalMessageImpl;
import net.coille.imt.middleware.common.message.PersonalMessageImpl;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Emitter extends Remote {
    void sendMessage(PersonalMessageImpl message) throws RemoteException;
    void sendMessageToAll(GlobalMessageImpl message) throws RemoteException;
}
