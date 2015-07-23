package org.fenixedu.bennu.notifications.backend.json;

import java.util.ArrayList;
import java.util.List;

import org.fenixedu.bennu.core.annotation.DefaultJsonAdapter;
import org.fenixedu.bennu.core.json.JsonBuilder;
import org.fenixedu.bennu.core.json.JsonCreator;
import org.fenixedu.bennu.core.json.JsonViewer;
import org.fenixedu.bennu.notifications.master.Master;
import org.fenixedu.notifications.master.backend.NotificationInfo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@DefaultJsonAdapter(NotificationInfo.class)
public class NotificationInfoJsonAdapter implements JsonViewer<NotificationInfo>, JsonCreator<NotificationInfo> {

    private static final String USERNAMES = "usernames";
    private static final String PAYLOAD = "payload";
    private static final String ID = "id";
    private static final String READ = "read";
    private static final String TIMESTAMP = "timestamp";

    @Override
    public NotificationInfo create(JsonElement payload, JsonBuilder builder) {
        JsonObject jsonPayload = payload.getAsJsonObject();
        JsonArray usernamesArray = jsonPayload.get(USERNAMES).getAsJsonArray();
        List<String> usernames = getUsernames(usernamesArray);
        JsonElement notificationPayload = jsonPayload.get(PAYLOAD);

        return Master.getInstance().createNotifications(usernames, notificationPayload).iterator().next();
    }

    @Override
    public JsonElement view(NotificationInfo notification, JsonBuilder builder) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ID, notification.getId());
        jsonObject.add(PAYLOAD, notification.getPayload());
        jsonObject.addProperty(READ, notification.isRead());
        jsonObject.addProperty(TIMESTAMP, notification.getTimestamp().getMillis());

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
