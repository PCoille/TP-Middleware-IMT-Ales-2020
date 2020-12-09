package net.coille.middleware.server.arch.p2pArch;

import net.coille.middleware.common.arch.p2pArch.ClientConnection;
import net.coille.middleware.common.arch.p2pArch.ServerConnection;
import net.coille.middleware.common.utils.ClientManager;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ServerConnectionImpl extends UnicastRemoteObject implements ServerConnection {
    private Map<String, ClientManager> clientManagerMap = new HashMap<>();
    private Map<String, ClientConnection> clientConnectionMap = new HashMap<>();
    // Users that do not respond. The users in this list will be deleted later.
    private List<String> ghostUsers = new ArrayList<>();

    protected ServerConnectionImpl() throws RemoteException {
        super();
    }

    private void removeGhostUsers(){
        ghostUsers.forEach(s -> {
            try {
                disconnect(s);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        ghostUsers.clear();
    }



    @Override
    public boolean connect(String nickname, ClientConnection connection, ClientManager manager) throws RemoteException {
        if (!(clientManagerMap.containsKey(nickname) || clientConnectionMap.containsKey(nickname))){
            initializeClientManager(nickname, manager);
            clientManagerMap.put(nickname, manager);
            clientConnectionMap.put(nickname, connection);
            System.out.println(nickname + " connected.");

            removeGhostUsers();
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void disconnect(String nickname) throws RemoteException {
        clientManagerMap.remove(nickname);
        clientConnectionMap.remove(nickname);

        // TODO: Change this so it does not try to remove from "ghost" users
        clientManagerMap.forEach((s, clientManager) -> {
            try {
                clientManager.rmClient(nickname);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        System.out.println(nickname + " disconnected.");
    }

    @Override
    public void addGhostUser(String nickname) throws RemoteException {
        ghostUsers.add(nickname);
    }

    @Override
    public ClientConnection getClient(String nickname) throws RemoteException {
        return clientConnectionMap.get(nickname);
    }

    private String getUsernameFromClientManager(ClientManager clientManager) {
        return this.clientManagerMap.entrySet()
                .stream()
                .filter(stringClientManagerEntry -> clientManager.equals(stringClientManagerEntry.getValue()))
                .findFirst()
                .get()
                .getKey();
    }

    private void initializeClientManager(String pseudo, ClientManager clientManager) throws RemoteException {
        clientManager.initClients(new ArrayList<>(clientManagerMap.keySet()));
        clientManagerMap.forEach((s, clientManager1) -> {
            try {
                clientManager1.addClient(pseudo);
            } catch (ConnectException e) {
                String pbUser = getUsernameFromClientManager(clientManager1);

                System.out.println("Cannot connect to " + pbUser);
                // Preparing problematic user for removal
                ghostUsers.add(pbUser);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
}
