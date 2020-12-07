package net.coille.server.arch.pushArch;

import net.coille.common.arch.ArchitectureEnum;
import net.coille.server.utils.Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class PushServerImpl implements Server {
    @Override
    public void serveConnection() throws RemoteException, MalformedURLException {
        ConnectionImpl connection = new ConnectionImpl();
        Naming.rebind("rmi://localhost:12357/Connection", connection);
    }

    @Override
    public ArchitectureEnum getArchitecture() {
        return ArchitectureEnum.PUSH_ARCH;
    }
}
