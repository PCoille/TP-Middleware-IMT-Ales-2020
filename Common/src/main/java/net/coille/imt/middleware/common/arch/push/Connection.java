package net.coille.imt.middleware.common.arch.push;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Connection extends Remote {
    Emitter connect(String pseudo, Receiver receiver) throws RemoteException;
    void disconnect(String pseudo) throws RemoteException;
}
