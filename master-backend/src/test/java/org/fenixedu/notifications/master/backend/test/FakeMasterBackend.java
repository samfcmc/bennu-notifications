package org.fenixedu.notifications.master.backend.test;

import java.util.Collection;

import org.fenixedu.notifications.master.backend.MasterBackend;
import org.fenixedu.notifications.master.backend.NotificationInfo;
import org.fenixedu.notifications.master.backend.annotation.MasterBackendImplementation;
import org.fenixedu.notifications.master.backend.webhook.Webhook;

import com.google.gson.JsonElement;

@MasterBackendImplementation
public class FakeMasterBackend implements MasterBackend {

    public FakeMasterBackend() {
        // Just to not have an empty constructor
    }

    @Override
    public Collection<NotificationInfo> createNotifications(Collection<String> usernames, JsonElement payload) {
        return null;
    }

    @Override
    public NotificationInfo createNotification(String username, JsonElement payload) {
        return null;
    }

    @Override
    public void markAsRead(String id, String username) {
    }

    @Override
    public NotificationInfo getNotification(String id, String username) {
        return null;
    }

    @Override
    public Collection<NotificationInfo> getLastNNotificatons(String username, int n) {
        return null;
    }

    @Override
    public Collection<NotificationInfo> getNotificationsAfter(String username, String id) {
        return null;
    }

    @Override
    public Collection<NotificationInfo> getNotificationsBefore(String username, String id) {
        return null;
    }

    @Override
    public Collection<NotificationInfo> getUnread(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addWebhook(Webhook webhook) {
        // TODO Auto-generated method stub

    }

    @Override
    public Collection<Webhook> getWebhooks() {
        // TODO Auto-generated method stub
        return null;
    }

}
