package net.coille.imt.middleware.server.arch.pull;

import net.coille.imt.middleware.common.arch.pull.Dialogue;
import net.coille.imt.middleware.common.message.GlobalMessageImpl;
import net.coille.imt.middleware.common.message.Message;
import net.coille.imt.middleware.common.message.PersonalMessageImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DialogueImpl extends UnicastRemoteObject implements Dialogue {
    // Maybe not abstract enough ?
    private ConnectionImpl connection;
    private List<Message> messages = new ArrayList<>();

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
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public List<String> getClients() {
        return new ArrayList<>(connection.getDialogueMap().keySet());
    }
}
