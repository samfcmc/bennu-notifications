package org.fenixedu.notificationscore.json;

import org.fenixedu.bennu.core.annotation.DefaultJsonAdapter;
import org.fenixedu.bennu.core.json.JsonAdapter;
import org.fenixedu.bennu.core.json.JsonBuilder;
import org.fenixedu.bennu.notifications.master.NMaster;
import org.fenixedu.notificationscore.domain.Notification;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@DefaultJsonAdapter(Notification.class)
public class NotificationJsonAdapter implements JsonAdapter<Notification> {

    @Override
    public Notification create(JsonElement payload, JsonBuilder builder) {
        JsonObject jsonPayload = payload.getAsJsonObject();
        String username = jsonPayload.get("username").getAsString();
        JsonElement notificationPayload = jsonPayload.get("payload");

        return NMaster.createNotification(username, notificationPayload);
    }

    @Override
    public Notification update(JsonElement payload, Notification notification, JsonBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonElement view(Notification notification, JsonBuilder builder) {
        JsonObject jsonObject = new JsonObject();

        JsonElement notificationPayload = notification.getPayload();
        jsonObject.add("payload", notificationPayload);

        return jsonObject;
    }

}
