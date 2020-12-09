package net.coille.middleware.server;

import net.coille.middleware.server.arch.pullArch.PullServerImpl;
import net.coille.middleware.server.utils.GenericServer;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class PullServer {

    public static void main(String[] args) throws MalformedURLException, RemoteException {
        GenericServer genericServer = new GenericServer(new PullServerImpl());
        genericServer.initConnection();
    }

}