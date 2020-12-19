package net.coille.middleware.server.arch.push;

import net.coille.middleware.common.arch.push.Emitter;
import net.coille.middleware.common.message.GlobalMessageImpl;
import net.coille.middleware.common.message.PersonalMessageImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EmitterImpl extends UnicastRemoteObject implements Emitter {
    private
    ConnectionImpl connection;

    public EmitterImpl(ConnectionImpl connection) throws RemoteException {
        super();

        this.connection = connection;
    }

    @Override
    public void sendMessage(PersonalMessageImpl message) throws RemoteException {
        if (connection.getReceiverMap().containsKey(message.getReceiver())) {
            connection.getReceiverMap().get(message.getReceiver()).receive(message);
        }
    }

    @Override
    public void sendMessageToAll(GlobalMessageImpl message) throws RemoteException {
        connection.getReceiverMap().forEach((s, receiver) -> {
            try {
                receiver.receive(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
}
