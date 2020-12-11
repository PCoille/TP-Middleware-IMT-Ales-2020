package net.coille.middleware.server.arch.push;

import net.coille.middleware.common.arch.push.Connection;
import net.coille.middleware.common.arch.push.Emitter;
import net.coille.middleware.common.arch.push.Receiver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConnectionImpl extends UnicastRemoteObject implements Connection {
    private transient Map<String, Emitter> emitterMap = new HashMap<>();
    private transient Map<String, Receiver> receiverMap = new HashMap<>();

    public ConnectionImpl() throws RemoteException {
        super();
    }

    @Override
    public Emitter connect(String pseudo, Receiver receiver) throws RemoteException {
        if (!(emitterMap.containsKey(pseudo) || receiverMap.containsKey(pseudo))){
            emitterMap.put(pseudo, new EmitterImpl(this));
            initializeReceiver(pseudo, receiver);
            receiverMap.put(pseudo, receiver);
            return emitterMap.get(pseudo);
        }
        else return null;
    }

    private void initializeReceiver(String pseudo, Receiver receiver) throws RemoteException {
        receiver.initClients(new ArrayList<>(receiverMap.keySet()));
        receiverMap.forEach((s, receiver1) -> {
            try {
                receiver1.addClient(pseudo);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void disconnect(String pseudo) {
        emitterMap.remove(pseudo);
        receiverMap.remove(pseudo);

        receiverMap.forEach((s, receiver) -> {
            try {
                receiver.rmClient(pseudo);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    public Map<String, Emitter> getEmitterMap() {
        return emitterMap;
    }
    public Map<String, Receiver> getReceiverMap() {
        return receiverMap;
    }
}
