package org.fenixedu.bennu.notifications.client;

import org.fenixedu.bennu.core.domain.User;

import com.google.gson.JsonElement;

public interface NotificationsClient {
    public void postNotification(User user, JsonElement payload);
}
