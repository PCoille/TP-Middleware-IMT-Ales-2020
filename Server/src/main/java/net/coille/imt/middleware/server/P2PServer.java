package net.coille.imt.middleware.server;

import net.coille.imt.middleware.server.arch.p2p.P2PServerImpl;
import net.coille.imt.middleware.server.utils.GenericServer;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class P2PServer {

    public static void main(String[] args) throws MalformedURLException, RemoteException {
        GenericServer genericServer = new GenericServer(new P2PServerImpl());
        genericServer.initConnection();
    }

}