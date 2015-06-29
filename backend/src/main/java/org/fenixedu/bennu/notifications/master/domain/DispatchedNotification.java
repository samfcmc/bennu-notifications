package org.fenixedu.bennu.notifications.master.domain;

import java.util.HashSet;
import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.joda.time.DateTime;

import com.google.gson.JsonElement;

public class DispatchedNotification extends DispatchedNotification_Base {

    public DispatchedNotification(User user, JsonElement payload) {
        this(user, payload, DateTime.now());
    }

    public DispatchedNotification(User user, JsonElement payload, DateTime timestamp) {
        super();
        init(user, payload, timestamp);
    }

    protected void init(User user, JsonElement payload, DateTime timestamp) {
        super.init(payload, timestamp);
        setUser(user);
        updateLastNotification();
    }

    private void updateLastNotification() {
        User user = getUser();
        DateTime timestamp = getTimestamp();
        DispatchedNotification lastNotification = user.getLastNotification();
        if (lastNotification == null || !timestamp.isBefore(lastNotification.getTimestamp())) {
            // There was no last notification or this is the most recent one
            setPrevious(lastNotification);
            user.setLastNotification(this);
        } else {
            DispatchedNotification currentNotification = lastNotification;
            while (currentNotification.hasPrevious()) {
                DispatchedNotification previousNotification = currentNotification.getPrevious();
                if (timestamp.isAfter(previousNotification.getTimestamp())) {
                    currentNotification.setPrevious(this);
                    setPrevious(previousNotification);
                    break;
                } else {
                    currentNotification = currentNotification.getPrevious();
                }
            }
            if (!currentNotification.hasPrevious()) {
                // Worst case: this notification is the oldest one
                currentNotification.setPrevious(this);
                setPrevious(null);
            }
        }
    }

    public boolean hasPrevious() {
        return getPrevious() != null;
    }

    public Set<DispatchedNotification> getBefore() {
        Set<DispatchedNotification> notifications = new HashSet<DispatchedNotification>();
        DispatchedNotification notification = this;
        while (notification.hasPrevious()) {
            notifications.add(notification.getPrevious());
            notification = notification.getPrevious();
        }

        return notifications;
    }

    public Set<DispatchedNotification> getLast() {
        Set<DispatchedNotification> notifications = new HashSet<DispatchedNotification>();
        DispatchedNotification notification = getUser().getLastNotification();
        if (notification.hasPrevious()) {
            while (notification != null) {
                if (notification.equals(this)) {
                    break;
                } else {
                    notifications.add(notification);
                    notification = notification.getPrevious();
                }
            }
        }

        return notifications;
    }

}
