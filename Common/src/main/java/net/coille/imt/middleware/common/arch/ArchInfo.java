package net.coille.imt.middleware.common.arch;

import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ArchInfo extends Remote {
    void serveArch() throws MalformedURLException, RemoteException;
    ArchitectureEnum getArchitecture() throws RemoteException;
}
