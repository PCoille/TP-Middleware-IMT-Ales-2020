package net.coille.middleware.client.arch.pushArch;

import net.coille.middleware.client.ui.UIController;
import net.coille.middleware.common.message.GlobalMessageImpl;
import net.coille.middleware.common.message.Message;
import net.coille.middleware.common.message.PersonalMessageImpl;
import net.coille.middleware.common.arch.pushArch.Connection;
import net.coille.middleware.common.arch.pushArch.Emitter;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class PushClient implements UIController {
    private Connection connection;
    private ReceiverImpl receiver;
    private Emitter emitter;

    @Override
    public void initSystem() throws RemoteException, NotBoundException, MalformedURLException {
        this.connection = (Connection) Naming.lookup("rmi://localhost:12357/Connection");
        this.receiver = new ReceiverImpl();

        System.out.println("Push Client initialized");
    }

    @Override
    public boolean initUser(String username) throws RemoteException {
        Emitter emitter = connection.connect(username, receiver);
        if (emitter != null) {
            this.emitter = emitter;
            this.receiver.addClient(username);
            return true;
        }
        else return false;
    }

    @Override
    public void exit(String username) throws RemoteException {
        connection.disconnect(username);
    }

    @Override
    public List<Message> getMessages() {
        return receiver.getMessages();
    }

    @Override
    public void sendPersonalMessage(PersonalMessageImpl message) throws RemoteException {
        emitter.sendMessage(message);

        if(!message.senderIsReceiver()) {
            receiver.receive(message);
        }
    }

    @Override
    public void sendMessageToAll(GlobalMessageImpl message) throws RemoteException {
        emitter.sendMessageToAll(message);
    }

    @Override
    public List<String> getUsers() {
        return receiver.getClients();
    }


}
