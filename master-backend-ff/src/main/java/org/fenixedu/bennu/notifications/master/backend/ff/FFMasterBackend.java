package org.fenixedu.bennu.notifications.master.backend.ff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.backend.ff.domain.Notification;
import org.fenixedu.bennu.notifications.master.backend.ff.domain.Payload;
import org.fenixedu.notifications.master.backend.MasterBackend;
import org.fenixedu.notifications.master.backend.NotificationInfo;
import org.fenixedu.notifications.master.backend.annotation.MasterBackendImplementation;
import org.fenixedu.notifications.master.backend.exception.NotificationDoesNotBelongToUserException;
import org.fenixedu.notifications.master.backend.webhook.Webhook;

import pt.ist.fenixframework.FenixFramework;

import com.google.gson.JsonElement;

@MasterBackendImplementation
public class FFMasterBackend implements MasterBackend {

    @Override
    public Collection<NotificationInfo> createNotifications(Collection<String> usernames, JsonElement payload) {
        List<NotificationInfo> list = new ArrayList<NotificationInfo>(usernames.size());
        for (String username : usernames) {
            list.add(createNotification(username, payload));
        }
        return list;
    }

    @Override
    public NotificationInfo createNotification(String username, JsonElement payload) {
        User user = User.findByUsername(username);
        Notification notification = new Notification(user, new Payload(payload));
        NotificationInfo notificationInfo =
                new NotificationInfo(notification.getExternalId(), username, payload, notification.getTimestamp());
        return notificationInfo;
    }

    private Notification getNotificationFromDomain(String id, User user) {
        Notification notification = FenixFramework.getDomainObject(id);
        if (notification.getUser().equals(user)) {
            // User owns this notification
            return notification;
        } else {
            throw new NotificationDoesNotBelongToUserException(user.getUsername(), id);
        }
    }

    private Notification getNotificationFromDomain(String id, String username) {
        User user = User.findByUsername(username);
        return getNotificationFromDomain(id, user);
    }

    @Override
    public void markAsRead(String id, String username) {
        Notification notification = getNotificationFromDomain(id, username);
        notification.read();
    }

    @Override
    public NotificationInfo getNotification(String id, String username) {
        Notification notification = getNotificationFromDomain(id, username);
        return getNotificationInfo(notification);

    }

    private NotificationInfo getNotificationInfo(Notification notification) {
        return new NotificationInfo(notification.getExternalId(), notification.getUser().getUsername(), notification.getPayload()
                .getContent(), notification.getRead(), notification.getTimestamp());
    }

    private Collection<NotificationInfo> getNotificationInfoCollection(Collection<Notification> notifications) {
        List<NotificationInfo> list = new ArrayList<>(notifications.size());
        for (Notification notification : notifications) {
            list.add(getNotificationInfo(notification));
        }
        return list;
    }

    @Override
    public Collection<NotificationInfo> getLastNNotificatons(String username, int n) {
        User user = User.findByUsername(username);
        Notification notification = user.getLastNotification();
        if (notification == null) {
            return new HashSet<>();
        }
        Set<Notification> notifications = user.getLastNotification().getLastN(n);
        return getNotificationInfoCollection(notifications);
    }

    @Override
    public Collection<NotificationInfo> getNotificationsAfter(String username, String id) {
        Notification notification = getNotificationFromDomain(id, username);
        Set<Notification> notifications = notification.getNotificationsAfter();
        return getNotificationInfoCollection(notifications);
    }

    @Override
    public Collection<NotificationInfo> getNotificationsBefore(String username, String id) {
        Notification notification = getNotificationFromDomain(id, username);
        Set<Notification> notifications = notification.getNotificationsBefore();
        return getNotificationInfoCollection(notifications);
    }

    @Override
    public Collection<NotificationInfo> getUnread(String username) {
        User user = User.findByUsername(username);
        Notification notification = user.getLastNotification();
        Collection<Notification> unread = notification.getPreviousUnread();
        Collection<NotificationInfo> result = getNotificationInfoCollection(unread);
        return result;
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
