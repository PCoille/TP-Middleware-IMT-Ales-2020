package net.coille.imt.middleware.server;

import net.coille.imt.middleware.common.arch.ArchitectureEnum;
import net.coille.imt.middleware.server.arch.p2p.P2PServerImpl;
import net.coille.imt.middleware.server.arch.pull.PullServerImpl;
import net.coille.imt.middleware.server.arch.push.PushServerImpl;
import net.coille.imt.middleware.server.utils.GenericServer;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class MainServer {
    // May be useful for an implementation of a server GUI
    static GenericServer generateServer(ArchitectureEnum architectureEnum) {
        switch (architectureEnum){
            case P2P_ARCH:
                return new GenericServer(new P2PServerImpl());
            case PUSH_ARCH:
                return new GenericServer(new PushServerImpl());
            case PULL_ARCH:
                return new GenericServer(new PullServerImpl());
            default:
                return null;
        }
    }

    static ArchitectureEnum checkArch(String archString){
        switch (archString.toLowerCase()) {
            case "p2p":
            case "--p2p":
                return ArchitectureEnum.P2P_ARCH;
            case "push":
            case "--push":
                return ArchitectureEnum.PUSH_ARCH;
            case "pull":
            case "--pull":
                return ArchitectureEnum.PULL_ARCH;
            default:
                return null;
        }
    }

    static GenericServer generateServerFromString(String archString) {
        ArchitectureEnum archEnum = checkArch(archString);
        if(archEnum == null) {
            return null;
        }
        else {
            return generateServer(archEnum);
        }
    }

    static GenericServer getServerFromUserInput() {
        GenericServer genericServer = null;
        Scanner scanner = new Scanner(System.in);

        while (genericServer == null) {
            System.out.println("Choose one architecture from \"p2p\", \"push\", \"pull\"");
            String archString = scanner.nextLine();
            genericServer = generateServerFromString(archString);
            if (genericServer == null) {
                System.out.println("Please enter a valid architecture name");
            }
        }

        return genericServer;
    }

    public static void main(String[] args) throws MalformedURLException, RemoteException {
        GenericServer genericServer = null;
        if(args.length > 0) {
            genericServer = generateServerFromString(args[0]);
        }

        if (genericServer == null) {
            genericServer = getServerFromUserInput();
        }

        genericServer.initConnection();
    }
}
