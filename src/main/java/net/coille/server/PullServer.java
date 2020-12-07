package net.coille.server;

import net.coille.server.arch.pullArch.PullServerImpl;
import net.coille.server.utils.GenericServer;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class PullServer {

    public static void main(String[] args) throws MalformedURLException, RemoteException {
        GenericServer genericServer = new GenericServer(new PullServerImpl());
        genericServer.initConnection();
    }

}