package org.fenixedu.bennu.notifications.backend.json;

import org.fenixedu.bennu.core.annotation.DefaultJsonAdapter;
import org.fenixedu.bennu.core.json.JsonBuilder;
import org.fenixedu.bennu.core.json.JsonViewer;
import org.fenixedu.bennu.notifications.backend.view.NotificationsAfterByIdView;
import org.fenixedu.bennu.notifications.master.Master;

import com.google.gson.JsonElement;

@DefaultJsonAdapter(NotificationsAfterByIdView.class)
public class NotificationsAfterByIdJsonViewer implements JsonViewer<NotificationsAfterByIdView> {

    @Override
    public JsonElement view(NotificationsAfterByIdView obj, JsonBuilder ctx) {
        return ctx.view(Master.getNotification(obj.getUser(), obj.getObject()).getNotificationsAfter());
    }

}
