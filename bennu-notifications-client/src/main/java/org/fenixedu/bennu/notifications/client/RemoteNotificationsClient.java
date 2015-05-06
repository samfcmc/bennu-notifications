package org.fenixedu.bennu.notifications.client;

import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.notificationscore.client.NotificationsClient;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mashape.unirest.http.Unirest;

public class RemoteNotificationsClient implements NotificationsClient {

    private static final String USERNAMES = "usernames";
    private static final String PAYLOAD = "payload";
    private static RemoteNotificationsClient instance;
    private RemoteClientConfig config;
    private static final String NOTIFICATIONS_ENDPOINT = "bennu-notifications";

    private RemoteNotificationsClient() {
        this.config = RemoteClientConfig.getInstance();
    }

    public static RemoteNotificationsClient getInstance() {
        if (instance == null) {
            instance = new RemoteNotificationsClient();
        }
        return instance;
    }

    private JsonArray getUsernamesJsonArray(User... users) {
        JsonArray jsonArray = new JsonArray();
        for (User user : users) {
            JsonElement usernameElement = new JsonPrimitive(user.getUsername());
            jsonArray.add(usernameElement);
        }
        return jsonArray;
    }

    private JsonObject getJsonNotification(JsonElement payload, User... users) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(USERNAMES, getUsernamesJsonArray(users));
        jsonObject.add(PAYLOAD, payload);
        return jsonObject;
    }

    private String getEndpointUrl(String endpoint) {
        return this.config.getUrl() + endpoint;
    }

    private void invokePost(String endpoint, String body) {
        String url = getEndpointUrl(endpoint);
        Unirest.post(url).body(body);
    }

    private void invokePost(String endpoint, JsonElement jsonBody) {
        invokePost(endpoint, jsonBody.toString());
    }

    @Override
    public void postNotification(User user, JsonElement payload) {
        invokePost(NOTIFICATIONS_ENDPOINT, getJsonNotification(payload, user));
    }

    @Override
    public void postNotification(Set<User> users, JsonElement payload) {
        invokePost(NOTIFICATIONS_ENDPOINT, getJsonNotification(payload, users.toArray(new User[users.size()])));
    }

}
