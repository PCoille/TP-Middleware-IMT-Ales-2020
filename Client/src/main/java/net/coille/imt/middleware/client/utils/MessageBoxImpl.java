package net.coille.imt.middleware.client.utils;

import net.coille.imt.middleware.common.message.Message;
import net.coille.imt.middleware.common.utils.MessageBox;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class MessageBoxImpl extends UnicastRemoteObject implements MessageBox {
    protected transient List<Message> messageList = new ArrayList<>();

    public MessageBoxImpl() throws RemoteException {
        super();
    }

    @Override
    public void receive(Message message) {
        messageList.add(message);
    }

    public List<Message> getMessages() {
        return messageList;
    }
}
