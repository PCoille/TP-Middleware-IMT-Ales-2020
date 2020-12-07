package net.coille.common.arch.p2pArch;

import net.coille.common.utils.MessageBox;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientConnection extends Remote {
    MessageBox connect(String nickname, MessageBox receiver) throws RemoteException;
}
