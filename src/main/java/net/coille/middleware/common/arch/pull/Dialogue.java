package net.coille.middleware.common.arch.pull;

import net.coille.middleware.common.message.GlobalMessageImpl;
import net.coille.middleware.common.message.Message;
import net.coille.middleware.common.message.PersonalMessageImpl;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Dialogue extends Remote {
    void sendMessage(PersonalMessageImpl message) throws RemoteException;
    void sendMessageToAll(GlobalMessageImpl message) throws RemoteException;
    List<Message> getMessages() throws RemoteException;
    List<String> getClients() throws RemoteException;
}
