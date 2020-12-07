package net.coille.common.arch.pushArch;

import net.coille.common.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Receiver extends Remote {
    void receive(Message message) throws RemoteException;
    void initClients(List<String> clients) throws RemoteException;
    void addClient(String client) throws RemoteException;
    void rmClient(String client) throws RemoteException;
}
