package org.fenixedu.bennu.notifications.client.domain;

import org.fenixedu.bennu.core.domain.Bennu;

public class NotificationsSystem extends NotificationsSystem_Base {

    public static NotificationsSystem getInstance() {
        NotificationsSystem system = Bennu.getInstance().getNotificationsSystem();
        if (system == null) {
            system = new NotificationsSystem();
        }
        return system;
    }

    private NotificationsSystem() {
        super();
        setBennu(Bennu.getInstance());
    }

}
