package net.coille.client.utils;

import net.coille.common.message.MessageImpl;
import net.coille.common.message.PersonalMessageImpl;
import net.coille.common.utils.MessageBox;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class MessageBoxImpl extends UnicastRemoteObject implements MessageBox {
    private List<MessageImpl> messageList = new ArrayList<>();

    public MessageBoxImpl() throws RemoteException {
        super();
    }

    @Override
    public void receive(MessageImpl message) {
        messageList.add(message);
    }

    public List<MessageImpl> getMessages() {
        return messageList;
    }
}
