package net.coille.imt.middleware.client;

import net.coille.imt.middleware.client.ui.cli.CLIClient;

import java.rmi.RemoteException;


public class Client {

    public static void main(String[] args) throws RemoteException {
        CLIClient.main(args);
        System.exit(0);
    }

}

