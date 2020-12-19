package net.coille.imt.middleware.server.utils;

import net.coille.imt.middleware.common.arch.ArchitectureEnum;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public interface Server {
    void serveConnection() throws RemoteException, MalformedURLException;
    ArchitectureEnum getArchitecture();
}
