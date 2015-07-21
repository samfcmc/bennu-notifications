package org.fenixedu.bennu.notifications.backend.view;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.notifications.master.backend.NotificationInfo;

public class NotificationView extends UserBasedView<NotificationInfo> {

    public NotificationView(User user, NotificationInfo object) {
        super(user, object);
    }

}
