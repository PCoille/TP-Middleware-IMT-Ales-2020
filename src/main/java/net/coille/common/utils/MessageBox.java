package net.coille.common.utils;

import net.coille.common.message.MessageImpl;
import net.coille.common.message.PersonalMessageImpl;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageBox extends Remote {
    void receive(MessageImpl message) throws RemoteException;
}
