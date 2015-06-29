package org.fenixedu.bennu.notifications.master.json;

import java.util.ArrayList;
import java.util.List;

import org.fenixedu.bennu.core.annotation.DefaultJsonAdapter;
import org.fenixedu.bennu.core.json.JsonAdapter;
import org.fenixedu.bennu.core.json.JsonBuilder;
import org.fenixedu.bennu.core.security.Authenticate;
import org.fenixedu.bennu.notifications.master.Master;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@DefaultJsonAdapter(DispatchedNotification.class)
public class DispatchedNotificationJsonAdapter implements JsonAdapter<DispatchedNotification> {

    private static final String USERNAMES = "usernames";
    private static final String PAYLOAD = "payload";
    private static final String ID = "id";

    @Override
    public DispatchedNotification create(JsonElement payload, JsonBuilder builder) {
        JsonObject jsonPayload = payload.getAsJsonObject();
        JsonArray usernamesArray = jsonPayload.get(USERNAMES).getAsJsonArray();
        List<String> usernames = getUsernames(usernamesArray);
        JsonElement notificationPayload = jsonPayload.get(PAYLOAD);

        Master.createNotification(usernames, notificationPayload);
        return Authenticate.getUser().getLastNotification();
    }

    @Override
    public DispatchedNotification update(JsonElement payload, DispatchedNotification notification, JsonBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonElement view(DispatchedNotification notification, JsonBuilder builder) {
        JsonObject jsonObject = new JsonObject();
        JsonElement notificationPayload = notification.getPayload();
        jsonObject.addProperty(ID, notification.getExternalId());
        jsonObject.add(PAYLOAD, notificationPayload);

        return jsonObject;
    }

    private List<String> getUsernames(JsonArray usernamesJsonArray) {
        List<String> usernames = new ArrayList<String>(usernamesJsonArray.size());
        for (JsonElement usernameJsonElement : usernamesJsonArray) {
            String username = usernameJsonElement.getAsString();
            usernames.add(username);
        }
        return usernames;
    }

}
