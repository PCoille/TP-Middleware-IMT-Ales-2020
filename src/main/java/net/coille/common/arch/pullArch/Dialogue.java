package net.coille.common.arch.pullArch;

import net.coille.common.message.GlobalMessageImpl;
import net.coille.common.message.MessageImpl;
import net.coille.common.message.PersonalMessageImpl;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Dialogue extends Remote {
    void sendMessage(PersonalMessageImpl message) throws RemoteException;
    void sendMessageToAll(GlobalMessageImpl message) throws RemoteException;
    List<MessageImpl> getMessages() throws RemoteException;
    List<String> getClients() throws RemoteException;
}
