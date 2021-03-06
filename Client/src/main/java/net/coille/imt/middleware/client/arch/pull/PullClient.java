package net.coille.imt.middleware.client.arch.pull;

import net.coille.imt.middleware.client.ui.UIController;
import net.coille.imt.middleware.common.arch.pull.Connection;
import net.coille.imt.middleware.common.arch.pull.Dialogue;
import net.coille.imt.middleware.common.message.GlobalMessageImpl;
import net.coille.imt.middleware.common.message.Message;
import net.coille.imt.middleware.common.message.PersonalMessageImpl;

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
        Dialogue tmpDialogue = connection.connect(username);
        if (tmpDialogue != null) {
            this.dialogue = tmpDialogue;
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
