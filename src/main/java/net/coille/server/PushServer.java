package net.coille.server;

import net.coille.server.arch.pushArch.PushServerImpl;
import net.coille.server.utils.GenericServer;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class PushServer {

    public static void main(String[] args) throws MalformedURLException, RemoteException {
        GenericServer genericServer = new GenericServer(new PushServerImpl());
        genericServer.initConnection();
    }

}