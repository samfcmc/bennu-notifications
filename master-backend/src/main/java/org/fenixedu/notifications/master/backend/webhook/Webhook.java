package org.fenixedu.notifications.master.backend.webhook;

import com.google.gson.JsonObject;

public class Webhook {
    private WebhookMethod method;
    private String url;
    private JsonObject data;

    public Webhook(WebhookMethod method, String url, JsonObject data) {
        this.method = method;
        this.url = url;
        this.data = data;
    }

    public Webhook(String url) {
        this(WebhookMethod.GET, url, new JsonObject());
    }

    public WebhookMethod getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public JsonObject getData() {
        return data;
    }

    public void invoke() {
        //TODO: Make the request
    }
}
