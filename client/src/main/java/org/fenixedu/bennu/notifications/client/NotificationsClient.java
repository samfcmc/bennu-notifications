package org.fenixedu.bennu.notifications.client;

import java.util.Set;

import org.fenixedu.bennu.core.domain.User;

import com.google.gson.JsonElement;

public interface NotificationsClient {
    public void postNotification(User user, JsonElement payload);

    public void postNotification(Set<User> users, JsonElement payload);

}
