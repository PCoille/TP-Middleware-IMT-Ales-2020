package net.coille.middleware.common.arch;

public enum ArchitectureEnum {
    PULL_ARCH("Pull"),
    PUSH_ARCH("Push"),
    P2P_ARCH("Peer to Peer");

    private String archName;

    ArchitectureEnum(String archName){
        this.archName = archName;
    }

    public String getArchName() {
        return archName;
    }
}
