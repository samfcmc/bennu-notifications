package org.fenixedu.bennu.notifications.backend.json;

import org.fenixedu.bennu.core.annotation.DefaultJsonAdapter;
import org.fenixedu.bennu.core.json.JsonBuilder;
import org.fenixedu.bennu.core.json.JsonViewer;
import org.fenixedu.bennu.notifications.backend.view.NotificationsBeforeByIdView;
import org.fenixedu.bennu.notifications.master.Master;

import com.google.gson.JsonElement;

@DefaultJsonAdapter(NotificationsBeforeByIdView.class)
public class NotificationsBeforeByIdJsonViewer implements JsonViewer<NotificationsBeforeByIdView> {

    @Override
    public JsonElement view(NotificationsBeforeByIdView obj, JsonBuilder ctx) {
        return ctx.view(Master.getNotification(obj.getUser(), obj.getObject()).getNotificationsBefore());
    }

}
