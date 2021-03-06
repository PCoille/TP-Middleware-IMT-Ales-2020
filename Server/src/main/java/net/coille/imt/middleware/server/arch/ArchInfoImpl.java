package net.coille.imt.middleware.server.arch;

import net.coille.imt.middleware.common.arch.ArchInfo;
import net.coille.imt.middleware.common.arch.ArchitectureEnum;

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
