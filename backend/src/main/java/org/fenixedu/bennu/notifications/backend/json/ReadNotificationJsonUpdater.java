package org.fenixedu.bennu.notifications.backend.json;

import org.fenixedu.bennu.core.json.JsonBuilder;
import org.fenixedu.bennu.core.json.JsonUpdater;
import org.fenixedu.bennu.notifications.backend.view.NotificationView;
import org.fenixedu.bennu.notifications.master.Master;

import com.google.gson.JsonElement;

public class ReadNotificationJsonUpdater implements JsonUpdater<NotificationView> {

    @Override
    public NotificationView update(JsonElement json, NotificationView obj, JsonBuilder ctx) {
        Master.getInstance().markAsRead(obj.getObject().getId(), obj.getUser().getUsername());
        return obj;
    }

}
