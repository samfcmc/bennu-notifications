package org.fenixedu.bennu.notifications.test.backend.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.fenixedu.bennu.notifications.master.Master;
import org.fenixedu.notifications.master.backend.NotificationInfo;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GetNotificationsTest extends AbstractAPITest {

    private static final String ID = "id";
    private static final String USERNAME = "user";
    private static final String TOKEN = "user";

    @Test
    public void successNotificationsAfter() {
        JsonElement payload = getNotificationPayload();
        NotificationInfo first = Master.getInstance().createNotification(USERNAME, payload);
        Master.getInstance().createNotification(USERNAME, payload);
        Master.getInstance().createNotification(USERNAME, payload);
        JsonArray responseJson = invokeGetNotificationsAfterEndpoint(first.getId(), TOKEN);

        assertEquals("Result should have 2 elements", 2, responseJson.size());

        // Last should not be in result
        for (JsonElement jsonElement : responseJson) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String id = jsonObject.get(ID).getAsString();
            assertNotification(jsonObject);
            assertNotEquals("last notification should not be included in the result", id, first.getId());
        }
    }

    @Test(expected = Exception.class)
    public void afterIdDoesNotExist() {
        JsonElement payloadJson = getNotificationPayload();
        Master.getInstance().createNotification(USERNAME, payloadJson);
        Master.getInstance().createNotification(USERNAME, payloadJson);
        Master.getInstance().createNotification(USERNAME, payloadJson);
        invokeGetNotificationsAfterEndpoint("fake", TOKEN);
    }

    @Test(expected = Exception.class)
    public void afterIdDoesNotBelongToUser() {
        JsonElement payloadJson = getNotificationPayload();
        NotificationInfo notification = Master.getInstance().createNotification(USERNAME, payloadJson);
        Master.getInstance().createNotification(USERNAME, payloadJson);
        String wrongToken = "wrongToken";
        invokeGetNotificationsAfterEndpoint(notification.getId(), wrongToken);
    }

    @Test
    public void successNotificationsBefore() {
        JsonElement payload = getNotificationPayload();
        Master.getInstance().createNotification(USERNAME, payload);
        Master.getInstance().createNotification(USERNAME, payload);
        NotificationInfo notificationInfo = Master.getInstance().createNotification(USERNAME, payload);
        JsonArray responseJson = invokeGetNotificationsBeforeEndpoint(notificationInfo.getId(), TOKEN);

        assertEquals("Result should have 2 elements", 2, responseJson.size());

        // Notification3 should not be in result
        for (JsonElement jsonElement : responseJson) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String id = jsonObject.get(ID).getAsString();
            assertNotification(jsonObject);
            assertNotEquals("notification3 should not be included in the result", id, notificationInfo.getId());
        }
    }

    @Test(expected = Exception.class)
    public void beforeIdDoesNotBelongToUser() {
        JsonElement payloadJson = getNotificationPayload();
        Master.getInstance().createNotification(USERNAME, payloadJson);
        NotificationInfo notificationInfo = Master.getInstance().createNotification(USERNAME, payloadJson);
        String wrongToken = "wrongToken";

        invokeGetNotificationsBeforeEndpoint(notificationInfo.getId(), wrongToken);
    }

    @Test
    public void noNotificationsAfterLast() {
        JsonElement payload = getNotificationPayload();
        Master.getInstance().createNotification(USERNAME, payload);
        Master.getInstance().createNotification(USERNAME, payload);
        NotificationInfo notificationInfo = Master.getInstance().createNotification(USERNAME, payload);
        JsonArray responseJson = invokeGetNotificationsAfterEndpoint(notificationInfo.getId(), TOKEN);

        assertEquals("Result should be empty", 0, responseJson.size());
    }

    @Test
    public void getNLastGreaterThan0() {
        JsonElement payload = getNotificationPayload();
        int n = 10;
        for (int i = 0; i < n; i++) {
            Master.getInstance().createNotification(USERNAME, payload);
        }
        JsonArray responseJson = invokeGetNLastNotifications(n, TOKEN);

        assertEquals("Result should have " + n + " elements", n, responseJson.size());

        for (JsonElement jsonElement : responseJson) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            assertNotification(jsonObject);
        }
    }

    @Test
    public void getNLastEqual0() {
        JsonElement payload = getNotificationPayload();
        int n = 10;
        for (int i = 0; i < n; i++) {
            Master.getInstance().createNotification(USERNAME, payload);
        }
        JsonArray responseJson = invokeGetNLastNotifications(0, TOKEN);

        assertEquals("Result should be empty", 0, responseJson.size());
    }

    @Test(expected = Exception.class)
    public void getNLastLessThan0() {
        JsonElement payload = getNotificationPayload();
        int n = 10;
        for (int i = 0; i < n; i++) {
            Master.getInstance().createNotification(USERNAME, payload);
        }
        invokeGetNLastNotifications(-1, TOKEN);
    }

    @Test
    public void getNLastNGreaterThanAll() {
        JsonElement payload = getNotificationPayload();
        int n = 10;
        for (int i = 0; i < n; i++) {
            Master.getInstance().createNotification(USERNAME, payload);
        }

        int limit = n + 2;
        JsonArray responseJson = invokeGetNLastNotifications(limit, TOKEN);
        System.out.println(responseJson.toString());
        assertEquals("Result should have " + n + " elements", n, responseJson.size());

        for (JsonElement jsonElement : responseJson) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            assertNotification(jsonObject);
        }
    }

    @Test(expected = Exception.class)
    public void noQueryParamsProvided() {
        JsonElement payloadJson = getNotificationPayload();
        Master.getInstance().createNotification(USERNAME, payloadJson);
        invokeGetLastNotificationsEndpoint(TOKEN);
    }

}
