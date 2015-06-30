package org.fenixedu.bennu.notifications.backend.json;

import org.fenixedu.bennu.core.annotation.DefaultJsonAdapter;
import org.fenixedu.bennu.core.json.JsonBuilder;
import org.fenixedu.bennu.core.json.JsonViewer;
import org.fenixedu.bennu.notifications.backend.api.DummyResource.DummyView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@DefaultJsonAdapter(DummyView.class)
public class DummyJsonViewer implements JsonViewer<DummyView> {

    @Override
    public JsonElement view(DummyView obj, JsonBuilder builder) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("value", obj.getValue());

        return jsonObject;
    }

}
