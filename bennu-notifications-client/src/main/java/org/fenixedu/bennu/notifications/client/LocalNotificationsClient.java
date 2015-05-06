package org.fenixedu.bennu.notifications.client;

import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.NMaster;
import org.fenixedu.notificationscore.client.NotificationsClient;

import com.google.gson.JsonElement;

/**
 * The Class LocalNotificationsClient.
 * This client is used when developers choose to not have
 * a separate service for this.
 */
public class LocalNotificationsClient implements NotificationsClient {

    private static LocalNotificationsClient instance;

    public static LocalNotificationsClient getInstance() {
        if (instance == null) {
            instance = new LocalNotificationsClient();
        }
        return instance;
    }

    private LocalNotificationsClient() {
    }

    @Override
    public void postNotification(User user, JsonElement payload) {
        NMaster.createNotification(user, payload);
    }

    @Override
    public void postNotification(Set<User> users, JsonElement payload) {
        NMaster.createNotification(users, payload);

    }

}
