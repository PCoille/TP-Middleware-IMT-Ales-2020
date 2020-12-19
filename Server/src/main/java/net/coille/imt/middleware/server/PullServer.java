package net.coille.imt.middleware.server;

import net.coille.imt.middleware.server.arch.pull.PullServerImpl;
import net.coille.imt.middleware.server.utils.GenericServer;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class PullServer {

    public static void main(String[] args) throws MalformedURLException, RemoteException {
        GenericServer genericServer = new GenericServer(new PullServerImpl());
        genericServer.initConnection();
    }

}