package org.fenixedu.bennu.notifications.client;

import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.client.payload.NotificationPayload;

public interface NotificationsClient {
    public void postNotification(User user, NotificationPayload payload);

    public void postNotification(Set<User> users, NotificationPayload payload);

}
