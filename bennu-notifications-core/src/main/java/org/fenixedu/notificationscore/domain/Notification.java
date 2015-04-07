package org.fenixedu.notificationscore.domain;

import org.fenixedu.bennu.core.domain.User;
import org.joda.time.DateTime;

import com.google.gson.JsonElement;

public class Notification extends Notification_Base {

    public Notification(User user, JsonElement payload) {
        this(user, payload, DateTime.now());
    }

    public Notification(User user, JsonElement payload, DateTime timestamp) {
        super();
        setUser(user);
        setPayload(payload);
        setTimestamp(timestamp);
    }

    @Override
    public void setTimestamp(DateTime timestamp) {
        super.setTimestamp(timestamp);
        updateLastNotification();
    }

    private void updateLastNotification() {
        User user = getUser();
        DateTime timestamp = getTimestamp();
        Notification lastNotification = user.getLastNotification();
        if (lastNotification == null || !timestamp.isBefore(lastNotification.getTimestamp())) {
            // There was no last notification or this is the most recent one
            setPrevious(lastNotification);
            user.setLastNotification(this);
        } else {
            Notification currentNotification = lastNotification;
            while (currentNotification.hasPrevious()) {
                Notification previousNotification = currentNotification.getPrevious();
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
}
