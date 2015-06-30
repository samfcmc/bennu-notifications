package org.fenixedu.bennu.notifications.backend.json;

import org.fenixedu.bennu.core.annotation.DefaultJsonAdapter;
import org.fenixedu.bennu.core.json.JsonBuilder;
import org.fenixedu.bennu.core.json.JsonViewer;
import org.fenixedu.notifications.core.domain.Payload;

import com.google.gson.JsonElement;

@DefaultJsonAdapter(Payload.class)
public class PayloadJsonViewer implements JsonViewer<Payload> {

    @Override
    public JsonElement view(Payload obj, JsonBuilder ctx) {
        return obj.getContent();
    }

}
