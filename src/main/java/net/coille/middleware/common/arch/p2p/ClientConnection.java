package net.coille.middleware.common.arch.p2p;

import net.coille.middleware.common.utils.MessageBox;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientConnection extends Remote {
    MessageBox connect(String nickname, MessageBox receiver) throws RemoteException;
}
