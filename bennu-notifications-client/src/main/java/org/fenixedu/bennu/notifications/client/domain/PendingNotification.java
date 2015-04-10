package org.fenixedu.bennu.notifications.client.domain;

import org.fenixedu.bennu.core.domain.User;
import org.joda.time.DateTime;

import com.google.gson.JsonElement;

public class PendingNotification extends PendingNotification_Base {

    public PendingNotification(User user, JsonElement payload, DateTime timestamp) {
        super();
        init(user, payload, timestamp);
    }

    public PendingNotification(User user, JsonElement payload) {
        this(user, payload, DateTime.now());
    }

    protected void init(User user, JsonElement payload, DateTime timestamp) {
        addUser(user);
        setNotificationsSystem(NotificationsSystem.getInstance());
        super.init(payload, timestamp);
    }

}
