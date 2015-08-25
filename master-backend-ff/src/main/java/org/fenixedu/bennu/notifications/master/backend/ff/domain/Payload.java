package org.fenixedu.bennu.notifications.master.backend.ff.domain;

import com.google.gson.JsonElement;

public class Payload extends Payload_Base {

    public Payload(JsonElement content) {
        super();
        setContent(content);
    }

}
