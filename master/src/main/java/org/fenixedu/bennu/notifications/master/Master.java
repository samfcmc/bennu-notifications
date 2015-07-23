package org.fenixedu.bennu.notifications.master;

import java.util.Collection;

import org.fenixedu.notifications.master.backend.MasterBackendFactory;
import org.fenixedu.notifications.master.backend.MasterBackend;
import org.fenixedu.notifications.master.backend.NotificationInfo;

import com.google.gson.JsonElement;

public class Master {

    private static Master instance;

    private MasterBackend backend;

    private Master() {
        this.backend = getBackend();
    }

    public static Master getInstance() {
        if (instance == null) {
            instance = new Master();
        }
        return instance;
    }

    public Collection<NotificationInfo> createNotifications(Collection<String> usernames, JsonElement payload) {
        return backend.createNotifications(usernames, payload);
    }

    private MasterBackend getBackend() {
        return MasterBackendFactory.getBackend();
    }

    public NotificationInfo createNotification(String username, JsonElement payload) {
        return backend.createNotification(username, payload);
    }

    public void read(String id, String username) {
        backend.read(id, username);
    }

    public NotificationInfo getNotification(String username, String id) {
        return backend.getNotification(id, username);
    }

    public Collection<NotificationInfo> getLastN(String username, int n) {
        return backend.getLastNNotificatons(username, n);
    }

    public Collection<NotificationInfo> getNotificationsAfter(String username, String id) {
        return backend.getNotificationsAfter(username, id);
    }

    public Collection<NotificationInfo> getNotificationsBefore(String username, String id) {
        return backend.getNotificationsBefore(username, id);
    }

}
