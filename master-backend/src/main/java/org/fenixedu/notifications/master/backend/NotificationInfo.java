package org.fenixedu.notifications.master.backend;

import org.joda.time.DateTime;

import com.google.gson.JsonElement;

public class NotificationInfo {
    private String id;
    String username;
    private JsonElement payload;
    private boolean read;
    private DateTime timestamp;

    public NotificationInfo(String id, String username, JsonElement payload, boolean read, DateTime timestamp) {
        this.id = id;
        this.username = username;
        this.payload = payload;
        this.read = read;
        this.timestamp = timestamp;
    }

    public NotificationInfo(String id, String username, JsonElement payload, DateTime timestamp) {
        this(id, username, payload, false, timestamp);
    }

    public String getId() {
        return id;
    }

    public JsonElement getPayload() {
        return payload;
    }

    public boolean isRead() {
        return read;
    }

    public String getUsername() {
        return username;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }
}
