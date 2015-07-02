package org.fenixedu.bennu.notifications.test.master.api;

import static org.fenixedu.bennu.notifications.test.utils.TestUtils.generateUser;
import static org.fenixedu.bennu.notifications.test.utils.TestUtils.login;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;
import org.fenixedu.notifications.core.domain.Payload;
import org.junit.Test;

import com.google.gson.JsonObject;

public class ReadNotificationTest extends AbstractAPITest {

    private Payload getPayload() {
        return new Payload(new JsonObject());
    }

    @Test
    public void success() {
        User user = generateUser();
        login(user);
        DispatchedNotification notification = new DispatchedNotification(user, getPayload());
        boolean previousReadState = notification.getRead();
        invokeReadNotificationEndpoint(notification.getExternalId());

        assertFalse("Previous read state should be false", previousReadState);
        assertTrue("New read state should be true", notification.getRead());
    }

    @Test(expected = Exception.class)
    public void notificationAlreadyRead() {
        User user = generateUser();
        login(user);
        DispatchedNotification notification = new DispatchedNotification(user, getPayload());
        invokeReadNotificationEndpoint(notification.getExternalId());
        invokeReadNotificationEndpoint(notification.getExternalId());
    }

    @Test(expected = Exception.class)
    public void notAllowedToRead() {
        User user = generateUser();
        User loggedIn = generateUser();
        login(loggedIn);
        DispatchedNotification notification = new DispatchedNotification(user, getPayload());
        invokeReadNotificationEndpoint(notification.getExternalId());
    }
}
