package net.coille.middleware.server;

import net.coille.middleware.server.arch.pushArch.PushServerImpl;
import net.coille.middleware.server.utils.GenericServer;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class PushServer {

    public static void main(String[] args) throws MalformedURLException, RemoteException {
        GenericServer genericServer = new GenericServer(new PushServerImpl());
        genericServer.initConnection();
    }

}