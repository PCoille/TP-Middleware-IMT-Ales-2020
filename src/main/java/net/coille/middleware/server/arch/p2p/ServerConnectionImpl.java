package net.coille.middleware.server.arch.p2p;

import net.coille.middleware.common.arch.p2p.ClientConnection;
import net.coille.middleware.common.arch.p2p.ServerConnection;
import net.coille.middleware.common.utils.ClientManager;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ServerConnectionImpl extends UnicastRemoteObject implements ServerConnection {
    private transient Map<String, ClientManager> clientManagerMap = new HashMap<>();
    private transient Map<String, ClientConnection> clientConnectionMap = new HashMap<>();
    // Users that do not respond. The users in this list will be deleted later.
    private transient List<String> ghostUsers = new ArrayList<>();

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

        clientManagerMap.forEach((s, clientManager) -> {
            if(!ghostUsers.contains(getUsernameFromClientManager(clientManager))) {
                try {
                    clientManager.rmClient(nickname);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
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
                .orElse(new AbstractMap.SimpleEntry<>(null, null))
                .getKey();
    }

    private void initializeClientManager(String pseudo, ClientManager clientManager) throws RemoteException {
        clientManager.initClients(new ArrayList<>(clientManagerMap.keySet()));
        clientManagerMap.forEach((s, clientManager1) -> {
            try {
                clientManager1.addClient(pseudo);
            } catch (ConnectException e) {
                String pbUser = getUsernameFromClientManager(clientManager1);

                if (pbUser != null) {
                    System.out.println("Cannot connect to " + pbUser);
                    // Preparing problematic user for removal
                    ghostUsers.add(pbUser);
                }



            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        removeGhostUsers();
    }
}
