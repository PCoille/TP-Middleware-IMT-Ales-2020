package net.coille.imt.middleware.server.utils;

import net.coille.imt.middleware.common.arch.ArchInfo;
import net.coille.imt.middleware.server.arch.ArchInfoImpl;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GenericServer {
    Server server;

    public GenericServer(Server server) {
        this.server = server;
    }

    public void initConnection() throws RemoteException, MalformedURLException {
        // registry creation
        Registry registry = LocateRegistry.createRegistry(12357);

        server.serveConnection();
        ArchInfo archInfo = new ArchInfoImpl(server.getArchitecture());
        archInfo.serveArch();

        System.out.println(server.getArchitecture().getArchName() + " Serveur actif");
    }
}
