package org.fenixedu.bennu.notifications.backend.view;

import org.fenixedu.notifications.master.backend.NotificationInfo;

public class NotificationView extends UserBasedView<NotificationInfo> {

    public NotificationView(String username, NotificationInfo object) {
        super(username, object);
    }

}
