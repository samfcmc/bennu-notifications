package org.fenixedu.bennu.notifications.test.master.api;

import static org.fenixedu.bennu.notifications.test.utils.TestUtils.generateUser;
import static org.fenixedu.bennu.notifications.test.utils.TestUtils.getInexistantUsername;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;
import org.junit.Test;

import pt.ist.fenixframework.FenixFramework;

import com.google.gson.JsonObject;

public class CreateEndpointTest extends AbstractAPITest {

    private static final String KEY_1 = "key1";
    private static final String KEY_2 = "key2";
    private static final String VALUE_1 = "value1";
    private static final String VALUE_2 = "value2";

    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PAYLOAD = "payload";

    @Test
    public void success() {
        JsonObject requestJson = new JsonObject();
        User user = generateUser();
        requestJson.addProperty(USERNAME, user.getUsername());
        requestJson.add(PAYLOAD, getNotificationPayload());
        JsonObject json = invokeCreateNotificationEndpoint(requestJson);
        JsonObject payloadJson = json.get(PAYLOAD).getAsJsonObject();
        DispatchedNotification notification = FenixFramework.getDomainObject(json.get(ID).getAsString());

        assertJsonHasKey(json, ID);
        assertJsonHasKey(json, PAYLOAD);
        assertJsonHasKey(payloadJson, KEY_1);
        assertJsonHasKey(payloadJson, KEY_2);
        assertJsonKeyEqualsValue(payloadJson, KEY_1, VALUE_1);
        assertJsonKeyEqualsValue(payloadJson, KEY_2, VALUE_2);
        assertNotNull("There should be a new notification", notification);
        assertEquals("notification user should be the same", user, notification.getUser());
        assertEquals("notification payload should be the same as in response", payloadJson, notification.getPayload());
    }

    @Test(expected = Exception.class)
    public void userDoesNotExist() {
        JsonObject requestJson = new JsonObject();
        String username = getInexistantUsername();
        requestJson.addProperty(USERNAME, username);
        requestJson.add(PAYLOAD, getNotificationPayload());
        invokeCreateNotificationEndpoint(requestJson);
    }

    private JsonObject getNotificationPayload() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(KEY_1, VALUE_1);
        jsonObject.addProperty(KEY_2, VALUE_2);
        return jsonObject;
    }

}