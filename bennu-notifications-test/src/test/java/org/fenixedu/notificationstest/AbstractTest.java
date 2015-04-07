package org.fenixedu.notificationstest;

import java.util.Locale;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.core.domain.UserProfile;
import org.fenixedu.notificationstest.ff.FenixFrameworkRunner;
import org.junit.runner.RunWith;

@RunWith(FenixFrameworkRunner.class)
public abstract class AbstractTest {

    private static long lastUserId = 0;
    private static final String USERNAME = "user";
    private static final String INEXISTANT_USERNAME = "ghostUser";
    private static long lastInexistantUsernameId = 0;

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

    private static String getAvailableUsername(String baseUsername) {
        String username = baseUsername + lastUserId;
        User user = findUser(username);
        while (user != null) {
            lastUserId++;
            username = baseUsername + lastUserId;
            user = findUser(username);
        }
        return username;
    }

    private static String getAvailableUsername() {
        return getAvailableUsername(USERNAME);
    }

    protected static User generateUser() {
        String username = getAvailableUsername();
        return createUser(username, "User", "User", username + "@test.com");
    }

    protected static String getInexistantUsername() {
        return getAvailableUsername(INEXISTANT_USERNAME);
    }
}
