package net.coille.client.arch.p2pArch;

import net.coille.client.ui.UIController;
import net.coille.common.message.GlobalMessageImpl;
import net.coille.common.message.Message;
import net.coille.common.message.PersonalMessageImpl;
import net.coille.common.arch.p2pArch.ServerConnection;

import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class P2PClient implements UIController {
    private ClientConnectionImpl clientConnection;
    private ServerConnection serverConnection;

    @Override
    public void initSystem() throws RemoteException, NotBoundException, MalformedURLException {
        this.serverConnection = (ServerConnection) Naming.lookup("rmi://localhost:12357/Connection");;
        this.clientConnection = new ClientConnectionImpl();
        System.out.println("P2P Client initialized");
    }

    @Override
    public boolean initUser(String username) throws RemoteException {
        if(this.serverConnection.connect(username, clientConnection, clientConnection.getClientManager())){
            this.clientConnection.getClientManager().addClient(username);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void exit(String username) throws RemoteException {
        serverConnection.disconnect(username);
    }

    @Override
    public List<Message> getMessages() throws RemoteException {
        return clientConnection.getInbox().getMessages();
    }

    @Override
    public void sendPersonalMessage(PersonalMessageImpl message) throws RemoteException {
        if (!(serverConnection.getClient(message.getReceiver()) == null)) {
            try {
                serverConnection.getClient(message.getReceiver())
                        .connect(message.getSender(), clientConnection.getInbox())
                        .receive(message);

                if (!message.senderIsReceiver()) {
                    clientConnection.getInbox().receive(message);
                }

            } catch (ConnectException e) {
                System.out.println("Cannot connect to " + message.getReceiver());
                serverConnection.addGhostUser(message.getReceiver());
            }
        }
        else {
            System.out.println("Client " + message.getReceiver() + " not found.");
        }
    }

    @Override
    public void sendMessageToAll(GlobalMessageImpl message) throws RemoteException {
        clientConnection.getClientManager()
                .getClients()
                .forEach(s -> {
                    try {
                        serverConnection.getClient(s)
                                .connect(message.getSender(), clientConnection.getInbox())
                                .receive(message);
                    } catch (ConnectException e) {
                        System.out.println("Cannot connect to " + s);
                        try {
                            serverConnection.addGhostUser(s);
                        } catch (RemoteException ex) {
                            ex.printStackTrace();
                        }
                    }
                    catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });


    }

    @Override
    public List<String> getUsers() throws RemoteException {
        return clientConnection.getClientManager().getClients();
    }
}
