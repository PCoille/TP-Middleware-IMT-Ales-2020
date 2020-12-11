package net.coille.middleware.server.arch.pull;

import net.coille.middleware.common.arch.pull.Connection;
import net.coille.middleware.common.arch.pull.Dialogue;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ConnectionImpl extends UnicastRemoteObject implements Connection {
    private transient Map<String,Dialogue> dialogueMap = new HashMap<>();

    public ConnectionImpl() throws RemoteException {
        super();
    }

    @Override
    public Dialogue connect(String pseudo) throws RemoteException {
        if (!dialogueMap.containsKey(pseudo)){
            dialogueMap.put(pseudo, new DialogueImpl(this));
            return dialogueMap.get(pseudo);
        }
        else return null;
    }

    @Override
    public void disconnect(String pseudo) {
        dialogueMap.remove(pseudo);
    }

    public Map<String, Dialogue> getDialogueMap() {
        return dialogueMap;
    }
}
