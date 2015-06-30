package org.fenixedu.bennu.notifications.test.client.domain;

import static org.fenixedu.bennu.notifications.test.utils.TestUtils.generateUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.client.domain.NotificationsSystem;
import org.fenixedu.bennu.notifications.client.domain.PendingNotification;
import org.fenixedu.bennu.notifications.test.AbstractTest;
import org.fenixedu.notifications.core.domain.Payload;
import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PendingNotificationTest extends AbstractTest {

    private static final String KEY_1 = "key1";
    private static final String KEY_2 = "key2";
    private static final String VALUE_1 = "value1";
    private static final String VALUE_2 = "value2";

    @Test
    public void success() {
        User user = generateUser();
        JsonElement payloadJson = getPayload();
        Payload payload = new Payload(payloadJson);
        PendingNotification notification = new PendingNotification(user, payload);
        NotificationsSystem system = notification.getNotificationsSystem();

        assertNotNull("New pending notification should not be null", notification);
        assertNotNull("Notifications system should not be null", system);
        assertTrue("User should have one pending notification", user.getPendingNotificationSet().contains(notification));
        assertTrue("Notifications system should have the new notification",
                system.getPendingNotificationSet().contains(notification));
        assertEquals("Notification payload should be the same", notification.getPayload(), payload);
    }

    private JsonElement getPayload() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(KEY_1, VALUE_1);
        jsonObject.addProperty(KEY_2, VALUE_2);
        return jsonObject;
    }

}
