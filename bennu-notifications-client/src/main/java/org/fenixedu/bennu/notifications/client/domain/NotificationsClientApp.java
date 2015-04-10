package org.fenixedu.bennu.notifications.client.domain;

import org.fenixedu.bennu.core.domain.Bennu;

public class NotificationsClientApp extends NotificationsClientApp_Base {

    public static NotificationsClientApp getInstance() {
        NotificationsClientApp instance = Bennu.getInstance().getClientApp();
        if (instance == null) {
            instance = new NotificationsClientApp();
        }
        return instance;
    }

    private NotificationsClientApp() {
        setRoot(Bennu.getInstance());
    }

}
