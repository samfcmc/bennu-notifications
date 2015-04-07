package org.fenixedu.notificationstest.core;

import static org.junit.Assert.assertEquals;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.NMaster;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;
import org.fenixedu.bennu.notifications.test.AbstractTest;
import org.joda.time.DateTime;
import org.junit.Test;

import com.google.gson.JsonObject;

/**
 * The Class CreateNotificationTest.
 */
public class CreateNotificationTest extends AbstractTest {

    private static final int MINUTES = 20;

    // Fake payload
    private static final String KEY_1 = "key1";
    private static final String VALUE_1 = "value1";
    private static final String KEY_2 = "key2";
    private static final String VALUE_2 = "value2";

    /**
     * Creates a payload of a fake notification.
     *
     * @return the json object
     */
    public JsonObject createPayload() {
        JsonObject payload = new JsonObject();
        payload.addProperty(KEY_1, VALUE_1);
        payload.addProperty(KEY_2, VALUE_2);
        return payload;
    }

    /**
     * Creates just one notification.
     */
    @Test
    public void createFirst() {
        JsonObject payload = createPayload();
        User user = generateUser();
        DispatchedNotification notification = NMaster.createNotification(user, payload);

        assertEquals("Notification's user should be the same", user, notification.getUser());
        assertEquals("Payload's key1 should have value value1", VALUE_1, payload.get(KEY_1).getAsString());
        assertEquals("Payload's key2 should have value value2", VALUE_2, payload.get(KEY_2).getAsString());
        assertEquals("Created notification should be the last one", notification, user.getLastNotification());
    }

    /**
     * Creates some notifications with timestamps T0, T1, T2 and check if they are ordered.
     */
    @Test
    public void testOrder() {
        JsonObject payload = createPayload();
        User user = generateUser();
        DispatchedNotification notificationT0 = new DispatchedNotification(user, payload);
        DispatchedNotification notificationT1 = new DispatchedNotification(user, payload);
        DispatchedNotification notificationT2 = new DispatchedNotification(user, payload);

        assertEquals("The last notification should be T2", notificationT2, user.getLastNotification());
        assertEquals("The previous of T2 should be T1", notificationT1, user.getLastNotification().getPrevious());
        assertEquals("The previous of T1 should be T0", notificationT0, user.getLastNotification().getPrevious().getPrevious());
    }

    @Test
    public void insertOneInTheMiddle() {
        JsonObject payload = createPayload();
        User user = generateUser();

        DispatchedNotification notificationT0 = new DispatchedNotification(user, payload);
        DispatchedNotification notificationT1 = new DispatchedNotification(user, payload);
        DispatchedNotification notificationT3 = new DispatchedNotification(user, payload, DateTime.now().plusMinutes(MINUTES));
        DispatchedNotification notificationT2 =
                new DispatchedNotification(user, payload, notificationT3.getTimestamp().minusMinutes(MINUTES / 2));
        DispatchedNotification lastNotification = user.getLastNotification();

        assertEquals("Last notification should be T3", notificationT3, lastNotification);
        assertEquals("Previous of T3 should be T2", notificationT2, lastNotification.getPrevious());
        assertEquals("Previous of T2 should be T1", notificationT1, lastNotification.getPrevious().getPrevious());
        assertEquals("Previous of T1 should be T0", notificationT0, lastNotification.getPrevious().getPrevious().getPrevious());
    }

    @Test
    public void insertInBegin() {
        JsonObject payload = createPayload();
        User user = generateUser();

        DispatchedNotification notificationT1 = new DispatchedNotification(user, payload);
        DispatchedNotification notificationT2 = new DispatchedNotification(user, payload);
        DispatchedNotification notificationT3 = new DispatchedNotification(user, payload);
        DispatchedNotification notificationT0 =
                new DispatchedNotification(user, payload, notificationT1.getTimestamp().minusMinutes(MINUTES));
        DispatchedNotification lastNotification = user.getLastNotification();

        assertEquals("Last notification should be T3", notificationT3, lastNotification);
        assertEquals("Previous of T3 should be T2", notificationT2, lastNotification.getPrevious());
        assertEquals("Previous of T2 should be T1", notificationT1, lastNotification.getPrevious().getPrevious());
        assertEquals("Previous of T1 should be T0", notificationT0, lastNotification.getPrevious().getPrevious().getPrevious());
    }
}
