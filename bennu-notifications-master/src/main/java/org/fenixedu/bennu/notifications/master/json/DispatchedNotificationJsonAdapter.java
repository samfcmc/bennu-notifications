package org.fenixedu.bennu.notifications.master.json;

import org.fenixedu.bennu.core.annotation.DefaultJsonAdapter;
import org.fenixedu.bennu.core.json.JsonAdapter;
import org.fenixedu.bennu.core.json.JsonBuilder;
import org.fenixedu.bennu.notifications.master.NMaster;
import org.fenixedu.bennu.notifications.master.domain.DispatchedNotification;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@DefaultJsonAdapter(DispatchedNotification.class)
public class DispatchedNotificationJsonAdapter implements JsonAdapter<DispatchedNotification> {

    @Override
    public DispatchedNotification create(JsonElement payload, JsonBuilder builder) {
        JsonObject jsonPayload = payload.getAsJsonObject();
        String username = jsonPayload.get("username").getAsString();
        JsonElement notificationPayload = jsonPayload.get("payload");

        return NMaster.createNotification(username, notificationPayload);
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
        jsonObject.addProperty("id", notification.getExternalId());
        jsonObject.add("payload", notificationPayload);

        return jsonObject;
    }

}
