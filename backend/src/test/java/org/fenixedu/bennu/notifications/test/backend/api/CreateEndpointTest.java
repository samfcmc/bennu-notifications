package org.fenixedu.bennu.notifications.test.backend.api;

import org.fenixedu.bennu.core.domain.User;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class CreateEndpointTest extends AbstractAPITest {

    private static final String USERNAMES = "usernames";
    private static final String PAYLOAD = "payload";
    private static final String USERNAME = "user";
    private static final String TOKEN = "user";

    @Test
    public void success() {
        JsonObject requestJson = new JsonObject();
        requestJson.add(USERNAMES, getUsernamesJsonArray(USERNAME));
        requestJson.add(PAYLOAD, getNotificationPayload());
        JsonObject json = invokeCreateNotificationEndpoint(requestJson, TOKEN);
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

}
