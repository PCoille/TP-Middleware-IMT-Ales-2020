package net.coille.imt.middleware.client.ui;

import net.coille.imt.middleware.common.message.GlobalMessageImpl;
import net.coille.imt.middleware.common.message.Message;
import net.coille.imt.middleware.common.message.PersonalMessageImpl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class MockUIController implements UIController {
    String username;
    List<String> users = new ArrayList<>();
    List<Message> messages = new ArrayList<>();

    @Override
    public void initSystem() throws RemoteException, NotBoundException, MalformedURLException {
        users.add("u1");
        users.add("u2");
        users.add("u3");
    }

    @Override
    public boolean initUser(String username) throws RemoteException {
        if (!username.equals("admin")) {
            this.username = username;

            messages.add(new PersonalMessageImpl("u1", username, "Hello " + username));
            messages.add(new GlobalMessageImpl("u2", "Hi everyone. " + username + " is here."));

            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void exit(String username) throws RemoteException {
        // Nothing to exit, leaving method blank
    }

    @Override
    public List<Message> getMessages() throws RemoteException {
        return messages;
    }

    @Override
    public void sendPersonalMessage(PersonalMessageImpl message) throws RemoteException {
        messages.add(message);
    }

    @Override
    public void sendMessageToAll(GlobalMessageImpl message) throws RemoteException {
        messages.add(message);
    }

    @Override
    public List<String> getUsers() throws RemoteException {
        return users;
    }
}
