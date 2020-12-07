package net.coille.client.arch.pushArch;

import net.coille.client.utils.ClientManagerImpl;
import net.coille.client.utils.MessageBoxImpl;
import net.coille.common.message.MessageImpl;
import net.coille.common.message.PersonalMessageImpl;
import net.coille.common.arch.pushArch.Receiver;
import net.coille.common.utils.ClientManager;
import net.coille.common.utils.MessageBox;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ReceiverImpl extends UnicastRemoteObject implements Receiver, MessageBox, ClientManager {
    private ClientManagerImpl clientManager = new ClientManagerImpl();
    private MessageBoxImpl messageBox = new MessageBoxImpl();

    protected ReceiverImpl() throws RemoteException {
        super();
    }

    @Override
    public void receive(MessageImpl message) throws RemoteException {
        messageBox.receive(message);
    }

    @Override
    public void initClients(List<String> clients) throws RemoteException {
        clientManager.initClients(clients);
    }

    @Override
    public void addClient(String client) throws RemoteException {
        clientManager.addClient(client);
    }

    @Override
    public void rmClient(String client) throws RemoteException {
        clientManager.rmClient(client);
    }

    public List<MessageImpl> getMessages() {
        return messageBox.getMessages();
    }

    public List<String> getClients() {
        return clientManager.getClients();
    }
}
