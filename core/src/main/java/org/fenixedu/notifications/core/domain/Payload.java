package org.fenixedu.notifications.core.domain;

import com.google.gson.JsonElement;

public class Payload extends Payload_Base {

    public Payload(JsonElement content) {
        super();
        setContent(content);
    }

}
