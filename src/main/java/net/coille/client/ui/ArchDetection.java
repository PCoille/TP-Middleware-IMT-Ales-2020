package net.coille.client.ui;

import net.coille.common.arch.ArchitectureEnum;

public interface ArchDetection {
    ArchitectureEnum getServerArchitecture();
    UIController getUIControllerClient();
}
