package net.coille.imt.middleware.client.arch.p2p;

import net.coille.imt.middleware.client.utils.ClientManagerImpl;
import net.coille.imt.middleware.client.utils.MessageBoxImpl;
import net.coille.imt.middleware.common.arch.p2p.ClientConnection;
import net.coille.imt.middleware.common.utils.MessageBox;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientConnectionImpl extends UnicastRemoteObject implements ClientConnection {
    protected ClientManagerImpl clientManager = new ClientManagerImpl();
    protected MessageBoxImpl inbox = new MessageBoxImpl();
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
