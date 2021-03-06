package net.coille.imt.middleware.client.arch.push;

import net.coille.imt.middleware.client.utils.ClientManagerImpl;
import net.coille.imt.middleware.client.utils.MessageBoxImpl;
import net.coille.imt.middleware.common.arch.push.Receiver;
import net.coille.imt.middleware.common.message.Message;
import net.coille.imt.middleware.common.utils.ClientManager;
import net.coille.imt.middleware.common.utils.MessageBox;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ReceiverImpl extends UnicastRemoteObject implements Receiver, MessageBox, ClientManager {
    protected ClientManagerImpl clientManager = new ClientManagerImpl();
    protected MessageBoxImpl messageBox = new MessageBoxImpl();

    protected ReceiverImpl() throws RemoteException {
        super();
    }

    @Override
    public void receive(Message message) throws RemoteException {
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

    public List<Message> getMessages() {
        return messageBox.getMessages();
    }

    public List<String> getClients() {
        return clientManager.getClients();
    }
}
