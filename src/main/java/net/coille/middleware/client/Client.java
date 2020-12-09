package net.coille.middleware.client;

import java.rmi.RemoteException;

import net.coille.middleware.client.ui.cli.CLIClient;


public class Client {

    public static void main(String[] args) throws RemoteException {
        CLIClient.main(args);
        System.exit(0);
    }

}

