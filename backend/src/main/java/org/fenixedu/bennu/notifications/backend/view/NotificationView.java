package org.fenixedu.bennu.notifications.backend.view;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;

public class NotificationView extends UserBasedView<DispatchedNotification> {

    public NotificationView(User user, DispatchedNotification object) {
        super(user, object);
    }

}
