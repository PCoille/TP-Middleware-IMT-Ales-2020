package net.coille.server.arch.pullArch;

import net.coille.common.message.GlobalMessageImpl;
import net.coille.common.message.MessageImpl;
import net.coille.common.message.PersonalMessageImpl;
import net.coille.common.arch.pullArch.Dialogue;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class DialogueImpl extends UnicastRemoteObject implements Dialogue {
    // Maybe not abstract enough ?
    private ConnectionImpl connection;
    private List<MessageImpl> messages = new ArrayList<>();

    protected DialogueImpl(ConnectionImpl connection) throws RemoteException {
        super();

        this.connection = connection;
    }

    @Override
    public void sendMessage(PersonalMessageImpl message) throws RemoteException {
        if (connection.getDialogueMap().containsKey(message.getReceiver())){
            connection.getDialogueMap().get(message.getReceiver()).getMessages().add(message);
            // Send a copy to the sender if he is not the receiver
            if (!message.senderIsReceiver()) {
                connection.getDialogueMap().get(message.getSender()).getMessages().add(message);
            }
        }
    }

    @Override
    public void sendMessageToAll(GlobalMessageImpl message) throws RemoteException {
        connection.getDialogueMap().forEach((s, dialogue) -> {
            try {
                dialogue.getMessages().add(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public List<MessageImpl> getMessages() {
        return messages;
    }

    @Override
    public List<String> getClients() {
        return new ArrayList<>(connection.getDialogueMap().keySet());
    }
}
