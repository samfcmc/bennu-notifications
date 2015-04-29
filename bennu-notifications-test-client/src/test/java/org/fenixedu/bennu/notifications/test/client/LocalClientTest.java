package org.fenixedu.bennu.notifications.test.client;

import static org.fenixedu.bennu.notifications.test.utils.TestUtils.generateUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.client.LocalNotificationsClient;
import org.fenixedu.bennu.notifications.client.domain.PendingNotification;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;
import org.fenixedu.bennu.notifications.test.AbstractTest;
import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class LocalClientTest extends AbstractTest {

    private static final String KEY_1 = "key1";
    private static final String KEY_2 = "key2";
    private static final String VALUE_1 = "value1";
    private static final String VALUE_2 = "value2";

    private JsonElement getPayload() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(KEY_1, VALUE_1);
        jsonObject.addProperty(KEY_2, VALUE_2);
        return jsonObject;
    }

    @Test
    public void success() {
        User user = generateUser();
        JsonElement payload = getPayload();
        LocalNotificationsClient.getInstance().postNotification(user, payload);
        Set<PendingNotification> pendingNotifications = user.getPendingNotificationSet();
        DispatchedNotification dispatchedNotification = user.getLastNotification();

        assertEquals("User should not have any pending notifications", pendingNotifications.size(), 0);
        assertNotNull("Dispatched notification should not be null", dispatchedNotification);
        assertNull("Previous of dispatched notification should be null", dispatchedNotification.getPrevious());
        assertEquals("User of dispatched notification should be the same", user, dispatchedNotification.getUser());
        JsonObject payloadJson = dispatchedNotification.getPayload().getAsJsonObject();
        assertEquals("Key 1 of payload should be the same", payloadJson.get(KEY_1).getAsString(), VALUE_1);
        assertEquals("Key 2 of payload should be the same", payloadJson.get(KEY_2).getAsString(), VALUE_2);
    }

    @Test
    public void successPostMultipleNotifications() {
        User user = generateUser();
        JsonElement payload = getPayload();
        LocalNotificationsClient client = LocalNotificationsClient.getInstance();
        client.postNotification(user, payload);
        client.postNotification(user, payload);
        Set<PendingNotification> pendingNotifications = user.getPendingNotificationSet();
        DispatchedNotification dispatchedNotification = user.getLastNotification();

        assertEquals("User should not have any pending notifications", pendingNotifications.size(), 0);
        assertNotNull("Dispatched notification should not be null", dispatchedNotification);
        assertNotNull("Previous of dispatched notification should not be null", dispatchedNotification.getPrevious());
        assertEquals("User of dispatched notification should be the same", user, dispatchedNotification.getUser());
        assertEquals("User of previous dispatched notification should be the same", user, dispatchedNotification.getPrevious()
                .getUser());
        JsonObject payloadJson = dispatchedNotification.getPayload().getAsJsonObject();
        assertEquals("Key 1 of payload should be the same", payloadJson.get(KEY_1).getAsString(), VALUE_1);
        assertEquals("Key 2 of payload should be the same", payloadJson.get(KEY_2).getAsString(), VALUE_2);
        payloadJson = dispatchedNotification.getPrevious().getPayload().getAsJsonObject();
        assertEquals("Key 1 of payload should be the same", payloadJson.get(KEY_1).getAsString(), VALUE_1);
        assertEquals("Key 2 of payload should be the same", payloadJson.get(KEY_2).getAsString(), VALUE_2);
    }

    @Test
    public void successMultipleUsers() {
        User user1 = generateUser();
        User user2 = generateUser();
        JsonElement payload = getPayload();
        LocalNotificationsClient client = LocalNotificationsClient.getInstance();
        client.postNotification(user1, payload);
        client.postNotification(user2, payload);

        Set<PendingNotification> pendingNotificationsUser1 = user1.getPendingNotificationSet();
        Set<PendingNotification> pendingNotificationsUser2 = user2.getPendingNotificationSet();
        DispatchedNotification dispatchedNotificationUser1 = user1.getLastNotification();
        DispatchedNotification dispatchedNotificationUser2 = user2.getLastNotification();

        assertEquals("User 1 should not have any pending notifications", pendingNotificationsUser1.size(), 0);
        assertEquals("User 2 should not have any pending notifications", pendingNotificationsUser2.size(), 0);
        assertNotNull("Dispatched notification of user 1 should not be null", dispatchedNotificationUser1);
        assertNotNull("Dispatched notification of user 2 should not be null", dispatchedNotificationUser2);
        assertNull("Previous of dispatched notification of user 1 should be null", dispatchedNotificationUser1.getPrevious());
        assertNull("Previous of dispatched notification of user 2 should be null", dispatchedNotificationUser2.getPrevious());

        assertEquals("User of dispatched notification 1 should be the same as user 1", user1,
                dispatchedNotificationUser1.getUser());
        assertEquals("User of dispatched notification 1 should be the same as user 2", user2,
                dispatchedNotificationUser2.getUser());
        JsonObject payloadJson = dispatchedNotificationUser1.getPayload().getAsJsonObject();
        assertEquals("Key 1 of payload should be the same", payloadJson.get(KEY_1).getAsString(), VALUE_1);
        assertEquals("Key 2 of payload should be the same", payloadJson.get(KEY_2).getAsString(), VALUE_2);
        payloadJson = dispatchedNotificationUser2.getPayload().getAsJsonObject();
        assertEquals("Key 1 of payload should be the same", payloadJson.get(KEY_1).getAsString(), VALUE_1);
        assertEquals("Key 2 of payload should be the same", payloadJson.get(KEY_2).getAsString(), VALUE_2);
    }
}
