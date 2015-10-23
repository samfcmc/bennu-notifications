package org.fenixedu.notifications.master.backend.webhook;

import com.google.gson.JsonObject;

public class Webhook {
    private String name;
    private WebhookMethod method;
    private String url;
    private JsonObject data;

    public Webhook(String name, WebhookMethod method, String url, JsonObject data) {
        this.name = name;
        this.method = method;
        this.url = url;
        this.data = data;
    }

    public Webhook(String name, String url) {
        this(name, WebhookMethod.GET, url, new JsonObject());
    }

    public String getName() {
        return name;
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
