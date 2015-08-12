package org.fenixedu.bennu.notifications.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.client.exception.NotificationsClientException;
import org.fenixedu.bennu.notifications.client.payload.NotificationPayload;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RemoteNotificationsClient implements NotificationsClient {

    private static final String USERNAMES = "usernames";
    private static final String PAYLOAD = "payload";
    private RemoteClientConfig config;
    private static final String NOTIFICATIONS_ENDPOINT = "/api/notifications";
    private Gson gson;
    private Map<String, String> headers;

    public RemoteNotificationsClient(RemoteClientConfig config) {
        this.config = config;
        this.gson = new Gson();
        this.headers = new HashMap<>();
        this.headers.put("Content-type", "application/json");
    }

    private JsonArray getUsernamesJsonArray(User... users) {
        JsonArray jsonArray = new JsonArray();
        for (User user : users) {
            JsonElement usernameElement = new JsonPrimitive(user.getUsername());
            jsonArray.add(usernameElement);
        }
        return jsonArray;
    }

    private JsonObject getJsonNotification(NotificationPayload payload, User... users) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(USERNAMES, getUsernamesJsonArray(users));
        String payloadString = gson.toJson(payload);
        JsonObject payloadJson = gson.fromJson(payloadString, JsonObject.class);
        jsonObject.add(PAYLOAD, payloadJson);
        return jsonObject;
    }

    private String getEndpointUrl(String endpoint) {
        return this.config.getEndpointUrl(endpoint);
    }

    private void invokePost(String endpoint, String body) throws NotificationsClientException {
        String url = getEndpointUrl(endpoint);
        try {
            Unirest.post(url).headers(headers).body(body).asString();
        } catch (UnirestException e) {
            throw new NotificationsClientException();
        }
    }

    private void invokePost(String endpoint, JsonElement jsonBody) throws NotificationsClientException {
        invokePost(endpoint, jsonBody.toString());
    }

    @Override
    public void postNotification(User user, NotificationPayload payload) throws NotificationsClientException {
        invokePost(NOTIFICATIONS_ENDPOINT, getJsonNotification(payload, user));
    }

    @Override
    public void postNotification(Set<User> users, NotificationPayload payload) {
        invokePost(NOTIFICATIONS_ENDPOINT, getJsonNotification(payload, users.toArray(new User[users.size()])));
    }

}
