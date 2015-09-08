package org.fenixedu.bennu.notifications.test.backend.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.fenixedu.bennu.notifications.master.Master;
import org.fenixedu.notifications.master.backend.NotificationInfo;
import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ReadNotificationTest extends AbstractAPITest {

    private static final String USERNAME = "user";
    private static final String TOKEN = "user";

    private JsonElement getPayload() {
        return new JsonObject();
    }

    @Test
    public void success() {
        NotificationInfo notification = Master.getInstance().createNotification(USERNAME, getPayload());
        boolean previousReadState = notification.isRead();
        invokeReadNotificationEndpoint(notification.getId(), TOKEN);
        notification = Master.getInstance().getNotification(USERNAME, notification.getId());
        assertFalse("Previous read state should be false", previousReadState);
        assertTrue("New read state should be true", notification.isRead());
    }

    @Test(expected = Exception.class)
    public void notAllowedToRead() {
        NotificationInfo notification = Master.getInstance().createNotification(USERNAME, getPayload());
        String wrongToken = "wrongToken";
        invokeReadNotificationEndpoint(notification.getId(), wrongToken);
    }
}
