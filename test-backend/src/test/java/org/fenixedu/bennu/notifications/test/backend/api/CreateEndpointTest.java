package org.fenixedu.bennu.notifications.test.backend.api;

import static org.fenixedu.bennu.notifications.test.utils.TestUtils.generateUser;
import static org.fenixedu.bennu.notifications.test.utils.TestUtils.login;

import org.fenixedu.bennu.core.domain.User;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class CreateEndpointTest extends AbstractAPITest {

    private static final String USERNAMES = "usernames";
    private static final String PAYLOAD = "payload";

    @Test
    public void success() {
        JsonObject requestJson = new JsonObject();
        User user = generateUser();
        login(user);
        requestJson.add(USERNAMES, getUsernamesJsonArray(user));
        requestJson.add(PAYLOAD, getNotificationPayload());
        JsonObject json = invokeCreateNotificationEndpoint(requestJson);
        JsonObject payloadJson = json.get(PAYLOAD).getAsJsonObject();

        assertNotification(json);
        assertJsonHasKey(payloadJson, KEY_1);
        assertJsonHasKey(payloadJson, KEY_2);
        assertJsonKeyEqualsValue(payloadJson, KEY_1, VALUE_1);
        assertJsonKeyEqualsValue(payloadJson, KEY_2, VALUE_2);
    }

    private JsonArray getUsernamesJsonArray(User... users) {
        JsonArray jsonArray = new JsonArray();
        for (User user : users) {
            JsonElement usernameJsonElement = new JsonPrimitive(user.getUsername());
            jsonArray.add(usernameJsonElement);
        }
        return jsonArray;
    }

    private JsonArray getUsernamesJsonArray(String... usernames) {
        JsonArray jsonArray = new JsonArray();
        for (String username : usernames) {
            JsonElement usernameJsonElement = new JsonPrimitive(username);
            jsonArray.add(usernameJsonElement);
        }
        return jsonArray;
    }

    @Test(expected = Exception.class)
    public void userNotLoggedIn() {
        User user = generateUser();
        JsonObject requestJsonObject = new JsonObject();
        requestJsonObject.add(USERNAMES, getUsernamesJsonArray(user));
        requestJsonObject.add(PAYLOAD, getNotificationPayload());
        invokeCreateNotificationEndpoint(requestJsonObject);
    }

}
