package org.fenixedu.bennu.notifications.client;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.client.exception.NoUserProvidedException;
import org.fenixedu.bennu.notifications.master.NMaster;

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
        if (user == null) {
            throw new NoUserProvidedException();
        }
        NMaster.createNotification(user, payload);
    }

}
