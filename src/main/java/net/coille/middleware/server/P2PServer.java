package net.coille.middleware.server;

import net.coille.middleware.server.arch.p2p.P2PServerImpl;
import net.coille.middleware.server.utils.GenericServer;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class P2PServer {

    public static void main(String[] args) throws MalformedURLException, RemoteException {
        GenericServer genericServer = new GenericServer(new P2PServerImpl());
        genericServer.initConnection();
    }

}