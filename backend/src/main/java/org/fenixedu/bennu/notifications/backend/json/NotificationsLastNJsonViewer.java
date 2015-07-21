package org.fenixedu.bennu.notifications.backend.json;

import org.fenixedu.bennu.core.annotation.DefaultJsonAdapter;
import org.fenixedu.bennu.core.json.JsonBuilder;
import org.fenixedu.bennu.core.json.JsonViewer;
import org.fenixedu.bennu.notifications.backend.view.NotificationsLastNView;
import org.fenixedu.bennu.notifications.master.Master;

import com.google.gson.JsonElement;

@DefaultJsonAdapter(NotificationsLastNView.class)
public class NotificationsLastNJsonViewer implements JsonViewer<NotificationsLastNView> {

    @Override
    public JsonElement view(NotificationsLastNView obj, JsonBuilder ctx) {
        return ctx.view(Master.getInstance().getLastN(obj.getUser().getUsername(), obj.getObject()));
    }

}
