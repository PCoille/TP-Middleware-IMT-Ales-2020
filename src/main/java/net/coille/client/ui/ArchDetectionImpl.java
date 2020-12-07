package net.coille.client.ui;

import net.coille.client.arch.p2pArch.P2PClient;
import net.coille.client.arch.pullArch.PullClient;
import net.coille.client.arch.pushArch.PushClient;
import net.coille.common.arch.ArchInfo;
import net.coille.common.arch.ArchitectureEnum;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ArchDetectionImpl implements ArchDetection {
    ArchitectureEnum architecture;

    public ArchDetectionImpl() throws RemoteException, NotBoundException, MalformedURLException {
        ArchInfo archInfo = (ArchInfo) Naming.lookup("rmi://localhost:12357/ArchInfo");
        architecture = archInfo.getArchitecture();
    }


    @Override
    public ArchitectureEnum getServerArchitecture() {
        return architecture;
    }

    @Override
    public UIController getUIControllerClient() {
        switch (architecture){
            case PULL_ARCH:
                return new PullClient();

            case PUSH_ARCH:
                return new PushClient();

            case P2P_ARCH:
                return new P2PClient();

            default:
                System.out.println("Architecture info not found. Loading default config.");
                return new PushClient();
        }
    }
}
