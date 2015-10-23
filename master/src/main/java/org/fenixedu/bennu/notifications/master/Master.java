package org.fenixedu.bennu.notifications.master;

import java.util.Collection;

import org.fenixedu.notifications.master.backend.MasterBackend;
import org.fenixedu.notifications.master.backend.MasterBackendFactory;
import org.fenixedu.notifications.master.backend.NotificationInfo;
import org.fenixedu.notifications.master.backend.webhook.Webhook;
import org.fenixedu.notifications.master.backend.webhook.WebhookMethod;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Master {

    private static Master instance;

    private Master() {
    }

    public static Master getInstance() {
        if (instance == null) {
            instance = new Master();
        }
        return instance;
    }

    public Collection<NotificationInfo> createNotifications(Collection<String> usernames, JsonElement payload) {
        return getBackend().createNotifications(usernames, payload);
    }

    private MasterBackend getBackend() {
        return MasterBackendFactory.getBackend();
    }

    public NotificationInfo createNotification(String username, JsonElement payload) {
        return getBackend().createNotification(username, payload);
    }

    public void markAsRead(String id, String username) {
        getBackend().markAsRead(id, username);
    }

    public NotificationInfo getNotification(String username, String id) {
        return getBackend().getNotification(id, username);
    }

    public Collection<NotificationInfo> getLastN(String username, int n) {
        return getBackend().getLastNNotificatons(username, n);
    }

    public Collection<NotificationInfo> getNotificationsAfter(String username, String id) {
        return getBackend().getNotificationsAfter(username, id);
    }

    public Collection<NotificationInfo> getNotificationsBefore(String username, String id) {
        return getBackend().getNotificationsBefore(username, id);
    }

    public Collection<NotificationInfo> getUnread(String username) {
        return getBackend().getUnread(username);
    }

    public Webhook createWebhook(String name, WebhookMethod method, String url, JsonObject data) {
        Webhook webhook = new Webhook(name, method, url, data);
        getBackend().addWebhook(webhook);

        return webhook;
    }

}
