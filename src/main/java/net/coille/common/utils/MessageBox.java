package net.coille.common.utils;

import net.coille.common.message.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageBox extends Remote {
    void receive(Message message) throws RemoteException;
}
