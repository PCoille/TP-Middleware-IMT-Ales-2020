package net.coille.middleware.client;

import net.coille.middleware.client.ui.cli.CLIClient;

import java.rmi.RemoteException;


public class Client {

    public static void main(String[] args) throws RemoteException {
        CLIClient.main(args);
        System.exit(0);
    }

}

