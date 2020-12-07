package net.coille.common.utils;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientManager extends Remote {
    void initClients(List<String> clients) throws RemoteException;
    void addClient(String client) throws RemoteException;
    void rmClient(String client) throws RemoteException;
}
