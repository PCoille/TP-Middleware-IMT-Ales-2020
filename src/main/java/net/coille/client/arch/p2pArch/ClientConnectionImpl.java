package net.coille.client.arch.p2pArch;

import net.coille.client.utils.ClientManagerImpl;
import net.coille.client.utils.MessageBoxImpl;
import net.coille.common.arch.p2pArch.ClientConnection;
import net.coille.common.utils.MessageBox;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientConnectionImpl extends UnicastRemoteObject implements ClientConnection {
    private ClientManagerImpl clientManager = new ClientManagerImpl();
    private MessageBoxImpl inbox = new MessageBoxImpl();
    //private Map<String, MessageBox> peersMessageBox;

    protected ClientConnectionImpl() throws RemoteException {
        super();
    }

    @Override
    public MessageBox connect(String nickname, MessageBox receiver) throws RemoteException {
        //peersMessageBox.put(nickname, receiver);
        return inbox;
    }

    public ClientManagerImpl getClientManager() {
        return clientManager;
    }

    public MessageBoxImpl getInbox() {
        return inbox;
    }
}
