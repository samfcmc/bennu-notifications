package org.fenixedu.bennu.notifications.test.master.api;

import static org.fenixedu.bennu.notifications.test.utils.TestUtils.generateUser;
import static org.fenixedu.bennu.notifications.test.utils.TestUtils.login;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;
import org.fenixedu.notifications.core.domain.Payload;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GetNotificationsTest extends AbstractAPITest {

    private static final String ID = "id";

    @Test
    public void successNotificationsAfter() {
        User user = generateUser();
        JsonElement payload = getNotificationPayload();
        DispatchedNotification last = new DispatchedNotification(user, new Payload(payload));
        new DispatchedNotification(user, new Payload(payload));
        new DispatchedNotification(user, new Payload(payload));
        login(user);
        JsonArray responseJson = invokeGetNotificationsAfterEndpoint(last.getExternalId());

        assertEquals("Result should have 2 elements", 2, responseJson.size());

        // Last should not be in result
        for (JsonElement jsonElement : responseJson) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String id = jsonObject.get(ID).getAsString();
            assertNotification(jsonObject);
            assertNotEquals("last notification should not be included in the result", id, last.getExternalId());
        }
    }

    @Test(expected = Exception.class)
    public void afterIdDoesNotExist() {
        User user = generateUser();
        JsonElement payloadJson = getNotificationPayload();
        Payload payload = new Payload(payloadJson);
        new DispatchedNotification(user, payload);
        new DispatchedNotification(user, payload);
        new DispatchedNotification(user, payload);
        login(user);
        invokeGetNotificationsAfterEndpoint("fake");
    }

    @Test(expected = Exception.class)
    public void afterIdDoesNotBelongToUser() {
        User loggedIn = generateUser();
        User user = generateUser();
        JsonElement payloadJson = getNotificationPayload();
        DispatchedNotification notification = new DispatchedNotification(user, new Payload(payloadJson));
        new DispatchedNotification(user, new Payload(payloadJson));

        login(loggedIn);
        invokeGetNotificationsAfterEndpoint(notification.getExternalId());
    }

    @Test
    public void successNotificationsBefore() {
        User user = generateUser();
        JsonElement payload = getNotificationPayload();
        new DispatchedNotification(user, new Payload(payload));
        new DispatchedNotification(user, new Payload(payload));
        DispatchedNotification notification3 = new DispatchedNotification(user, new Payload(payload));
        login(user);
        JsonArray responseJson = invokeGetNotificationsBeforeEndpoint(notification3.getExternalId());

        assertEquals("Result should have 2 elements", 2, responseJson.size());

        // Notification3 should not be in result
        for (JsonElement jsonElement : responseJson) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String id = jsonObject.get(ID).getAsString();
            assertNotification(jsonObject);
            assertNotEquals("notification3 should not be included in the result", id, notification3.getExternalId());
        }
    }

    @Test(expected = Exception.class)
    public void beforeIdDoesNotBelongToUser() {
        User loggedIn = generateUser();
        User user = generateUser();
        JsonElement payloadJson = getNotificationPayload();
        new DispatchedNotification(user, new Payload(payloadJson));
        DispatchedNotification notification = new DispatchedNotification(user, new Payload(payloadJson));

        login(loggedIn);
        invokeGetNotificationsBeforeEndpoint(notification.getExternalId());
    }

    @Test
    public void noNotificationsAfterLast() {
        User user = generateUser();
        JsonElement payload = getNotificationPayload();
        new DispatchedNotification(user, new Payload(payload));
        new DispatchedNotification(user, new Payload(payload));
        DispatchedNotification last = new DispatchedNotification(user, new Payload(payload));
        login(user);
        JsonArray responseJson = invokeGetNotificationsAfterEndpoint(last.getExternalId());

        assertEquals("Result should be empty", 0, responseJson.size());
    }

    @Test
    public void getNLastGreaterThan0() {
        User user = generateUser();
        JsonElement payload = getNotificationPayload();
        int n = 10;
        for (int i = 0; i < n; i++) {
            new DispatchedNotification(user, new Payload(payload));
        }
        login(user);
        JsonArray responseJson = invokeGetNLastNotifications(n);

        assertEquals("Result should have " + n + " elements", n, responseJson.size());

        for (JsonElement jsonElement : responseJson) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            assertNotification(jsonObject);
        }
    }

    @Test
    public void getNLastEqual0() {
        User user = generateUser();
        JsonElement payload = getNotificationPayload();
        int n = 10;
        for (int i = 0; i < n; i++) {
            new DispatchedNotification(user, new Payload(payload));
        }
        login(user);
        JsonArray responseJson = invokeGetNLastNotifications(0);

        assertEquals("Result should be empty", 0, responseJson.size());
    }

    @Test(expected = Exception.class)
    public void getNLastLessThan0() {
        User user = generateUser();
        JsonElement payload = getNotificationPayload();
        int n = 10;
        for (int i = 0; i < n; i++) {
            new DispatchedNotification(user, new Payload(payload));
        }
        login(user);
        invokeGetNLastNotifications(-1);
    }

    @Test
    public void getNLastNGreaterThanAll() {
        User user = generateUser();
        JsonElement payload = getNotificationPayload();
        int n = 10;
        for (int i = 0; i < n; i++) {
            new DispatchedNotification(user, new Payload(payload));
        }

        login(user);

        int limit = n + 2;
        JsonArray responseJson = invokeGetNLastNotifications(limit);
        assertEquals("Result should have " + n + " elements", n, responseJson.size());

        for (JsonElement jsonElement : responseJson) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            assertNotification(jsonObject);
        }
    }

    @Test(expected = Exception.class)
    public void noQueryParamsProvided() {
        User user = generateUser();
        JsonElement payloadJson = getNotificationPayload();
        Payload payload = new Payload(payloadJson);
        new DispatchedNotification(user, payload);
        login(user);
        invokeGetLastNotificationsEndpoint();
    }

}
