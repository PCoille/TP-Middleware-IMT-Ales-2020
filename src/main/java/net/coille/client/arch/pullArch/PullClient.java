package net.coille.client.arch.pullArch;

import net.coille.client.ui.UIController;
import net.coille.common.message.GlobalMessageImpl;
import net.coille.common.message.Message;
import net.coille.common.message.PersonalMessageImpl;
import net.coille.common.arch.pullArch.Connection;
import net.coille.common.arch.pullArch.Dialogue;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class PullClient implements UIController {
    private Connection connection;
    private Dialogue dialogue;

    @Override
    public void initSystem() throws RemoteException, NotBoundException, MalformedURLException {
        this.connection = (Connection) Naming.lookup("rmi://localhost:12357/Connection");
        System.out.println("Pull Client initialized");
    }

    @Override
    public boolean initUser(String username) throws RemoteException {
        Dialogue dialogue = connection.connect(username);
        if (dialogue != null) {
            this.dialogue = dialogue;
            return true;
        }
        else return false;
    }

    @Override
    public void exit(String username) throws RemoteException {
        connection.disconnect(username);
    }

    @Override
    public List<Message> getMessages() throws RemoteException {
        return dialogue.getMessages();
    }

    @Override
    public void sendPersonalMessage(PersonalMessageImpl message) throws RemoteException {
        dialogue.sendMessage(message);
    }

    @Override
    public void sendMessageToAll(GlobalMessageImpl message) throws RemoteException {
        dialogue.sendMessageToAll(message);
    }

    @Override
    public List<String> getUsers() throws RemoteException {
        return dialogue.getClients();
    }
}
