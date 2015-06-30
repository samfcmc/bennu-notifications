package org.fenixedu.notifications.core.domain;

import org.joda.time.DateTime;

import com.google.gson.JsonObject;

public class Notification extends Notification_Base {

    public Notification() {
        this(new Payload(new JsonObject()));
    }

    public Notification(Payload payload) {
        this(payload, DateTime.now());
    }

    public Notification(Payload payload, DateTime timestamp) {
        init(payload, timestamp);
    }

    protected void init(Payload payload, DateTime timestamp) {
        setPayload(payload);
        setTimestamp(timestamp);
    }

    @Override
    public void setTimestamp(DateTime timestamp) {
        super.setTimestamp(timestamp);
    }

}
