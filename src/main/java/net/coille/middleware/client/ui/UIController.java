package net.coille.middleware.client.ui;

import net.coille.middleware.common.message.GlobalMessageImpl;
import net.coille.middleware.common.message.Message;
import net.coille.middleware.common.message.PersonalMessageImpl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public interface UIController {
    void initSystem() throws RemoteException, NotBoundException, MalformedURLException;
    boolean initUser(String username) throws RemoteException;
    void exit(String username) throws RemoteException;
    List<Message> getMessages() throws RemoteException;
    void sendPersonalMessage(PersonalMessageImpl message) throws RemoteException;
    void sendMessageToAll(GlobalMessageImpl message) throws RemoteException;
    List<String> getUsers() throws RemoteException;
}
