package net.coille.middleware.common.utils;

import net.coille.middleware.common.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageBox extends Remote {
    void receive(Message message) throws RemoteException;
}
