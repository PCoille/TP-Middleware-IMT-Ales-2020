package net.coille.middleware.client.utils;

import net.coille.middleware.common.utils.ClientManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ClientManagerImpl extends UnicastRemoteObject implements ClientManager {
    protected transient List<String> clientsList = new ArrayList<>();

    public ClientManagerImpl() throws RemoteException {
        super();
    }

    @Override
    public void initClients(List<String> clients) {
        clientsList.addAll(clients);
    }

    @Override
    public void addClient(String client) {
        clientsList.add(client);
    }

    @Override
    public void rmClient(String client) {
        clientsList.remove(client);
    }

    public List<String> getClients() {
        return clientsList;
    }
}
