package org.fenixedu.bennu.notifications.client;

import java.util.Set;

import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.notifications.client.exception.NotificationsClientException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RemoteNotificationsClient implements NotificationsClient {

    private static final String USERNAMES = "usernames";
    private static final String PAYLOAD = "payload";
    private RemoteClientConfig config;
    private static final String NOTIFICATIONS_ENDPOINT = "bennu-notifications";

    public RemoteNotificationsClient(RemoteClientConfig config) {
        this.config = config;
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
        return this.config.getEndpointUrl(endpoint);
    }

    private void invokePost(String endpoint, String body) throws NotificationsClientException {
        String url = getEndpointUrl(endpoint);
        try {
            Unirest.post(url).body(body).asString();
        } catch (UnirestException e) {
            throw new NotificationsClientException();
        }
    }

    private void invokePost(String endpoint, JsonElement jsonBody) throws NotificationsClientException {
        invokePost(endpoint, jsonBody.toString());
    }

    private void invokePostAsync(String endpoint, String body, ClientCallback callback) {
        String url = getEndpointUrl(endpoint);
        Unirest.post(url).body(body).asStringAsync(new Callback<String>() {
            @Override
            public void failed(UnirestException exception) {
                callback.failed(exception);
            }

            @Override
            public void completed(HttpResponse<String> response) {
                callback.success();
            }

            @Override
            public void cancelled() {
                callback.failed(new NotificationsClientException());

            }
        });
    }

    private void invokePostAsync(String endpoint, JsonElement jsonBody, ClientCallback callback) {
        invokePostAsync(endpoint, jsonBody.toString(), callback);
    }

    @Override
    public void postNotification(User user, JsonElement payload) throws NotificationsClientException {
        invokePost(NOTIFICATIONS_ENDPOINT, getJsonNotification(payload, user));
    }

    @Override
    public void postNotification(Set<User> users, JsonElement payload) {
        invokePost(NOTIFICATIONS_ENDPOINT, getJsonNotification(payload, users.toArray(new User[users.size()])));
    }

    @Override
    public void postNotificationAsync(User user, JsonElement payload, ClientCallback callback) {
        invokePostAsync(NOTIFICATIONS_ENDPOINT, getJsonNotification(payload, user), callback);
    }

    @Override
    public void postNotificationAsync(Set<User> users, JsonElement payload, ClientCallback callback) {
        invokePostAsync(NOTIFICATIONS_ENDPOINT, getJsonNotification(payload, users.toArray(new User[users.size()])), callback);

    }

}
