package net.coille.middleware.common.arch.pull;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Connection extends Remote {
    Dialogue connect(String pseudo) throws RemoteException;
    void disconnect(String pseudo) throws RemoteException;
}
