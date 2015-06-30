package org.fenixedu.bennu.notifications.client.domain;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.notifications.core.domain.Payload;
import org.joda.time.DateTime;

public class PendingNotification extends PendingNotification_Base {

    public PendingNotification(User user, Payload payload, DateTime timestamp) {
        super();
        init(user, payload, timestamp);
    }

    public PendingNotification(User user, Payload payload) {
        this(user, payload, DateTime.now());
    }

    protected void init(User user, Payload payload, DateTime timestamp) {
        addUser(user);
        setNotificationsSystem(NotificationsSystem.getInstance());
        super.init(payload, timestamp);
    }

}
