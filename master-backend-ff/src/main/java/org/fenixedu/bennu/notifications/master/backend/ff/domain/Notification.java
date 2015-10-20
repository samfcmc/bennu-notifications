package org.fenixedu.bennu.notifications.master.backend.ff.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.joda.time.DateTime;

public class Notification extends Notification_Base {

    public Notification() {
        super();
    }

    public Notification(User user, Payload payload) {
        this(user, payload, DateTime.now());
    }

    /**
     * Instantiates a new dispatched notification.
     *
     * @param user the user
     * @param payload the payload
     * @param timestamp the timestamp
     */
    public Notification(User user, Payload payload, DateTime timestamp) {
        super();
        init(user, payload, timestamp);
    }

    /**
     * Inits the.
     *
     * @param user the user
     * @param payload the payload
     * @param timestamp the timestamp
     */
    protected void init(User user, Payload payload, DateTime timestamp) {
        setUser(user);
        setRead(false);
        updateLastNotification();
    }

    /**
     * updateLastNotification: Update its order in the user's notifications according to its timestamp
     */
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

    /**
     * Checks for previous.
     *
     * @return true, if successful
     */
    public boolean hasPrevious() {
        return getPrevious() != null;
    }

    /**
     * getBefore: Get all notifications before this one
     *
     * @return the before
     */
    public Set<Notification> getNotificationsBefore() {
        Set<Notification> notifications = new HashSet<Notification>();
        Notification notification = this;
        while (notification.hasPrevious()) {
            notifications.add(notification.getPrevious());
            notification = notification.getPrevious();
        }

        return notifications;
    }

    /**
     * getNotificationsAfter: Get all notifications from this one to the user's last notification
     *
     * @return the last
     */
    public Set<Notification> getNotificationsAfter() {
        Set<Notification> notifications = new HashSet<Notification>();
        Notification notification = getUser().getLastNotification();
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

    public Set<Notification> getLastN(int n) {
        Set<Notification> notifications = new HashSet<Notification>();
        //DispatchedNotification notification = this;
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        for (Notification notification = this; (notification != null) && (n != 0); notification = notification.getPrevious(), n--) {
            notifications.add(notification);
        }

        return notifications;
    }

    public void read() {
        setRead(true);
    }

    public Collection<Notification> getPreviousUnread() {
        Notification notification = this;
        List<Notification> unread = new LinkedList<>();
        while (notification.hasPrevious()) {
            if (notification.getRead()) {
                break;
            } else {
                unread.add(notification);
                notification = notification.getPrevious();
            }
        }
        return unread;
    }

}
