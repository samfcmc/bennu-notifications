package org.fenixedu.bennu.notifications.master.domain;

import java.util.HashSet;
import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.exception.NotificationAlreadReadException;
import org.fenixedu.notifications.core.domain.Payload;
import org.joda.time.DateTime;

/**
 * DispatchedNotification: Notifications stored in the backend
 *
 */
public class DispatchedNotification extends DispatchedNotification_Base {

    /**
     * Instantiates a new dispatched notification.
     *
     * @param user the user
     * @param payload the payload
     */
    public DispatchedNotification(User user, Payload payload) {
        this(user, payload, DateTime.now());
    }

    /**
     * Instantiates a new dispatched notification.
     *
     * @param user the user
     * @param payload the payload
     * @param timestamp the timestamp
     */
    public DispatchedNotification(User user, Payload payload, DateTime timestamp) {
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
        super.init(payload, timestamp);
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
    public Set<DispatchedNotification> getNotificationsBefore() {
        Set<DispatchedNotification> notifications = new HashSet<DispatchedNotification>();
        DispatchedNotification notification = this;
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
    public Set<DispatchedNotification> getNotificationsAfter() {
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

    public Set<DispatchedNotification> getLastN(int n) {
        Set<DispatchedNotification> notifications = new HashSet<DispatchedNotification>();
        //DispatchedNotification notification = this;
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        for (DispatchedNotification notification = this; (notification != null) && (n != 0); notification =
                notification.getPrevious(), n--) {
            notifications.add(notification);
        }

        return notifications;
    }

    @Override
    public void setRead(boolean read) {
        if (read && getRead()) {
            //Notification has already been read
            throw new NotificationAlreadReadException(this);
        } else {
            super.setRead(read);
        }
    }

}
