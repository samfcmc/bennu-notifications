package org.fenixedu.notifications.master.backend;

import java.util.Collection;

import org.fenixedu.notifications.master.backend.webhook.Webhook;

import com.google.gson.JsonElement;

public interface MasterBackend {

    Collection<NotificationInfo> createNotifications(Collection<String> usernames, JsonElement payload);

    NotificationInfo createNotification(String username, JsonElement payload);

    void markAsRead(String id, String username);

    NotificationInfo getNotification(String id, String username);

    Collection<NotificationInfo> getLastNNotificatons(String username, int n);

    Collection<NotificationInfo> getNotificationsAfter(String username, String id);

    Collection<NotificationInfo> getNotificationsBefore(String username, String id);

    Collection<NotificationInfo> getUnread(String username);

    void addWebhook(Webhook webhook);

    Collection<Webhook> getWebhooks();

}
