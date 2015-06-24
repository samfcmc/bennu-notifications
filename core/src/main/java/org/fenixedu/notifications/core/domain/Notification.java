package org.fenixedu.notifications.core.domain;

import org.joda.time.DateTime;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Notification extends Notification_Base {

	public Notification() {
		this(new JsonObject());
	}

	public Notification(JsonElement payload) {
		this(payload, DateTime.now());
	}

	public Notification(JsonElement payload, DateTime timestamp) {
		init(payload, timestamp);
	}

	protected void init(JsonElement payload, DateTime timestamp) {
		setPayload(payload);
		setTimestamp(timestamp);
	}

	@Override
	public void setTimestamp(DateTime timestamp) {
		super.setTimestamp(timestamp);
	}

}
