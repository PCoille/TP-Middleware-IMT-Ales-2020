package net.coille.middleware.server.arch;

import net.coille.middleware.common.arch.ArchitectureEnum;
import net.coille.middleware.common.arch.ArchInfo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ArchInfoImpl extends UnicastRemoteObject implements ArchInfo {
    ArchitectureEnum architecture;

    public ArchInfoImpl(ArchitectureEnum architecture) throws RemoteException {
        super();
        this.architecture = architecture;
    }

    @Override
    public void serveArch() throws MalformedURLException, RemoteException {
        //publication of component reference in the registry
        Naming.rebind("rmi://localhost:12357/ArchInfo", this);
    }

    @Override
    public ArchitectureEnum getArchitecture() {
        return architecture;
    }
}
