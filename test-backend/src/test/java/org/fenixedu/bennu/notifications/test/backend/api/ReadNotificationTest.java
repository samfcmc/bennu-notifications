package org.fenixedu.bennu.notifications.test.backend.api;

import static org.fenixedu.bennu.notifications.test.utils.TestUtils.generateUser;
import static org.fenixedu.bennu.notifications.test.utils.TestUtils.login;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.Master;
import org.fenixedu.notifications.master.backend.NotificationInfo;
import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ReadNotificationTest extends AbstractAPITest {

    private JsonElement getPayload() {
        return new JsonObject();
    }

    @Test
    public void success() {
        User user = generateUser();
        String username = user.getUsername();
        login(user);
        NotificationInfo notification = Master.getInstance().createNotification(username, getPayload());
        boolean previousReadState = notification.isRead();
        invokeReadNotificationEndpoint(notification.getId());
        notification = Master.getInstance().getNotification(username, notification.getId());
        assertFalse("Previous read state should be false", previousReadState);
        assertTrue("New read state should be true", notification.isRead());
    }

    @Test(expected = Exception.class)
    public void notificationAlreadyRead() {
        User user = generateUser();
        String username = user.getUsername();
        login(user);
        NotificationInfo notification = Master.getInstance().createNotification(username, getPayload());
        invokeReadNotificationEndpoint(notification.getId());
        invokeReadNotificationEndpoint(notification.getId());
    }

    @Test(expected = Exception.class)
    public void notAllowedToRead() {
        User user = generateUser();
        User loggedIn = generateUser();
        String username = user.getUsername();
        login(loggedIn);
        NotificationInfo notification = Master.getInstance().createNotification(username, getPayload());
        invokeReadNotificationEndpoint(notification.getId());
    }
}
