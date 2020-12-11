package net.coille.middleware.common.arch.p2p;

import net.coille.middleware.common.utils.ClientManager;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerConnection extends Remote {
    boolean connect(String nickname, ClientConnection connection, ClientManager manager) throws RemoteException;
    void disconnect(String nickname) throws RemoteException;
    void addGhostUser(String nickname) throws RemoteException;
    ClientConnection getClient(String nickname) throws RemoteException;
}
