package org.fenixedu.bennu.notifications.test.backend.api;

import static org.fenixedu.bennu.notifications.test.utils.TestUtils.generateUser;
import static org.fenixedu.bennu.notifications.test.utils.TestUtils.login;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.Master;
import org.fenixedu.notifications.master.backend.NotificationInfo;
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
        String username = user.getUsername();
        NotificationInfo first = Master.getInstance().createNotification(username, payload);
        Master.getInstance().createNotification(username, payload);
        Master.getInstance().createNotification(username, payload);
        login(user);
        JsonArray responseJson = invokeGetNotificationsAfterEndpoint(first.getId());

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
        User user = generateUser();
        JsonElement payloadJson = getNotificationPayload();
        String username = user.getUsername();
        Master.getInstance().createNotification(username, payloadJson);
        Master.getInstance().createNotification(username, payloadJson);
        Master.getInstance().createNotification(username, payloadJson);
        login(user);
        invokeGetNotificationsAfterEndpoint("fake");
    }

    @Test(expected = Exception.class)
    public void afterIdDoesNotBelongToUser() {
        User loggedIn = generateUser();
        User user = generateUser();
        String username = user.getUsername();
        JsonElement payloadJson = getNotificationPayload();
        NotificationInfo notification = Master.getInstance().createNotification(username, payloadJson);
        Master.getInstance().createNotification(username, payloadJson);
        login(loggedIn);
        invokeGetNotificationsAfterEndpoint(notification.getId());
    }

    @Test
    public void successNotificationsBefore() {
        User user = generateUser();
        JsonElement payload = getNotificationPayload();
        String username = user.getUsername();
        Master.getInstance().createNotification(username, payload);
        Master.getInstance().createNotification(username, payload);
        NotificationInfo notificationInfo = Master.getInstance().createNotification(username, payload);
        login(user);
        JsonArray responseJson = invokeGetNotificationsBeforeEndpoint(notificationInfo.getId());

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
        User loggedIn = generateUser();
        User user = generateUser();
        String username = user.getUsername();
        JsonElement payloadJson = getNotificationPayload();
        Master.getInstance().createNotification(username, payloadJson);
        NotificationInfo notificationInfo = Master.getInstance().createNotification(username, payloadJson);

        login(loggedIn);
        invokeGetNotificationsBeforeEndpoint(notificationInfo.getId());
    }

    @Test
    public void noNotificationsAfterLast() {
        User user = generateUser();
        JsonElement payload = getNotificationPayload();
        String username = user.getUsername();
        Master.getInstance().createNotification(username, payload);
        Master.getInstance().createNotification(username, payload);
        NotificationInfo notificationInfo = Master.getInstance().createNotification(username, payload);
        login(user);
        JsonArray responseJson = invokeGetNotificationsAfterEndpoint(notificationInfo.getId());

        assertEquals("Result should be empty", 0, responseJson.size());
    }

    @Test
    public void getNLastGreaterThan0() {
        User user = generateUser();
        JsonElement payload = getNotificationPayload();
        String username = user.getUsername();
        int n = 10;
        for (int i = 0; i < n; i++) {
            Master.getInstance().createNotification(username, payload);
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
        String username = user.getUsername();
        int n = 10;
        for (int i = 0; i < n; i++) {
            Master.getInstance().createNotification(username, payload);
        }
        login(user);
        JsonArray responseJson = invokeGetNLastNotifications(0);

        assertEquals("Result should be empty", 0, responseJson.size());
    }

    @Test(expected = Exception.class)
    public void getNLastLessThan0() {
        User user = generateUser();
        JsonElement payload = getNotificationPayload();
        String username = user.getUsername();
        int n = 10;
        for (int i = 0; i < n; i++) {
            Master.getInstance().createNotification(username, payload);
        }
        login(user);
        invokeGetNLastNotifications(-1);
    }

    @Test
    public void getNLastNGreaterThanAll() {
        User user = generateUser();
        JsonElement payload = getNotificationPayload();
        String username = user.getUsername();
        int n = 10;
        for (int i = 0; i < n; i++) {
            Master.getInstance().createNotification(username, payload);
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
        String username = user.getUsername();
        Master.getInstance().createNotification(username, payloadJson);
        login(user);
        invokeGetLastNotificationsEndpoint();
    }

}
