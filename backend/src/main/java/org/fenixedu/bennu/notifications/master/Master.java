package org.fenixedu.bennu.notifications.master;

import java.util.Collection;
import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;
import org.fenixedu.bennu.notifications.master.exception.UserDoesNotExistException;
import org.fenixedu.notifications.core.domain.Payload;

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

}
