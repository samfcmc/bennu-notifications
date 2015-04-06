package org.fenixedu.notificationstest.core;

import java.util.Locale;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.core.domain.UserProfile;

public abstract class AbstractTest {

    private static long lastUserId = 0;
    private static final String USERNAME = "user";

    /**
     * Creates the user.
     *
     * @param username the username
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     */
    protected static User createUser(String username, String firstName, String lastName, String email) {
        return new User(username, new UserProfile(firstName, lastName, firstName + " " + lastName, email, Locale.getDefault()));
    }

    protected static User findUser(String username) {
        return User.findByUsername(username);
    }

    protected static User generateUser() {
        String username = USERNAME + lastUserId;
        User user = findUser(username);
        while (user != null) {
            lastUserId++;
            username = USERNAME + lastUserId;
            user = findUser(username);
        }
        return createUser(username, "User", "User", username + "@test.com");
    }

}
