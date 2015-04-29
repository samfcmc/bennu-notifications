package org.fenixedu.bennu.notifications.master;

import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;
import org.fenixedu.bennu.notifications.master.exception.UserDoesNotExistException;

import com.google.gson.JsonElement;

public class NMaster {

    public static DispatchedNotification createNotification(String username, JsonElement payload) {
        User user = User.findByUsername(username);
        return createNotification(user, payload);
    }

    public static DispatchedNotification createNotification(User user, JsonElement payload) {
        if (user == null) {
            throw new UserDoesNotExistException();
        }
        return new DispatchedNotification(user, payload);
    }

    public static void createNotification(Set<User> users, JsonElement payload) {
        users.stream().forEach(user -> createNotification(user, payload));
    }

}
