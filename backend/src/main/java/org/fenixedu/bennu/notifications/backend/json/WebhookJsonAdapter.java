package org.fenixedu.bennu.notifications.backend.json;

import org.fenixedu.bennu.core.annotation.DefaultJsonAdapter;
import org.fenixedu.bennu.core.json.JsonAdapter;
import org.fenixedu.bennu.core.json.JsonBuilder;
import org.fenixedu.bennu.notifications.master.Master;
import org.fenixedu.notifications.master.backend.webhook.Webhook;
import org.fenixedu.notifications.master.backend.webhook.WebhookMethod;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@DefaultJsonAdapter(Webhook.class)
public class WebhookJsonAdapter implements JsonAdapter<Webhook> {

    private Gson gson = new Gson();

    private static final String NAME = "name";
    private static final String METHOD = "method";
    private static final String URL = "url";
    private static final String DATA = "data";

    @Override
    public Webhook create(JsonElement json, JsonBuilder ctx) {
        JsonObject payload = json.getAsJsonObject();
        String name = payload.get(NAME).getAsString();
        String methodName = payload.get(METHOD).getAsString();
        WebhookMethod method = WebhookMethod.valueOf(methodName);
        String url = payload.get(URL).getAsString();
        String data = payload.get(DATA).getAsString();
        JsonObject dataJson = gson.fromJson(data, JsonObject.class);
        return Master.getInstance().createWebhook(name, method, url, dataJson);
    }

    @Override
    public Webhook update(JsonElement json, Webhook obj, JsonBuilder ctx) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonElement view(Webhook obj, JsonBuilder ctx) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(NAME, obj.getName());
        jsonObject.addProperty(METHOD, obj.getMethod().name());
        jsonObject.addProperty(URL, obj.getUrl());
        jsonObject.add(DATA, obj.getData());

        return jsonObject;
    }

}
