package org.fenixedu.bennu.notifications.master;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.notificationscore.domain.Notification;
import org.fenixedu.notificationscore.exception.UserDoesNotExistException;

import com.google.gson.JsonElement;

public class NMaster {

    public static Notification createNotification(String username, JsonElement payload) {
        User user = User.findByUsername(username);
        return createNotification(user, payload);
    }

    public static Notification createNotification(User user, JsonElement payload) {
        if (user == null) {
            throw new UserDoesNotExistException();
        }
        return new Notification(user, payload);
    }

}
