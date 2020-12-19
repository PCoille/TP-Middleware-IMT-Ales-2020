package net.coille.imt.middleware.client.arch.archDetection;

import net.coille.imt.middleware.client.ui.UIController;
import net.coille.imt.middleware.common.arch.ArchitectureEnum;

public interface ArchDetection {
    ArchitectureEnum getServerArchitecture();
    UIController getUIControllerClient();
}
