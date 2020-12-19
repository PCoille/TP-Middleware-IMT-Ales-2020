package net.coille.imt.middleware.common.utils;

import net.coille.imt.middleware.common.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageBox extends Remote {
    void receive(Message message) throws RemoteException;
}
