package org.fenixedu.bennu.notifications.backend.json;

import org.fenixedu.bennu.core.annotation.DefaultJsonAdapter;
import org.fenixedu.bennu.core.json.JsonBuilder;
import org.fenixedu.bennu.core.json.JsonViewer;
import org.fenixedu.bennu.notifications.backend.view.NotificationView;

import com.google.gson.JsonElement;

@DefaultJsonAdapter(NotificationView.class)
public class NotificationViewJsonViewer implements JsonViewer<NotificationView> {

    @Override
    public JsonElement view(NotificationView obj, JsonBuilder ctx) {
        return ctx.view(obj.getObject());
    }

}
