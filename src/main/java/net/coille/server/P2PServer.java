package net.coille.server;

import net.coille.server.arch.p2pArch.P2PServerImpl;
import net.coille.server.utils.GenericServer;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class P2PServer {

    public static void main(String[] args) throws MalformedURLException, RemoteException {
        GenericServer genericServer = new GenericServer(new P2PServerImpl());
        genericServer.initConnection();
    }

}