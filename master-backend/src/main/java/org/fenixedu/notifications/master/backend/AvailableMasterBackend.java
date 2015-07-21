package org.fenixedu.notifications.master.backend;

import java.util.Optional;

public class AvailableMasterBackend {

    private static MasterBackend registeredBackend;

    private AvailableMasterBackend() {
    }

    public static void registerMasterBackend(MasterBackend backend) {
        registeredBackend = backend;
    }

    public static MasterBackend getRegisteredBackend() {
        return Optional.ofNullable(registeredBackend).orElseGet(() -> new InMemMasterBackend());
    }

}
