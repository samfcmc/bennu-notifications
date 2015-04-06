package org.fenixedu.notificationscore.core;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.notificationscore.domain.Notification;

import com.google.gson.JsonElement;

public class NMaster {

    public static Notification createNotification(String username, JsonElement payload) {
        User user = User.findByUsername(username);
        return createNotification(user, payload);
    }

    public static Notification createNotification(User user, JsonElement payload) {
        return new Notification(user, payload);
    }

}
