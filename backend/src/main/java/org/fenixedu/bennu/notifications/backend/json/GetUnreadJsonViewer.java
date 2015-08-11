package org.fenixedu.bennu.notifications.backend.json;

import org.fenixedu.bennu.core.json.JsonBuilder;
import org.fenixedu.bennu.core.json.JsonViewer;
import org.fenixedu.bennu.notifications.backend.view.GetUnreadView;
import org.fenixedu.bennu.notifications.master.Master;

import com.google.gson.JsonElement;

public class GetUnreadJsonViewer implements JsonViewer<GetUnreadView> {

    @Override
    public JsonElement view(GetUnreadView obj, JsonBuilder ctx) {
        return ctx.view(Master.getInstance().getUnread(obj.getUser().getUsername()));
    }

}
