package org.fenixedu.bennu.notifications.master.client;

import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.NMaster;
import org.fenixedu.notificationscore.client.ClientCallback;
import org.fenixedu.notificationscore.client.NotificationsClient;

import com.google.gson.JsonElement;

/**
 * The Class LocalNotificationsClient.
 * This client is used when developers choose to not have
 * a separate service for this.
 */
public class LocalNotificationsClient implements NotificationsClient {

    public LocalNotificationsClient() {
    }

    @Override
    public void postNotification(User user, JsonElement payload) {
        NMaster.createNotification(user, payload);
    }

    @Override
    public void postNotification(Set<User> users, JsonElement payload) {
        NMaster.createNotification(users, payload);

    }

    @Override
    public void postNotificationAsync(User user, JsonElement payload, ClientCallback callback) {
        NMaster.createNotification(user, payload);
        callback.success();
    }

    @Override
    public void postNotificationAsync(Set<User> users, JsonElement payload, ClientCallback callback) {
        NMaster.createNotification(users, payload);
        callback.success();
    }

}