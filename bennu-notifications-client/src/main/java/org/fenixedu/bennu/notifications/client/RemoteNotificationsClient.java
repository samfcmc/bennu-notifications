package org.fenixedu.bennu.notifications.client;

import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.notificationscore.client.NotificationsClient;

import com.google.gson.JsonElement;

public class RemoteNotificationsClient implements NotificationsClient {

    private static RemoteNotificationsClient instance;

    private RemoteNotificationsClient() {
    }

    public static RemoteNotificationsClient getInstance() {
        if (instance == null) {
            instance = new RemoteNotificationsClient();
        }
        return instance;
    }

    @Override
    public void postNotification(User user, JsonElement payload) {
        // TODO Auto-generated method stub

    }

    @Override
    public void postNotification(Set<User> users, JsonElement payload) {
        // TODO Auto-generated method stub

    }

}
