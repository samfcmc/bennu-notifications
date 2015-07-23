package org.fenixedu.notifications.master.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.fenixedu.notifications.master.backend.exception.NotificationAlreadyReadException;
import org.fenixedu.notifications.master.backend.exception.NotificationDoesNotBelongToUserException;
import org.fenixedu.notifications.master.backend.exception.NotificationNotFoundException;
import org.joda.time.DateTime;

import com.google.gson.JsonElement;

public class InMemMasterBackend implements MasterBackend {
    private Map<String, NotificationInfo> notifications;
    private int id;

    public InMemMasterBackend() {
        this.notifications = new HashMap<>();
        this.id = 0;
    }

    @Override
    public Collection<NotificationInfo> createNotifications(Collection<String> usernames, JsonElement payload) {
        List<NotificationInfo> list = new ArrayList<>(usernames.size());
        for (String username : usernames) {
            NotificationInfo notificationInfo = createNotification(username, payload);
            list.add(notificationInfo);
        }
        return list;
    }

    @Override
    public NotificationInfo createNotification(String username, JsonElement payload) {
        String id = Integer.toString(this.id);
        NotificationInfo notificationInfo = new NotificationInfo(id, username, payload, new DateTime());
        this.notifications.put(id, notificationInfo);
        this.id++;
        return notificationInfo;
    }

    @Override
    public void markAsRead(String id, String username) {
        NotificationInfo notification = getNotification(id, username);
        if (notification.isRead()) {
            throw new NotificationAlreadyReadException(notification);
        }
        notification.setRead(true);
    }

    @Override
    public NotificationInfo getNotification(String id, String username) {
        NotificationInfo notificationInfo = this.notifications.get(id);
        if (notificationInfo == null) {
            throw new NotificationNotFoundException(id);
        }
        if (notificationInfo.getUsername().equals(username)) {
            return notificationInfo;
        } else {
            throw new NotificationDoesNotBelongToUserException(username, id);
        }
    }

    private Stream<NotificationInfo> getStreamForUser(String username) {
        return this.notifications.values().stream().filter(notification -> notification.getUsername().equals(username));
    }

    @Override
    public Collection<NotificationInfo> getLastNNotificatons(String username, int n) {
        return getStreamForUser(username).sorted(new Comparator<NotificationInfo>() {

            @Override
            public int compare(NotificationInfo o1, NotificationInfo o2) {
                if (o1.getTimestamp().isAfter(o2.getTimestamp())) {
                    return 1;
                } else if (o1.getTimestamp().isEqual(o2.getTimestamp())) {
                    return 0;
                } else {
                    return -1;
                }
            }
        }).limit(n).collect(Collectors.toSet());
    }

    private int getId(NotificationInfo notificationInfo) {
        int value = Integer.parseInt(notificationInfo.getId());
        return value;
    }

    @Override
    public Collection<NotificationInfo> getNotificationsAfter(String username, String id) {
        NotificationInfo notification = getNotification(id, username);
        return getStreamForUser(username)
                .filter(notificationToFilter -> notificationToFilter.getTimestamp().isAfter(notification.getTimestamp())
                        || (notificationToFilter.getTimestamp().isEqual(notification.getTimestamp()) && getId(notificationToFilter) > getId(notification)))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<NotificationInfo> getNotificationsBefore(String username, String id) {
        NotificationInfo notification = getNotification(id, username);
        return getStreamForUser(username)
                .filter(notificationToFilter -> notificationToFilter.getTimestamp().isBefore(notification.getTimestamp())
                        || (notificationToFilter.getTimestamp().isEqual(notification.getTimestamp()) && getId(notificationToFilter) < getId(notification)))
                .collect(Collectors.toSet());
    }

}
