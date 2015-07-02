package org.fenixedu.bennu.notifications.master;

import java.util.Collection;
import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;
import org.fenixedu.bennu.notifications.master.exception.NotificationDoesNotBelongToUserException;
import org.fenixedu.bennu.notifications.master.exception.NotificationNotFoundException;
import org.fenixedu.bennu.notifications.master.exception.UserDoesNotExistException;
import org.fenixedu.bennu.notifications.master.exception.UserNotAllowedToReadException;
import org.fenixedu.notifications.core.domain.Payload;

import pt.ist.fenixframework.FenixFramework;

import com.google.gson.JsonElement;

public class Master {

    public static void createNotification(Collection<String> usernames, JsonElement payload) {
        for (String username : usernames) {
            User user = User.findByUsername(username);
            createNotification(user, payload);
        }
    }

    public static void createNotification(User user, JsonElement payload) {
        if (user == null) {
            throw new UserDoesNotExistException();
        }
        new DispatchedNotification(user, new Payload(payload));
    }

    public static void createNotification(Set<User> users, JsonElement payload) {
        users.stream().forEach(user -> createNotification(user, payload));
    }

    public static void read(DispatchedNotification notification, User user) {
        if (user.equals(notification.getUser())) {
            notification.setRead(true);
        } else {
            throw new UserNotAllowedToReadException(notification, user);
        }
    }

    public static DispatchedNotification getNotification(User user, String id) {
        DispatchedNotification notification = FenixFramework.getDomainObject(id);
        if (notification == null) {
            throw new NotificationNotFoundException(id);
        } else if (notification.getUser().equals(user)) {
            return notification;
        } else {
            throw new NotificationDoesNotBelongToUserException(user.getUsername(), id);
        }
    }

    public static Set<DispatchedNotification> getLastN(User user, int n) {
        return user.getLastNotification().getLastN(n);
    }

}
